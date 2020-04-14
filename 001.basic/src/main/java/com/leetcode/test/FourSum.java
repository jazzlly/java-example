package com.leetcode.test;

public class FourSum {

    /**
     * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，
     * 判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？
     * 找出所有满足条件且不重复的四元组。
     *
     * 注意：
     *
     * 答案中不可以包含重复的四元组。
     *
     * 示例：
     *
     * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
     *
     * -2, -1, 0, 0, 1, 2
     *
     *
     * 满足要求的四元组集合为：
     * [
     *   [-1,  0, 0, 1],
     *   [-2, -1, 1, 2],
     *   [-2,  0, 0, 2]
     * ]
     */
//    Map<Integer, Integer> twoSumMap = new HashMap<>();
//
//    public List<List<Integer>> fourSum(int[] nums, int target) {
//        List<List<Integer>> ans = new ArrayList<>(); // todo: 考虑不重复的四元组
//        Set<Set<Integer>> setAns = new HashSet<>();
//
//        if (nums == null || nums.length == 0) {
//            return ans;
//        }
//
//        Arrays.sort(nums); // todo: nums排过序，考虑优化
//
//        for (int i = 0; i < nums.length; i++) {
//            twoSumMap.put(nums[i], i);
//        }
//
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = i; j < nums.length; j++) {
//                for (int k = 0; k < nums.length; k++) {
//                    int a = nums[i];
//                    int b = nums[j];
//                    int c = nums[k];
//
//                    int complement = target - a - b -c;
//                    if (twoSumMap.containsKey(complement) && twoSumMap.get(complement) != i) {
//                        return new int[] { i, twoSumMap.get(complement) };
//                    }
//
//                }
//
//
//            }
//        }
//
//
//
//        return ans;
//    }
//
//
//    public int[] twoSum(int[] nums, int target) {
//
//        for (int i = 0; i < nums.length; i++) {
//            int complement = target - nums[i];
//            if (twoSumMap.containsKey(complement) && twoSumMap.get(complement) != i) {
//                return new int[] { i, twoSumMap.get(complement) };
//            }
//        }
//        throw new IllegalArgumentException("No two sum solution");
//    }

}
