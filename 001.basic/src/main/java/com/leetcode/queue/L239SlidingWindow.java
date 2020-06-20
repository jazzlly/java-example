package com.leetcode.queue;

import java.util.*;

/**
 * 239. 滑动窗口最大值
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 *
 * 返回滑动窗口中的最大值。
 *
 * 示例:
 *
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 */
public class L239SlidingWindow {

    /**
     * 执行用时 : 102 ms, 在所有 Java 提交中击败了5.10%的用户
     * 内存消耗 :47.5 MB, 在所有 Java 提交中击败了5.03%的用户
     */
    public static int[] maxSlidingWindow1(int[] nums, int k) {
        if (k == 0 || nums.length == 0) {
            return new int[]{};
        }

        int[] result = new int[nums.length - k + 1];

        // max heap
        PriorityQueue<Integer> queue =
                new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < nums.length; i++) {
            queue.offer(nums[i]);

            if (i + 1 >= k) {
                result[i + 1 - k] = queue.peek();
                queue.remove(nums[i + 1 - k]);
            }
        }
        return result;
    }

    /**
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindowDqueue2(int[] nums, int k) {
        if (k == 0 || nums.length == 0) {
            return new int[]{};
        }

        int[] result = new int[nums.length - k + 1];
        // 保存入队数字再nums中索引
        Deque<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < nums.length; i++) {
            while (queue.peekLast() != null &&
                    nums[i] > nums[queue.peekLast()]) {
                queue.pollLast();
            }

            queue.offer(i); // 将索引入队

            if (i + 1 >= k) {
                int peek = queue.peek();
                result[i + 1 - k] = nums[peek];
                if (i + 1 == peek + k) {
                    queue.poll();
                }
            }
        }
        return result;
    }

    /**
     * 单调队列解法
     * 所谓单调队列，就是一个双端队列，队列数据按照降序排列
     * 队列头是最大值
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] ret = new int[nums.length - k + 1];

        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            while (deque.peekLast() != null && nums[i] > deque.peekLast()) {
                deque.pollLast();
            }
            deque.addLast(nums[i]);

            int prevIndex = i - k + 1;
            if (prevIndex >= 0) {
                ret[prevIndex] = deque.getFirst(); // 获取最大值
                if (deque.getFirst().equals(nums[prevIndex])) {
                    deque.pollFirst();
                }
            }
        }
        return ret;
    }


























}
