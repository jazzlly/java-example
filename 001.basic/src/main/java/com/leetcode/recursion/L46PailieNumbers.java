package com.leetcode.recursion;

import java.util.*;

public class L46PailieNumbers {

    private List<List<Integer>> result = new ArrayList<>();

    int[] numbers;

    public List<List<Integer>> permute1(int[] nums) {
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

    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        Set<Integer> remaining = new HashSet<>();
        for (int num : nums) {
            remaining.add(num);
        }
        List<Integer> current = new ArrayList<>();
        recursion(remaining, current, ret, nums);

        return ret;
    }

    void recursion(Set<Integer> candidate, List<Integer> current,
                   List<List<Integer>> ret, int[] nums) {
        if (current.size() == nums.length) {
            ret.add(new ArrayList<>(current));
            return;
        }

        for (Integer integer : candidate) {
            current.add(integer);
            Set<Integer> tmp = new HashSet<>(candidate);
            tmp.remove(integer);
            recursion(tmp, current, ret, nums);

            // 恢复状态
            current.remove(current.size() - 1);
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return ans;
        }
        recursion(nums, new LinkedList<>(), ans);
        return ans;
    }

    void recursion(int[] nums, LinkedList<Integer> path, List<List<Integer>> ans) {
        if (path.size() == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }

        Set<Integer> tmp = new HashSet<>(path);
        for (int i = 0; i < nums.length; i++) {
            if (tmp.contains(nums[i])) {
                continue;
            }
            path.addLast(nums[i]);
            recursion(nums, path, ans);
            path.removeLast();
        }
    }
}
