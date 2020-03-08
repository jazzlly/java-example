package com.leetcode.tree;

/**
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉搜索树中。
 */
public class L235CommAncestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) {
            return null;
        }

        int max = Math.max(p.val, q.val);
        int min = Math.min(p.val, q.val);
        if (root.val >= min && root.val <= max) {
            return root;
        }

        if (root.val > max) {
            return lowestCommonAncestor(root.left, p, q);
        }

        if (root.val < min) {
            return lowestCommonAncestor(root.right, p, q);
        }

        return null;
    }

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode node = root;
        while (node != null) {
            if (node.val > p.val && node.val > q.val) {
                node = node.left;
            }else if (node.val < p.val && node.val < q.val) {
                node = node.right;
            } else {
                break;
            }
        }
        return node;
    }

    public void appendLeftChild(TreeNode node, TreeNode left) {
        node.left = left;
    }

    public void appendRightChild(TreeNode node, TreeNode right) {
        node.right = right;
    }
}
