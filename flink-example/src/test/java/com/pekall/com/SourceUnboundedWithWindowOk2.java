package com.pekall.com;

import com.pekall.com.util.GsonUtils;
import com.pekall.com.vo.LogMenuDict;
import org.apache.flink.api.common.eventtime.TimestampAssigner;
import org.apache.flink.api.common.eventtime.TimestampAssignerSupplier;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;
import org.apache.flink.streaming.api.functions.windowing.AllWindowFunction;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;
import org.junit.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SourceUnboundedWithWindowOk2 {

    @Test
    public void smoke() throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.getConfig().setAutoWatermarkInterval(1000L);

        // 添加事件源， 每隔3秒创建一个事件
        DataStreamSource<LogMenuDict> source = env.addSource(new RichSourceFunction<LogMenuDict>() {
            private volatile boolean run = true;

            @Override
            public void run(SourceContext<LogMenuDict> sourceContext) throws Exception {
                while (run) {
                    LogMenuDict foo = LogMenuDict.foo();
                    System.out.println("log menu from source: " + GsonUtils.toJson(foo));
                    // sourceContext.collectWithTimestamp(foo, System.currentTimeMillis());
                    sourceContext.collect(foo);

                    Thread.sleep(2_000L);
                }
            }
            @Override
            public void cancel() {
                run = false;
            }
        });

        source
                // 创建一个dummy watermark 创建器，仅仅输出日志
                .assignTimestampsAndWatermarks(
                        WatermarkStrategy.<LogMenuDict>forBoundedOutOfOrderness(Duration.ofSeconds(2))
                                .withTimestampAssigner(new TimestampAssignerSupplier<LogMenuDict>() {
                                    @Override
                                    public TimestampAssigner<LogMenuDict> createTimestampAssigner(Context context) {
                                        return new TimestampAssigner<LogMenuDict>() {
                                            @Override
                                            public long extractTimestamp(LogMenuDict element, long recordTimestamp) {
                                                System.out.println("extract time stamp: " + recordTimestamp);
                                                return System.currentTimeMillis();
                                            }
                                        };
                                    }
                                })
                )
                // 创建一个15秒的时间窗口收集日志，并打印
                .windowAll(TumblingEventTimeWindows.of(Time.seconds(5))).apply(
                        new AllWindowFunction<LogMenuDict, List<LogMenuDict>, TimeWindow>() {
                            @Override
                            public void apply(TimeWindow timeWindow, Iterable<LogMenuDict> iterable,
                                              Collector<List<LogMenuDict>> collector) throws Exception {
                                if (!iterable.iterator().hasNext()) {
                                    System.out.println("no data, ignore!");
                                    return;
                                }

                                List<LogMenuDict> dicts = new ArrayList<>();
                                for (LogMenuDict dict : iterable) {
                                    dicts.add(dict);
                                }

                                System.out.println("got data: " + GsonUtils.toJson(dicts));
                                collector.collect(dicts);
                            }
                        });

        env.execute("haha");
    }
}
