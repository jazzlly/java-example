package com.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 257. 二叉树的所有路径
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 *
 * 输入:
 *
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 *
 * 输出: ["1->2->5", "1->3"]
 *
 * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 */
public class L257BinaryTreePath {

    List<String> result = new ArrayList<>();
    public List<String> binaryTreePaths1(TreeNode root) {
        if (root == null) {
            return result;
        }

        recursion(root, root.val + "");
        return result;
    }

    private void recursion(TreeNode node, String path) {
        if (node.left == null && node.right == null) {
            result.add(path);
            return;
        }

        if (node.left != null) {
            recursion(node.left, path + "->" + node.left.val);
        }
        if (node.right != null) {
            recursion(node.right, path + "->" +node.right.val);
        }
    }


    public List<String> binaryTreePaths(TreeNode root) {
        List<String> rst = new ArrayList<>();
        if (root == null) {
            return rst;
        }

        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root, root.val + ""));

        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            TreeNode node = pair.node;

            // 叶子节点
            if (node.left == null && node.right == null) {
                rst.add(pair.path);
                continue;
            }

            // 非叶子节点
            if (node.left != null) {
                Pair pair1 = new Pair(node.left, pair.path + "->" + node.left.val);
                queue.add(pair1);
            }
            if (node.right != null) {
                Pair pair2 = new Pair(node.right, pair.path + "->" + node.right.val);
                queue.add(pair2);
            }
        }

        return rst;
    }

    static class Pair {
        TreeNode node;
        String path;

        public Pair(TreeNode node, String path) {
            this.node = node;
            this.path = path;
        }
    }
}
