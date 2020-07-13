package com.leetcode.tree.middle;

import com.leetcode.tree.TreeNode;
import com.leetcode.tree.utils.TreeUtils;

import java.util.*;

/**
 * 513. 找树左下角的值
 * 给定一个二叉树，在树的最后一行找到最左边的值。
 *
 * 示例 1:
 * 输入:
 *
 *     2
 *    / \
 *   1   3
 *
 * 输出:
 * 1
 *
 *
 * 示例 2:
 * 输入:
 *
 *         1
 *        / \
 *       2   3
 *      /   / \
 *     4   5   6
 *        /
 *       7
 *
 * 输出:
 * 7
 *
 * 注意: 您可以假设树（即给定的根节点）不为 NULL。
 */
public class L513LeftMostValue {
    public int findBottomLeftValue1(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean hasChildren = false;
            int first = 0;

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == 0) {
                    first = node.val;
                }

                if (node.left != null) {
                    hasChildren = true;
                    queue.add(node.left);
                }
                if (node.right != null) {
                    hasChildren = true;
                    queue.add(node.right);
                }
            }

            if (!hasChildren) {
                return first;
            }
        }

        throw new IllegalStateException("not got hear!");
    }

    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int ans = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            ans = node.val;

            if (node.right != null) {
                queue.add(node.right);
            }
            if (node.left != null) {
                queue.add(node.left);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        TreeNode root = TreeUtils.makeBinaryTree(Arrays.asList(
                1, 2, 3, 4, null, 5, 6, null, null, null, null, 7));
        L513LeftMostValue test = new L513LeftMostValue();
        System.out.println(test.findBottomLeftValue(root));
    }
}
