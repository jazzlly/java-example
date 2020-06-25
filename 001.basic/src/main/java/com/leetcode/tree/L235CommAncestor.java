package com.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉搜索树中。
 */
public class L235CommAncestor {
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
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
            if (node.val > Math.max(p.val, q.val)) {
                node = node.left;
            }else if (node.val < Math.min(p.val, q.val)) {
                node = node.right;
            } else {
                break;
            }
        }
        return node;
    }


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> path1 = new ArrayList<>();
        List<TreeNode> path2 = new ArrayList<>();

        search(root, p, path1);
        search(root, q, path2);

        TreeNode comm = null;
        for (int i = 0; i < Math.min(path1.size(), path2.size()); i++) {
            if (path1.get(i) == path2.get(i)) {
                comm = path1.get(i);
            } else {
                break;
            }
        }

        return comm;
    }

    void search(TreeNode node, TreeNode target, List<TreeNode> path) {
        if (node == null) {
            return;
        }

        path.add(node);
        if (node.val == target.val) {
            return;
        }

        if (target.val < node.val) {
            search(node.left, target, path);
        } else {
            search(node.right, target, path);
        }
    }
}
