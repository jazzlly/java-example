package com.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 199. 二叉树的右视图
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *
 * 示例:
 *
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1, 3, 4]
 * 解释:
 *
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 * 通过次数51,397提交次数80,524
 */
public class L199RightSideView {
    // BSF
    public List<Integer> rightSideView1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();

        if (root != null) {
            queue.add(root);
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            TreeNode last = null;
            for (int i = 0; i < size; i++) {
                last = queue.poll();
                if (last.left != null) {
                    queue.add(last.left);
                }
                if (last.right != null) {
                    queue.add(last.right);
                }
            }
            result.add(last.val);
        }
        return result;
    }

    // DFS
    int maxLevel = Integer.MIN_VALUE;
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        recusion(root, 0, result);

        return result;
    }

    private void recusion(TreeNode node, int level, List<Integer> result) {
        if (node == null) {
            return;
        }

        if (level > maxLevel) {
            result.add(node.val);
            maxLevel = level;
        }

        recusion(node.right, level + 1, result);
        recusion(node.left, level + 1, result);
    }
}
