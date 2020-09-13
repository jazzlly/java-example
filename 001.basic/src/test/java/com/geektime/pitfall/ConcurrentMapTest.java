package com.geektime.pitfall;

import org.junit.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;

public class ConcurrentMapTest {
    @Test
    public void smoke() {
        Map<String, LongAdder> map = new ConcurrentHashMap<>();
        map.computeIfAbsent("foo", s -> new LongAdder()).increment();
        System.out.println(map.get("foo").longValue());
        map.computeIfAbsent("foo", s -> new LongAdder()).increment();
        System.out.println(map.get("foo").longValue());

        map.entrySet().stream().collect(
                Collectors.toMap(e -> e.getKey(), e -> e.getValue().longValue()));

    }
}
