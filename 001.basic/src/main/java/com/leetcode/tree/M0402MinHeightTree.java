package com.leetcode.tree;

/**
 * 面试题 04.02. 最小高度树
 * 给定一个有序整数数组，元素各不相同且按升序排列，编写一个算法，创建一棵高度最小的二叉搜索树。
 *
 * 示例:
 * 给定有序数组: [-10,-3,0,5,9],
 *
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 *
 *           0
 *          / \
 *        -3   9
 *        /   /
 *      -10  5
 */
public class M0402MinHeightTree {
    public TreeNode sortedArrayToBST(int[] nums) {
        return recursion(nums, 0, nums.length);
    }

    /**
     * 找到num.len/2, 为root节点， 递归左子树， 递归右子树
     * @param nums
     * @param begin inclusive
     * @param end exclusive
     * @return 中间节点
     */
    TreeNode recursion(int[] nums, int begin, int end) {
        if (begin >= end || begin < 0 || end > nums.length) {
            return null;
        }

        int midIndex = begin + (end - begin) / 2;
        TreeNode mid = new TreeNode(nums[midIndex]);
        mid.left = recursion(nums, begin, midIndex);
        mid.right = recursion(nums, midIndex + 1, end);

        return mid;
    }
}
