package com.leetcode.tree;

/**
 * 559. N叉树的最大深度
 * 给定一个 N 叉树，找到其最大深度。
 *
 * 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
 */
public class L559NTreeDepth {

    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }

        int max = 0;
        if (root.children != null && root.children.size() > 0) {
            for (Node child : root.children) {
                max = Math.max(max, maxDepth(child));
            }
        }

        return 1 + max;
    }
}
