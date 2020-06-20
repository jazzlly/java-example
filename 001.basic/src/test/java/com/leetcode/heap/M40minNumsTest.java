package com.leetcode.heap;

import org.junit.Test;

import java.util.NavigableSet;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class M40minNumsTest {

    @Test
    public void smoke() {
        PriorityQueue<Integer> queue = new PriorityQueue<>(
                (o1, o2) -> o2 - o1);

        queue.add(8);
        queue.add(6);
        queue.add(5);
        queue.add(9);

        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }

    @Test
    public void smoke2() {
        TreeMap<Integer, Integer> map =
                new TreeMap<>((o1, o2) -> o2 - o1);
        map.put(5, 1);
        map.put(6, 1);
        map.put(3, 1);
        map.put(9, 1);

        System.out.println(map.firstKey());

        NavigableSet<Integer> set = map.navigableKeySet();
        for (Integer integer : set) {
            System.out.println(integer);
        }
    }
}