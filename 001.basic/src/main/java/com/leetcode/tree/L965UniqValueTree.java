package com.leetcode.tree;

/**
 * 965. 单值二叉树
 * 如果二叉树每个节点都具有相同的值，那么该二叉树就是单值二叉树。
 *
 * 只有给定的树是单值二叉树时，才返回 true；否则返回 false。
 */
public class L965UniqValueTree {
    Integer first = null;

    public boolean isUnivalTree(TreeNode root) {
        if (root == null) {
            return true;
        }

        if (first == null) {
            first = root.val;
        }

        if (!first.equals(root.val)) {
            return false;
        }

        return isUnivalTree(root.left) && isUnivalTree(root.right);
    }
}
