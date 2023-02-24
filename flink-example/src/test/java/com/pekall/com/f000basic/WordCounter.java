package com.pekall.com.f000basic;

import com.pekall.com.util.GsonUtils;
import com.pekall.com.vo.LogMenuDict;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.eventtime.TimestampAssigner;
import org.apache.flink.api.common.eventtime.TimestampAssignerSupplier;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

import java.time.Duration;
import java.util.UUID;

/**
 * word counter, 统计一段时间窗口内的单词频率
 */
@Slf4j
public class WordCounter {

    public static void main(String[] args) throws Exception {

        final StreamExecutionEnvironment env =
                StreamExecutionEnvironment.getExecutionEnvironment();

        // DataStreamSource<String> socketTextStream =
                // env.socketTextStream("192.168.10.252", 9999);

        // 添加事件源， 每隔3秒创建一个事件
        DataStreamSource<String> source = env.addSource(new RichSourceFunction<String>() {
            private volatile boolean run = true;

            @Override
            public void run(SourceContext<String> sourceContext) throws Exception {
                while (run) {
                    sourceContext.collectWithTimestamp(
                            UUID.randomUUID().toString(), System.currentTimeMillis());
                    Thread.sleep(3_000L);
                }
            }
            @Override
            public void cancel() {
                run = false;
            }
        });

        SingleOutputStreamOperator<Tuple2<String, Integer>> sum = source.flatMap(
                new FlatMapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public void flatMap(String value, Collector<Tuple2<String, Integer>> out) throws Exception {
                String[] split = value.split("\\s+");
                for (String s : split) {
                    Tuple2<String, Integer> tuple2 = new Tuple2<>(s, 1);
                    out.collect(tuple2);
                }
            }
        }).keyBy(new KeySelector<Tuple2<String, Integer>, String>() {
            @Override
            public String getKey(Tuple2<String, Integer> value) throws Exception {
                return value.f0;
            }
        }).sum(1);

        sum.print();
        env.execute("word count demo");
    }
}
