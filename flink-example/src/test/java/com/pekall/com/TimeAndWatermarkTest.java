package com.pekall.com;

import org.apache.commons.codec.binary.Hex;
import org.apache.flink.api.common.eventtime.*;
import org.apache.flink.api.common.functions.FilterFunction;
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

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Random;

public class TimeAndWatermarkTest {

    @Test
    public void smoke() throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.getConfig().setAutoWatermarkInterval(5_000);
        // 原始的stream
        DataStreamSource<String> textStream = env.socketTextStream("192.168.10.252", 9999);
        textStream.print();

        SingleOutputStreamOperator<Tuple3<String, Long, Long>> map = textStream.map(
                new MapFunction<String, Tuple3<String, Long, Long>>() {
            @Override
            public Tuple3<String, Long, Long> map(String value) throws Exception {
                return new Tuple3<>(value, System.currentTimeMillis() - 50_000L, 1L);
            }
        });

        WatermarkStrategy<Tuple3<String, Long, Long>> strategy = WatermarkStrategy
                .<Tuple3<String, Long, Long>>forBoundedOutOfOrderness(Duration.ofMinutes(1L))
                .withTimestampAssigner(new TimestampAssignerSupplier<Tuple3<String, Long, Long>>() {
                    @Override
                    public TimestampAssigner<Tuple3<String, Long, Long>> createTimestampAssigner(Context context) {
                        return new TimestampAssigner<Tuple3<String, Long, Long>>() {
                            @Override
                            public long extractTimestamp(Tuple3<String, Long, Long> element, long recordTimestamp) {
                                return element.f1;
                            }
                        };
                    }
                });

        SingleOutputStreamOperator<Tuple3<String, Long, Long>> sum =
                map.assignTimestampsAndWatermarks(strategy).keyBy(
                        new KeySelector<Tuple3<String, Long, Long>, Object>() {
                    @Override
                    public Object getKey(Tuple3<String, Long, Long> value) throws Exception {
                        return value.f0;
                    }
                }).window(TumblingEventTimeWindows.of(Time.seconds(10)))
                .sum(2);
        sum.print();

        env.execute();
    }
}