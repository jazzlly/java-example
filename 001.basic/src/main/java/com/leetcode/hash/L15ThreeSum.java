package com.leetcode.hash;

import java.util.*;

/**
 * 15. 三数之和
 * 给定一个包含n个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
 * 使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 * 示例：
 *
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 */
public class L15ThreeSum {

    // 一遍循环，头尾收缩！
    public static List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length <= 2) {
            return Collections.emptyList();
        }

        Arrays.sort(nums);

        Set<List<Integer>> result = new LinkedHashSet<>();
        for (int i = 0; i < nums.length - 2; i++) {
            int head = i + 1;
            int tail = nums.length - 1;
            while (tail > head) {
                int sum = nums[i] + nums[head] + nums[tail];
                if (0 == sum) {
                    // nums[i], nums[head], nums[tail] 已经从小到大排序了
                    result.add(Arrays.asList(
                            nums[i], nums[head], nums[tail]));
                    // 两个指针同时移动
                    head++;
                    tail--;
                } else if (sum > 0) {
                    tail--;
                } else {
                    head++;
                }
            }
        }

        return new ArrayList<>(result);
    }

    public static List<List<Integer>> threeSum1(int[] nums) {
        if (nums == null || nums.length <= 2) {
            return Collections.emptyList();
        }

        Set<List<Integer>> result = new LinkedHashSet<>();

        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Set<Integer> set = null;
            if (map.containsKey(nums[i])) {
                set = map.get(nums[i]);
                set.add(i);
            } else {
                set = new HashSet<>();
                set.add(i);
                map.put(nums[i], set);
            }
        }

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int a = nums[i];
                int b = nums[j];
                int c = -(a + b);

                Set<Integer> set = map.get(c);
                if (set == null) {
                    continue;
                }
                Set<Integer> tmp = new HashSet<>(set);
                tmp.remove(i);
                tmp.remove(j);
                if (tmp.size() == 0) {
                    continue;
                }

                List<Integer> list = Arrays.asList(a, b, c);
                list.sort(Comparator.naturalOrder());
                result.add(list);
            }
        }

        return new ArrayList<>(result);
    }
}
