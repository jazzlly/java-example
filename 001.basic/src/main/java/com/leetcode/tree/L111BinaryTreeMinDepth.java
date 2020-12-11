package com.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 111. 二叉树的最小深度
 * 给定一个二叉树，找出其最小深度。
 *
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 *
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最小深度  2.
 */
public class L111BinaryTreeMinDepth {

    int minDepth = Integer.MAX_VALUE;
    public int minDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }

        dfs(root, 1);
        return minDepth;
    }

    private void dfs(TreeNode node, int level) {
        if (node == null) {
            return;
        }

        if (node.left == null &&
                node.right == null &&
                level < minDepth) {
            minDepth = level;
        }

        dfs(node.left, level + 1);
        dfs(node.right, level + 1);
    }


    public int minDepth(TreeNode root) {
        int minDepth = 0;

        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
        }

        while(!queue.isEmpty()) {
            int size = queue.size();
            minDepth++;

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left == null && node.right == null) {
                    return minDepth;
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }

        return minDepth;
    }


}
