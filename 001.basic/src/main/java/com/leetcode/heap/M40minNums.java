package com.leetcode.heap;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * 输入整数数组 arr ，找出其中最小的 k 个数。
 *
 * 例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 *
 * 示例 1：
 * 输入：arr = [3,2,1], k = 2
 * 输出：[1,2] 或者 [2,1]
 * 示例 2：
 *
 * 输入：arr = [0,1,2,1], k = 1
 * 输出：[0]
 *  
 * 限制：
 *
 * 0 <= k <= arr.length <= 10000
 * 0 <= arr[i] <= 10000
 */
public class M40minNums {

    /**
     * 使用优先队列实现
     * @param arr
     * @param k
     * @return
     */
    public int[] getLeastNumbers1(int[] arr, int k) {
        if (k == 0) {
            return new int[0];
        }

        // 创建大顶堆
        PriorityQueue<Integer> queue =
                new PriorityQueue<>((o1, o2) -> o2 - o1);

        for (int i = 0; i < arr.length; i++) {
            if (queue.size() < k) {
                queue.add(arr[i]);
                continue;
            }

            // 队列已经有k个数了
            if (arr[i] >= queue.peek()) {
                continue;
            }

            queue.poll();
            queue.add(arr[i]);
        }

        int[] ret = new int[queue.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = queue.poll();
        }
        return ret;
    }


    /**
     * 使用tree map实现
     * @param arr
     * @param k
     * @return
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0) {
            return new int[0];
        }

        // 创建大顶堆
        TreeMap<Integer, Integer> map =
                new TreeMap<>((o1, o2) -> o2 - o1);
        int size = 0;
        for (int i = 0; i < arr.length; i++) {
            if (size < k) {
                size++;
                map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
                continue;
            }

            // 队列已经有k个数了
            if (arr[i] >= map.firstKey()) {
                continue;
            }

            Map.Entry<Integer, Integer> first = map.firstEntry();
            if (first.getValue().equals(1)) {
                map.pollFirstEntry();
            } else {
                map.put(first.getKey(), first.getValue() - 1);
            }

            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }

        int[] ret = new int[k];
        int c = 0;
        while (!map.isEmpty()) {
            Map.Entry<Integer, Integer> entry = map.pollFirstEntry();
            int value = entry.getValue();
            while (value-- > 0) {
                ret[c++] = entry.getKey();
            }
        }

        return ret;
    }
}
