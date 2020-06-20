package com.leetcode.tree;

/**
 *687. 最长同值路径
 * 给定一个二叉树，找到最长的路径，这个路径中的每个节点具有相同值。 这条路径可以经过也可以不经过根节点。
 * 注意：两个节点之间的路径长度由它们之间的边数表示。
 *
 * 示例 1:
 * 输入:
 *               5
 *              / \
 *             4   5
 *            / \   \
 *           1   1   5
 * 输出: 2
 *
 * 示例 2:
 * 输入:
 *               1
 *              / \
 *             4   5
 *            / \   \
 *           4   4   5
 * 输出:
 *
 * 2
 * 注意: 给定的二叉树不超过10000个结点。 树的高度不超过1000。
 */
public class L687LongCommPath {

    int max = 0;
    public int longestUnivaluePath(TreeNode root) {
        recursion(root);
        return max;
    }

    /*
     *               5
     *              / \
     *             4   5
     *            / \   \
     *           1   1   5
     */
    private Pair recursion(TreeNode node) {
        if (node == null) {
            return null;
        }

        int leftArrow = 0;
        if (node.left != null) {
            Pair pair = recursion(node.left);
            if (node.left.val == node.val) {
                leftArrow = 1 + Math.max(pair.left, pair.right);
            }
        }

        int rightArrow = 0;
        if (node.right != null) {
            Pair pair = recursion(node.right);
            if (node.right.val == node.val) {
                rightArrow = 1 + Math.max(pair.left, pair.right);
            }
        }

        max = Math.max(max, leftArrow + rightArrow);
        return new Pair(leftArrow, rightArrow);
    }

    static class Pair {
        final int left;
        final int right;

        public Pair(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }
}
