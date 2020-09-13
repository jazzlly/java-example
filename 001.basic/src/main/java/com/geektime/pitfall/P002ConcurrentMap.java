package com.geektime.pitfall;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class P002ConcurrentMap {

    //线程个数
    private static int THREAD_COUNT = 10;
    //总元素数量
    private static int ITEM_COUNT = 1000;

    //帮助方法，用来获得一个指定元素数量模拟数据的ConcurrentHashMap
    public ConcurrentHashMap<String, Long> getData(int count) {
        return LongStream.rangeClosed(1, count)
                .boxed()
                .collect(Collectors.toConcurrentMap(i -> UUID.randomUUID().toString(), Function.identity(),
                        (o1, o2) -> o1, ConcurrentHashMap::new));
    }

    public String wrong() throws InterruptedException {
        ConcurrentHashMap<String, Long> concurrentHashMap = getData(ITEM_COUNT - 100);

        //初始900个元素
        System.out.println("init size: " + concurrentHashMap.size());

        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
        //使用线程池并发处理逻辑
        forkJoinPool.execute(() -> IntStream.rangeClosed(1, 10).parallel().forEach(i -> {
            synchronized (concurrentHashMap) {
                //查询还需要补充多少个元素
                int gap = ITEM_COUNT - concurrentHashMap.size();
                System.out.println("gap size: " + gap);
                //补充元素
                concurrentHashMap.putAll(getData(gap));
            }
        }));
        //等待所有任务完成
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
        //最后元素个数会是1000吗？
        System.out.println("finish size:{}" + concurrentHashMap.size());
        return "OK";
    }

    public static void main(String[] args) throws InterruptedException {

        ConcurrentHashMap<String, Long> map = LongStream.rangeClosed(1, 5).boxed()
                .collect(Collectors.toConcurrentMap(
                    i -> UUID.randomUUID().toString(), Function.identity(),
                        (o1, o2) -> o1, ConcurrentHashMap::new));
        System.out.println(map.toString());

        System.out.println(
            LongStream.rangeClosed(1, 5).boxed().collect(Collectors.toList()));

        P002ConcurrentMap test = new P002ConcurrentMap();
        test.wrong();
    }
}
