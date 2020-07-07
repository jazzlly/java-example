package com.leetcode.tree;

import java.util.Comparator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * 654. 最大二叉树
 * 给定一个不含重复元素的整数数组。一个以此数组构建的最大二叉树定义如下：
 *
 * 二叉树的根是数组中的最大元素。
 * 左子树是通过数组中最大值左边部分构造出的最大二叉树。
 * 右子树是通过数组中最大值右边部分构造出的最大二叉树。
 * 通过给定的数组构建最大二叉树，并且输出这个树的根节点。
 *
 *
 *
 * 示例 ：
 *
 * 输入：[3,2,1,6,0,5]
 * 输出：返回下面这棵树的根节点：
 *
 *       6
 *     /   \
 *    3     5
 *     \    /
 *      2  0
 *        \
 *         1
 */

// fixme: todo sort?
public class L654MaxBinaryTree {

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        TreeNode node = null;
        for (int i = 0; i < nums.length; i++) {
            node = recursion(node, nums[i]);
        }
        return node;
    }

    private TreeNode recursion(TreeNode current, int numNext) {
        TreeNode next = new TreeNode(numNext);
        if (current == null) {
            return next;
        }
        if (numNext > current.val) {
            next.left = current;
            return next;
        } else {
            current.right = recursion(current.right, numNext);
            return current;
        }
    }
}
