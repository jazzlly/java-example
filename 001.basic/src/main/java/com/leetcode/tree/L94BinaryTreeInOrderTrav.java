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

    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        // 保存左子树已经遍历过的节点
        Set<TreeNode> leftChecked = new HashSet<>();

        TreeNode current = root;
        while (current != null) {
            // 遍历左子树
            if (!leftChecked.contains(current) && current.left != null) {
                stack.push(current);
                leftChecked.add(current);
                current = current.left;
                continue;
            }

            // 添加根节点
            ret.add(current.val);
            // leftChecked.add(current);

            // 遍历右子树
            if (current.right != null) {
                current = current.right;
                continue;
            }

            current = stack.empty() ? null : stack.pop();
        }

        return ret;
    }

    public List<Integer> inorderTraversal3(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;

        while (current != null || !stack.empty()) {
            // 左边节点都依次入栈
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            ret.add(current.val);
            current = current.right;
        }

        return ret;
    }

    // 染色算法
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }

        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> colorStack = new Stack<>(); // 0: 新节点, 1: 老节点

        stack.push(root);
        colorStack.push(0);

        while (!stack.empty()) {
            TreeNode node = stack.pop();
            int color = colorStack.pop();
            switch (color) {
                case 0:     // 新节点
                    if (node.right != null) {
                        stack.push(node.right);
                        colorStack.push(0);
                    }

                    stack.push(node);
                    colorStack.push(1);

                    if (node.left != null) {
                        stack.push(node.left);
                        colorStack.push(0);
                    }
                    break;
                case 1:
                    ret.add(node.val);
                    break;
                default:
                    break;
            }
        }

        return ret;
    }

}
