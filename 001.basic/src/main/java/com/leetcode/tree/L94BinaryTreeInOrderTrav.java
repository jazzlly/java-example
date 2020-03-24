package com.leetcode.tree;

import java.util.*;

/**
 * 94. 二叉树的中序遍历
 * 给定一个二叉树，返回它的中序 遍历。
 *
 * 示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 */
public class L94BinaryTreeInOrderTrav {
    private List<Integer> result = new ArrayList<>();

    public List<Integer> inorderTraversal1(TreeNode root) {
        recursion(root);
        return result;
    }

    void recursion(TreeNode node) {
        if (node == null) {
            return;
        }
        recursion(node.left);
        result.add(node.val);
        recursion(node.right);
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        TreeNode node = root;
        Stack<TreeNode> stack = new Stack<>();
        Set<TreeNode> nodeSet = new HashSet<>();

        while (node != null) {
            while (!nodeSet.contains(node) && node.left != null) {
                stack.push(node);
                nodeSet.add(node);
                node = node.left;
            }
            result.add(node.val);

            if (node.right != null) {
                node = node.right;
                continue;
            }

            if (stack.isEmpty()) {
                break;
            }

            node = stack.pop();
        }
        return result;
    }

}
