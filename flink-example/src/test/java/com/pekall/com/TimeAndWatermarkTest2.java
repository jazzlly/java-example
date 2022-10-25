package com.pekall.com;

import org.apache.flink.api.common.eventtime.TimestampAssigner;
import org.apache.flink.api.common.eventtime.TimestampAssignerSupplier;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;
import org.junit.Test;

import java.time.Duration;

public class TimeAndWatermarkTest2 {

    @Test
    public void smoke() throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // 原始的stream
        DataStreamSource<String> textStream = env.socketTextStream("192.168.10.252", 9999);
        textStream.print();

        SingleOutputStreamOperator<Tuple2<String, Long>> flatMap = textStream.flatMap(new FlatMapFunction<String, Tuple2<String, Long>>() {
            @Override
            public void flatMap(String value, Collector<Tuple2<String, Long>> out) throws Exception {
                out.collect(new Tuple2<>(value, 1L));
            }
        });
        flatMap.print();

        SingleOutputStreamOperator<Tuple2<String, Long>> watermarks = flatMap.assignTimestampsAndWatermarks(WatermarkStrategy
                .<Tuple2<String, Long>>forBoundedOutOfOrderness(Duration.ofSeconds(10))
                .withTimestampAssigner(new TimestampAssignerSupplier<Tuple2<String, Long>>() {
                    @Override
                    public TimestampAssigner<Tuple2<String, Long>> createTimestampAssigner(Context context) {
                        return new TimestampAssigner<Tuple2<String, Long>>() {
                            @Override
                            public long extractTimestamp(Tuple2<String, Long> element, long recordTimestamp) {
                                System.out.println("extract timestamp: " + recordTimestamp);
                                return System.currentTimeMillis() - 5_000L;
                            }
                        };
                    }
                }));
        watermarks.print();

        SingleOutputStreamOperator<Tuple2<String, Long>> sum =
                watermarks.keyBy(value -> value.f0)
                .window(TumblingProcessingTimeWindows.of(Time.seconds(10)))
                .sum(1);
        sum.print();


        env.execute();
    }
}