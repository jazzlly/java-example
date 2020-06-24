package com.leetcode.tree;

/**
 * 404. 左叶子之和
 * 计算给定二叉树的所有左叶子之和。
 *
 * 示例：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
 */
public class L404SumLeftLeaf {
    int sum = 0;

    public int sumOfLeftLeaves(TreeNode root) {
        recursion(root, false);
        return sum;
    }

    private void recursion(TreeNode node, boolean isLeft) {
        if (node == null) {
            return;
        }

        if (isLeft && node.left == null && node.right == null) {
            sum += node.val;
            return;
        }

        recursion(node.left, true);
        recursion(node.right, false);
    }
}
