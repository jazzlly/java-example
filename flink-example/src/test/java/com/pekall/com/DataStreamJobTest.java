package com.pekall.com;

import org.apache.commons.codec.binary.Hex;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;

public class DataStreamJobTest {

    @Test
    public void smoke() throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // 设置执行参数
        env.getConfig().setAutoWatermarkInterval(10_000L);

        // 流中元素默认后放到缓冲区中，缓冲满后，会flush到输出中
        // 设置timeout后，即使缓存区没有慢，超时后也会将元素flush到输出中
        // 可以设置env或算子的timeout
        env.setBufferTimeout(5_000);

        // 原始的stream
        DataStreamSource<String> textStream = env.socketTextStream("192.168.10.252", 9999);
        textStream.print();

        // 将原始stream转化为hex string steam
        SingleOutputStreamOperator<String> hexStringOutput = textStream.map(
                (MapFunction<String, String>)
                        s -> Hex.encodeHexString(s.getBytes(StandardCharsets.UTF_8), true));
        hexStringOutput.print();

        SingleOutputStreamOperator<String> filteredOutput = textStream.filter(new FilterFunction<String>() {
            @Override
            public boolean filter(String value) throws Exception {
                return !"xixixi".equals(value);
            }
        });
        filteredOutput.print();


        SingleOutputStreamOperator<Tuple2<String, Long>> sum = textStream.flatMap(new FlatMapFunction<String, Tuple2<String, Long>>() {
                    @Override
                    public void flatMap(String value, Collector<Tuple2<String, Long>> out) throws Exception {
                        String[] strings = value.split("\\W+");
                        for (String string : strings) {
                            out.collect(new Tuple2<>(string, 1L));
                        }
                    }
                }).keyBy(value -> value.f0)
                .window(TumblingProcessingTimeWindows.of(Time.minutes(1L)))
                .sum(1);
        sum.print();


        env.execute();
    }
}