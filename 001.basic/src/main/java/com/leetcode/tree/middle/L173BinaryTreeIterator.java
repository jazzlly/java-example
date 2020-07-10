package com.leetcode.tree.middle;

import com.leetcode.tree.TreeNode;

import java.util.Stack;

/**
 * 173. 二叉搜索树迭代器
 * 实现一个二叉搜索树迭代器。你将使用二叉搜索树的根节点初始化迭代器。
 *
 * 调用 next() 将返回二叉搜索树中的下一个最小的数。
 *
 *
 *
 * 示例：
 *
 *
 *
 * BSTIterator iterator = new BSTIterator(root);
 * iterator.next();    // 返回 3
 * iterator.next();    // 返回 7
 * iterator.hasNext(); // 返回 true
 * iterator.next();    // 返回 9
 * iterator.hasNext(); // 返回 true
 * iterator.next();    // 返回 15
 * iterator.hasNext(); // 返回 true
 * iterator.next();    // 返回 20
 * iterator.hasNext(); // 返回 false
 *
 *
 * 提示：
 *
 * next() 和 hasNext() 操作的时间复杂度是 O(1)，并使用 O(h) 内存，其中 h 是树的高度。
 * 你可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 中至少存在一个下一个最小的数。
 */
public class L173BinaryTreeIterator {

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    static class BSTIterator {

        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> colorStack = new Stack<>();

        public BSTIterator(TreeNode root) {
            if (root != null) {
                nodeStack.push(root);
                colorStack.push(0);
            }
        }

        /** @return the next smallest number */
        public int next() {
            while (!nodeStack.empty()) {
                TreeNode node = nodeStack.pop();
                Integer color = colorStack.pop();

                if (color.equals(0)) {
                    if (node.right != null) {
                        nodeStack.push(node.right);
                        colorStack.push(0);
                    }

                    nodeStack.push(node);
                    colorStack.push(1);

                    if (node.left != null) {
                        nodeStack.push(node.left);
                        colorStack.push(0);
                    }
                } else {
                    System.out.println(node.val);
                    return node.val;
                }
            }

            throw new IllegalStateException("oops!");
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return !nodeStack.empty();
        }
    }

    void InOrderTravel(TreeNode root) {
        if (root == null) {
            return;
        }

        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> colorStack = new Stack<>();
        nodeStack.push(root);
        colorStack.push(0);

        while (!nodeStack.empty()) {
            TreeNode node = nodeStack.pop();
            Integer color = colorStack.pop();

            if (color.equals(0)) {

                if (node.right != null) {
                    nodeStack.push(node.right);
                    colorStack.push(0);
                }

                nodeStack.push(node);
                colorStack.push(1);

                if (node.left != null) {
                    nodeStack.push(node.left);
                    colorStack.push(0);
                }
            } else {
                System.out.println(node.val);
            }
        }
    }

    public static void main(String[] args) {
        TreeNode n11 = new TreeNode(7);
        TreeNode n21 = new TreeNode(3);
        TreeNode n22 = new TreeNode(15);
        n11.left = n21;
        n11.right = n22;

        TreeNode n31 = new TreeNode(9);
        TreeNode n32 = new TreeNode(20);
        n22.left = n31;
        n22.right = n32;

        L173BinaryTreeIterator test = new L173BinaryTreeIterator();
        test.InOrderTravel(n11);

        BSTIterator iterator = new BSTIterator(n11);
        iterator.next();    // 返回 3
        iterator.next();    // 返回 7
        iterator.hasNext(); // 返回 true
        iterator.next();    // 返回 9
        iterator.hasNext(); // 返回 true
        iterator.next();    // 返回 15
        iterator.hasNext(); // 返回 true
        iterator.next();    // 返回 20
        System.out.println(iterator.hasNext()); // 返回 false
    }
}
