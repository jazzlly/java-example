package com.leetcode.tree;

/**
 * 1022. 从根到叶的二进制数之和
 * 给出一棵二叉树，其上每个结点的值都是 0 或 1 。每一条从根到叶的路径都代表一
 * 个从最高有效位开始的二进制数。例如，如果路径为 0 -> 1 -> 1 -> 0 -> 1，
 * 那么它表示二进制数 01101，也就是 13 。
 *
 * 对树上的每一片叶子，我们都要找出从根到该叶子的路径所表示的数字。
 *
 * 以 10^9 + 7 为模，返回这些数字之和。
 */
public class L1022BinaryValueSum {

    int sum = 0;
    public int sumRootToLeaf(TreeNode root) {
        recursion(root, 0);
        return sum;
    }

    private void recursion(TreeNode node, int parentPath) {
        if (node == null) {
            return;
        }

        parentPath = (parentPath << 1) + node.val;
        if (node.left == null && node.right == null) {
            sum += parentPath;
            return;
        }

        if (node.left != null) {
            recursion(node.left, parentPath);
        }
        if (node.right != null) {
            recursion(node.right, parentPath);
        }
    }
}
