package com.pekall.com;

import com.pekall.com.util.GsonUtils;
import com.pekall.com.vo.AppUsageAllStatsVo;
import com.pekall.com.vo.AppUsageLogVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.RichFlatMapFunction;
import org.apache.flink.api.common.state.MapState;
import org.apache.flink.api.common.state.MapStateDescriptor;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.connector.sink2.Sink;
import org.apache.flink.api.connector.sink2.SinkWriter;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple4;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.PrintSinkFunction;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;
import org.junit.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.Random;

@Slf4j
public class AppUsageStatTest {

    @Test
    public void smoke() throws Exception {

        Configuration conf = new Configuration();
        conf.setInteger("rest.port", 8088);

        final StreamExecutionEnvironment env =
                StreamExecutionEnvironment.createLocalEnvironment(2, conf);


        // 添加事件源， 每隔3秒创建一个事件
        DataStreamSource<AppUsageLogVo> source = env.addSource(
                new RichSourceFunction<AppUsageLogVo>() {
                    private volatile boolean run = true;

                    private Random random = new Random();

                    @Override
                    public void run(SourceContext<AppUsageLogVo> sourceContext) {
                        while (run) {
                            AppUsageLogVo foo = AppUsageLogVo.buildRandomVo();
                            sourceContext.collectWithTimestamp(foo, System.currentTimeMillis());
                            // sourceContext.collect(foo);
                            // System.out.println("app usage from source: " + GsonUtils.toJson(foo));
                            try {
                                Thread.sleep(100L + random.nextInt(400));
                            } catch (InterruptedException e) {
                                log.info("", e);
                            }
                        }
                    }

                    @Override
                    public void cancel() {
                        run = false;
                    }
                });

        /**
         * todo: 计算10秒内总体统计数据
         */
        // todo 4: 用户和
        // todo 5: 设备和

        // 10秒内访问总次数
        source.flatMap(new RichFlatMapFunction<AppUsageLogVo, Tuple2<AppUsageLogVo, AppUsageAllStatsVo>>() {
                    MapState<String, Integer> userSumStat;


                    @Override
                    public void open(Configuration configuration) throws Exception {
                        MapStateDescriptor<String, Integer> userMap = new MapStateDescriptor<String, Integer>(
                                "user map", Types.STRING, Types.INT);
                        userSumStat = getRuntimeContext().getMapState(userMap);
                    }

                    @Override
                    public void flatMap(AppUsageLogVo value, Collector<Tuple2<AppUsageLogVo, AppUsageAllStatsVo>> out) throws Exception {
                        AppUsageAllStatsVo statsVo = AppUsageAllStatsVo.builder()
                                .totalCount(1L)
                                .totalFlowBytes(value.getFlowBytes())
                                .totalDurationSecs((value.getEndTime() - value.getStartTime()) / 1000L)
                                .build();
                        out.collect(new Tuple2<>(value, statsVo));
                    }
                }).assignTimestampsAndWatermarks(WatermarkStrategy.forBoundedOutOfOrderness(Duration.ofSeconds(10)))
                .windowAll(TumblingEventTimeWindows.of(Time.seconds(10)))
                .aggregate(new AggregateFunction<Tuple2<AppUsageLogVo, AppUsageAllStatsVo>, AppUsageAllStatsVo, AppUsageAllStatsVo>() {
                    @Override
                    public AppUsageAllStatsVo createAccumulator() {
                        return null;
                    }

                    @Override
                    public Object add(Tuple2<AppUsageLogVo, AppUsageAllStatsVo> value, Object accumulator) {
                        return null;
                    }

                    @Override
                    public Object getResult(Object accumulator) {
                        return null;
                    }

                    @Override
                    public Object merge(Object a, Object b) {
                        return null;
                    }
                })
                .sum(1).addSink(new SinkFunction<Tuple2<AppUsageLogVo, AppUsageAllStatsVo>>() {
                    @Override
                    public void invoke(Tuple2<AppUsageLogVo, AppUsageAllStatsVo> value, Context context) throws Exception {
                        System.out.println("sum in 10 seconds, count: " + value.f1);
                        System.out.println("sum in 10 seconds, flow bytes: " + value.f2);
                        System.out.println("sum in 10 seconds, duration millis: " + value.f3);
                    }
                });


        /**
         * todo: 计算10秒内按照app分类的统计数据
         */
        // todo 1: 访问总次数
        // todo 2: 流量和
        // todo 3： 总时长
        // todo 4: 用户和
        // todo 5: 设备和

        /**
         * todo: 计算10秒内的app排名信息
         * 访问次数，流量，时长
         */

        /**
         * todo: 计算10秒内的用户排名信息
         * 访问次数，流量，时长
         */

        /**
         * todo: 10秒内设备排名信息
         * 访问次数，流量，时长
         */
        env.execute("haha");
    }
}
