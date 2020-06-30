package com.leetcode.tree.middle;

import com.leetcode.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 114. 二叉树展开为链表
 * 给定一个二叉树，原地将它展开为一个单链表。
 *
 *
 *
 * 例如，给定二叉树
 *
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 *
 * 将其展开为：
 *
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 */
public class L114TreeFlattern {

//    Queue<TreeNode> queue = new LinkedList<>();
//
//    public void flatten1(TreeNode root) {
//        recursion(root);
//
//        TreeNode prev = null;
//        while (!queue.isEmpty()) {
//            TreeNode node = queue.poll();
//            node.left = null;
//            node.right = null;
//
//            if (prev == null) {
//                // 第一个节点
//                prev = node;
//            } else {
//                prev.right = node;
//                prev = node;
//            }
//        }
//    }
//
//    private void recursion(TreeNode node) {
//        if (node == null) {
//            return;
//        }
//
//        queue.add(node);
//        recursion(node.left);
//        recursion(node.right);
//    }

    private TreeNode next = null;

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        flatten(root.right);
        flatten(root.left);

        root.right = next;
        root.left = null;
        next = root;
    }
}
