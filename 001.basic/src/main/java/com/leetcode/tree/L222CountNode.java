package com.leetcode.tree;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 222. 完全二叉树的节点个数
 * 给出一个完全二叉树，求出该树的节点个数。
 *
 * 说明：
 *
 * 完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。
 *
 * 示例:
 *
 * 输入:
 *     1
 *    / \
 *   2   3
 *  / \  /
 * 4  5 6
 *
 * 输出: 6
 */
public class L222CountNode {
    public int countNodes1(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int count = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                count++;

                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }

        return count;
    }

    int maxLevel = 0;
    int leafCount = 0;
    boolean done = false;

    public int countNodes(TreeNode root) {
        recursion(root, 0);
        return (int) (Math.pow(2, maxLevel - 1) + leafCount -1);
    }

    private void recursion(TreeNode node, int level) {
        if (node == null || done) {
            return;
        }

        maxLevel = Math.max(maxLevel, ++level);

        recursion(node.left, level);

        if (node.left != null && node.right == null) {
            done = true;
            return;
        }

        // 叶子节点
        if (node.left == null && node.right == null) {
            if (level < maxLevel) {
                done = true;
                return;
            }

            leafCount++;
            return;
        }

        recursion(node.right, level);
    }

//    public static void main(String[] args) {
//        TreeNode n11 = new TreeNode(1);
//
//        TreeNode n21 = new TreeNode(2);
//        TreeNode n22 = new TreeNode(3);
//        n11.left = n21;
//        n11.right = n22;
//
//        // TreeNode n31 =
//    }
}
