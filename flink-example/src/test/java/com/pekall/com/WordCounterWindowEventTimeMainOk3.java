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
        env.getConfig().setAutoWatermarkInterval(1000L);
        env.setBufferTimeout(1000L);

        DataStreamSource<String> socketTextStream =
                env.socketTextStream("192.168.10.252", 9999);

        SingleOutputStreamOperator<String> source = socketTextStream
                .assignTimestampsAndWatermarks(
                        WatermarkStrategy.<String>forBoundedOutOfOrderness(Duration.ofSeconds(3L))
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


        // 单词总量统计
//        SingleOutputStreamOperator<Tuple2<String, Long>> sum = source
//                .windowAll(TumblingEventTimeWindows.of(Time.seconds(10)))
//                .sum(1);
//        sum.print();

        // 单词词频统计
//        SingleOutputStreamOperator<Tuple2<String, Long>> keyBySum = source
//                .keyBy(value -> value.f0)
//                // 两分钟的窗口: 每小时的 0:00, 2:00, 4:00, ... 创建一个窗口
//                // .window(TumblingProcessingTimeWindows.of(Time.minutes(2L)))
//                .window(TumblingEventTimeWindows.of(Time.seconds(10L)))
//                .sum(1);
//        keyBySum.print();

        // source.print();
//        sum.addSink(new SinkFunction<Tuple2<String, Long>>() {
//            @Override
//            public void invoke(Tuple2<String, Long> value, Context context) throws Exception {
//                log.info("total word count: {}, time: {}",
//                        value.f1, simpleDateFormat.format(new Date(context.currentProcessingTime())));
//            }
//        });
//
//        keyBySum.addSink(new SinkFunction<Tuple2<String, Long>>() {
//            @Override
//            public void invoke(Tuple2<String, Long> value, Context context) throws Exception {
//                log.info("key: {}, count: {}, time: {}", value.f0, value.f1,
//                        simpleDateFormat.format(new Date(context.currentProcessingTime())));
//            }
//        });

        env.execute("xixihaha");
    }
}
