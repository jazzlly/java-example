package com.leetcode.division;

import java.util.ArrayList;
import java.util.List;

public class L78Subset {

    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        if (nums == null || nums.length == 0) {
            return ans;
        }

        dfs(new ArrayList<>(), nums, 0);
        return ans;
    }

    void dfs(List<Integer> list, int[] nums, int index) {
        if (index >=  nums.length) {
            // System.out.println("add list: " + list);
            ans.add(new ArrayList<>(list));
            return;
        }

        // 不选择本层index, 直接跳到下一层
        dfs(list, nums, index + 1);

        // 选择本层index, 然后跳到下一层
        list.add(nums[index]);
        dfs(list, nums, index + 1);

        // 回退到上一层前清理到本层数据
        list.remove(list.size() - 1);
    }

    L78Subset reset() {
        ans = new ArrayList<>();
        return this;
    }
}
