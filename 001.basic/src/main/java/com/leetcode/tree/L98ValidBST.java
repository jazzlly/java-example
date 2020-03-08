package com.leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 98. 验证二叉搜索树
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 *
 * 假设一个二叉搜索树具有如下特征：
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 *
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: true
 * 示例 2:
 *
 * 输入:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 *
 */
public class L98ValidBST {
    public boolean isValidBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        try {
            middleOrder(root, list);
        } catch (IllegalStateException e) {
            return false;
        }
        return true;
    }

    private void middleOrder(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }

        middleOrder(node.left, list);

        if (list.size() >= 1 && list.get(list.size() -1) >= node.val) {
            throw new IllegalStateException();
        }
        list.add(node.val);

        middleOrder(node.right, list);
    }

    public boolean isValidBST4(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<TreeNode> list = new ArrayList<>();

        TreeNode node = root;

        while(node != null) {
            while (true) {
                stack.push(node);
                if (node.left == null) {
                    break;
                }
                node = node.left;
            }

            if (list.size() >= 1 && list.get(list.size() -1).val >= node.val) {
                return false;
            }
            list.add(node);

            if (node.right != null) {
                node = node.right;
                continue;
            }

            node = stack.peek();
        }

        return true;
    }


    public boolean helper(TreeNode node, Integer lower, Integer upper) {
        if (node == null) {
            return true;
        }

        if (lower != null && lower >= node.val) {
            return false;
        }
        if (upper != null && upper <= node.val) {
            return false;
        }

        if (!helper(node.left, lower, node.val)) {
            return false;
        }
        if (!helper(node.right, node.val, upper)) {
            return false;
        }
        return true;
    }

    public boolean isValidBST2(TreeNode root) {
        return helper(root, null, null);
    }

    Stack<TreeNode> nodes = new Stack<>();
    Stack<Integer> uppers = new Stack<>();
    Stack<Integer> lowers = new Stack<>();

    public boolean isValidBST3(TreeNode root) {
        updateStack(root, null, null);

        while (!nodes.isEmpty()) {
            TreeNode node = nodes.pop();
            Integer lower = lowers.pop();
            Integer upper = uppers.pop();

            if (node == null) {
                continue;
            }

            if (lower != null && lower >= node.val) {
                return false;
            }
            if (upper != null && upper <= node.val) {
                return false;
            }
            updateStack(node.left, lower, node.val);
            updateStack(node.right, node.val, upper);
        }
        return true;
    }

    private void updateStack(TreeNode node, Integer lower, Integer upper) {
        nodes.push(node);
        lowers.push(lower);
        uppers.push(upper);
    }

    public void appendLeftChild(TreeNode node, TreeNode left) {
        node.left = left;
    }

    public void appendRightChild(TreeNode node, TreeNode right) {
        node.right = right;
    }
}
