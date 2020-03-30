package com.leetcode.search;

import com.leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class DfsTemplate {

    List<TreeNode> visited = new ArrayList<>();

    void dfs(TreeNode node) {
        if (node == null) {
            return;
        }

        visited.add(node);
        dfs(node.left);
        dfs(node.right);
    }

    public static void main(String[] args) {
        DfsTemplate t = new DfsTemplate();

        TreeNode root = new TreeNode(1);
        TreeNode l11 = new TreeNode(2);
        TreeNode l12 = new TreeNode(3);
        TreeNode l21 = new TreeNode(4);
        TreeNode l22 = new TreeNode(5);
        TreeNode l23 = new TreeNode(6);
        TreeNode l24 = new TreeNode(7);

        root.left = l11;
        root.right = l12;
        l11.left = l21;
        l11.right = l22;
        l12.left = l23;
        l12.right = l24;

        t.dfs(root);
        t.visited.forEach(node -> System.out.println(node.toString()));

    }
}
