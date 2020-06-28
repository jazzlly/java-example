package com.leetcode.tree;

public class MO28SymetricTree {

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        return recursion(root.left, root.right);
    }

    private boolean recursion(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }

        if (left == null || right == null) {
            return false;
        }

        if (left.val != right.val) {
            return false;
        }

        return recursion(left.left, right.right) &&
                recursion(left.right, right.left);
    }
}
