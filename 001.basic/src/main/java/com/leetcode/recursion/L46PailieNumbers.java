package com.leetcode.recursion;

import java.util.*;

public class L46PailieNumbers {

    private List<List<Integer>> result = new ArrayList<>();

    int[] numbers;

    public List<List<Integer>> permute(int[] nums) {
        if (nums == null) {
            return result;
        }

        numbers = nums;
        Set<Integer> candidates = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            candidates.add(nums[i]);
        }

        recursion(candidates, new LinkedList<>());
        return result;
    }

    void recursion(Set<Integer> candidate, LinkedList<Integer> currentResult) {
        if (currentResult.size() == numbers.length) {
            result.add(new ArrayList<>(currentResult));
            return;
        }

        for (int i: candidate) {
            currentResult.add(i);
            Set<Integer> tmp = new HashSet<>(candidate);
            tmp.remove(i);

            recursion(tmp, currentResult);

            currentResult.removeLast();
        }

    }
}
