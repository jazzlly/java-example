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

import java.text.SimpleDateFormat;
import java.time.Duration;

/**
 * word counter, 统计一段时间窗口内的单词频率
 */
@Slf4j
public class WordCounterWindowEventTimeMainOk {

    public static void main(String[] args) throws Exception {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.getConfig().setAutoWatermarkInterval(1000L);

        DataStreamSource<String> socketTextStream = env.socketTextStream("192.168.10.252", 9999);

        // socketTextStream.

        SingleOutputStreamOperator<Tuple2<String, Long>> source = socketTextStream
                .flatMap(new FlatMapFunction<String, Tuple2<String, Long>>() {
                    @Override
                    public void flatMap(String value, Collector<Tuple2<String, Long>> out) throws Exception {
                        String[] strings = value.toLowerCase().split("\\W+");
                        for (String string : strings) {
                            if (string.length() > 0) {
                                log.info("got word: {}", string);
                                out.collect(new Tuple2<>(string, 1L));
                            }
                        }
                    }
                }).assignTimestampsAndWatermarks(
                        WatermarkStrategy.<Tuple2<String, Long>>forBoundedOutOfOrderness(Duration.ofSeconds(10L))
                                .withTimestampAssigner(new TimestampAssignerSupplier<Tuple2<String, Long>>() {
                                    @Override
                                    public TimestampAssigner<Tuple2<String, Long>> createTimestampAssigner(Context context) {
                                        return new TimestampAssigner<Tuple2<String, Long>>() {
                                            @Override
                                            public long extractTimestamp(Tuple2<String, Long> element, long recordTimestamp) {
                                                System.out.println("extractTimestamp: " + recordTimestamp);
                                                return System.currentTimeMillis();
                                            }
                                        };
                                    }
                                }));

        // 单词总量统计
        SingleOutputStreamOperator<Tuple2<String, Long>> sum = source
                .windowAll(TumblingEventTimeWindows.of(Time.seconds(10)))
                .sum(1);
        sum.print();

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

        env.execute("xxxx");
    }
}
