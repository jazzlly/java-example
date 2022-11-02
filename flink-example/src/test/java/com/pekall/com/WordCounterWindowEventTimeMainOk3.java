package com.pekall.com;

import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.eventtime.TimestampAssigner;
import org.apache.flink.api.common.eventtime.TimestampAssignerSupplier;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

import java.time.Duration;

/**
 * word counter, 统计一段时间窗口内的单词频率
 */
@Slf4j
public class WordCounterWindowEventTimeMainOk3 {

    public static void main(String[] args) throws Exception {

        final StreamExecutionEnvironment env =
                StreamExecutionEnvironment.getExecutionEnvironment();
        // env.getConfig().setAutoWatermarkInterval(1000L);
        // env.setBufferTimeout(1000L);

        DataStreamSource<String> socketTextStream =
                env.socketTextStream("192.168.10.252", 9999);

        SingleOutputStreamOperator<String> source = socketTextStream
                .assignTimestampsAndWatermarks(
                        WatermarkStrategy.<String>forBoundedOutOfOrderness(Duration.ofSeconds(2L))
                                .withTimestampAssigner(
                                        new TimestampAssignerSupplier<String>() {
                                    @Override
                                    public TimestampAssigner<String> createTimestampAssigner(Context context) {
                                        return new TimestampAssigner<String>() {
                                            @Override
                                            public long extractTimestamp(String element, long recordTimestamp) {
                                                System.out.println("extractTimestamp");
                                                return System.currentTimeMillis();
                                            }
                                        };
                                    }
                                }));


        source.flatMap(new FlatMapFunction<String, Tuple2<String, Long>>() {
                    @Override
                    public void flatMap(String value, Collector<Tuple2<String, Long>> out) throws Exception {
                        System.out.println("flatmap, got value: " + value);
                        out.collect(new Tuple2<>(value, 1L));
                    }
                })
                .keyBy(value -> value.f0)
                .window(TumblingEventTimeWindows.of(Time.seconds(5)))
                .sum(1)
                .print();

        env.execute("xixihaha");
    }
}
