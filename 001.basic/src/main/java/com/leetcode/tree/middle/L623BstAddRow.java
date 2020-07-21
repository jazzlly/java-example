package com.leetcode.tree.middle;

import com.leetcode.tree.TreeNode;
import com.leetcode.tree.utils.TreeUtils;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 623. 在二叉树中增加一行
 * 给定一个二叉树，根节点为第1层，深度为 1。在其第 d 层追加一行值为 v 的节点。
 *
 * 添加规则：给定一个深度值 d （正整数），针对深度为 d-1 层的每一非空节点 N，为 N 创建两个值为 v 的左子树和右子树。
 *
 * 将 N 原先的左子树，连接为新节点 v 的左子树；将 N 原先的右子树，连接为新节点 v 的右子树。
 *
 * 如果 d 的值为 1，深度 d - 1 不存在，则创建一个新的根节点 v，原先的整棵树将作为 v 的左子树。
 *
 * 示例 1:
 *
 * 输入:
 * 二叉树如下所示:
 *        4
 *      /   \
 *     2     6
 *    / \   /
 *   3   1 5
 *
 * v = 1
 *
 * d = 2
 *
 * 输出:
 *        4
 *       / \
 *      1   1
 *     /     \
 *    2       6
 *   / \     /
 *  3   1   5
 *
 * 示例 2:
 *
 * 输入:
 * 二叉树如下所示:
 *       4
 *      /
 *     2
 *    / \
 *   3   1
 *
 * v = 1
 *
 * d = 3
 *
 * 输出:
 *       4
 *      /
 *     2
 *    / \
 *   1   1
 *  /     \
 * 3       1
 * 注意:
 *
 * 输入的深度值 d 的范围是：[1，二叉树最大深度 + 1]。
 * 输入的二叉树至少有一个节点。
 */
public class L623BstAddRow {

    // bfs
    public TreeNode addOneRow1(TreeNode root, int v, int d) {
        if (d == 1) {
            TreeNode node = new TreeNode(v);
            node.left = root;
            return node;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int level = 1;
        while (!queue.isEmpty()) {
            if (level > d - 1) {
                break;
            }

            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                if (level == (d - 1)) {
                    TreeNode left = new TreeNode(v);
                    TreeNode right = new TreeNode(v);
                    left.left = node.left;
                    right.right = node.right;
                    node.left = left;
                    node.right = right;
                }

                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            level++;
        }
        return root;
    }

    // dfs
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (d == 1) {
            TreeNode node = new TreeNode(v);
            node.left = root;
            return node;
        }

        dfsRecursion(root, 1, v, d);
        return root;
    }

    private void dfsRecursion(TreeNode node, int level, int v, int d) {
        if (node == null || level > d - 1) {
            return;
        }

        if (level == (d - 1)) {
            TreeNode left = new TreeNode(v);
            TreeNode right = new TreeNode(v);
            left.left = node.left;
            right.right = node.right;
            node.left = left;
            node.right = right;
            return;
        }

        dfsRecursion(node.left, level+1, v, d);
        dfsRecursion(node.right, level+1, v, d);
    }

    public static void main(String[] args) {
        TreeNode root = TreeUtils.makeBinaryTree(4, 2, 6, 3, 1, 5);
        L623BstAddRow test = new L623BstAddRow();
        TreeNode node = test.addOneRow(
                TreeUtils.makeBinaryTree(4, 2, 6, 3, 1, 5),
                -1, 2);

        System.out.println("haha");

        TreeNode node1 = test.addOneRow(
                TreeUtils.makeBinaryTree(4, 2, 6, 3, 1, 5),
                -1, 1);
        System.out.println("haha");

        TreeNode node2 = test.addOneRow(
                TreeUtils.makeBinaryTree(4, 2, 6, 3, 1, 5),
                -1, 3);
        System.out.println("hahaha");

    }
}
