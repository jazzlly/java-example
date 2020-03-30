package com.leetcode.search;

import com.leetcode.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class BfsTmpl {
    Queue<TreeNode> queue = new LinkedList<>();
    void bfs(TreeNode node) {
        if (node == null) {
            return;
        }
        queue.add(node);
        while (!queue.isEmpty()) {
            TreeNode tmp = queue.poll();
            System.out.println(tmp.val);
            if (tmp.left != null) {
                queue.add(tmp.left);
            }
            if (tmp.right != null) {
                queue.add(tmp.right);
            }
        }
    }

    public static void main(String[] args) {
        BfsTmpl t = new BfsTmpl();

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

        t.bfs(root);
    }
}
