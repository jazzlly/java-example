package com.leetcode.tree;

/**
 * 270. 最接近的二叉搜索树值
 * 给定一个不为空的二叉搜索树和一个目标值 target，请在该二叉搜索树中找到最接近目标值 target 的数值。
 *
 * 注意：
 *
 * 给定的目标值 target 是一个浮点数
 * 题目保证在该二叉搜索树中只会存在一个最接近目标值的数
 * 示例：
 *
 * 输入: root = [4,2,5,1,3]，目标值 target = 3.714286
 *
 *     4
 *    / \
 *   2   5
 *  / \
 * 1   3
 *
 * 输出: 4
 */
public class L270CloseDiffBst {
    double minDelta = Double.MAX_VALUE;
    TreeNode result = null;

    public int closestValue(TreeNode root, double target) {
        recursion(root, target);
        return result.val;
    }

    private void recursion(TreeNode node, double target) {
        if (node == null) {
            return;
        }

        double delta = Math.abs(target - node.val);
        if (delta < minDelta) {
            minDelta = delta;
            result = node;
        }

        if (target < node.val) {
            recursion(node.left, target);
        }

        if (target > node.val) {
            recursion(node.right, target);
        }
    }


}
