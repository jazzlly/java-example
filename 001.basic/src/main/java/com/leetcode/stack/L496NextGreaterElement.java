package com.leetcode.stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class L496NextGreaterElement {

    public int[] nextGreaterElement1(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();;

        for (int i = 0; i < nums2.length; i++) {
            while (!stack.isEmpty() && nums2[i] > stack.peek()) {
                map.put(stack.pop(), nums2[i]);
            }

            stack.push(nums2[i]);
        }

        while (!stack.isEmpty()) {
            map.put(stack.pop(), -1);
        }

        int[] ans = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            ans[i] = map.get(nums1[i]);
        }

        return ans;
    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();;

        for (int i = nums2.length - 1; i >= 0 ; i--) {
            while (!stack.isEmpty() && nums2[i] > stack.peek()) {
                stack.pop();
            }

            map.put(nums2[i], stack.isEmpty() ? -1 : stack.peek());
            stack.push(nums2[i]);
        }

        int[] ans = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            ans[i] = map.get(nums1[i]);
        }

        return ans;
    }

    public static void main(String[] args) {
        L496NextGreaterElement test = new L496NextGreaterElement();
        System.out.println(Arrays.toString(test.nextGreaterElement(
                new int[]{1, 3, 5, 2, 4},
                new int[]{6, 5, 4, 3, 2, 1, 7}
        )));

        System.out.println(Arrays.toString(test.nextGreaterElement(
                new int[]{4,1,2},
                new int[]{1,3,4,2}
        )));

        System.out.println(Arrays.toString(test.nextGreaterElement(
                new int[]{2,4},
                new int[]{1,2,3,4}
        )));
        /*
[7, 7, 7, 7, 7]
[-1, 3, -1]
[3, -1]
         */
    }

}
