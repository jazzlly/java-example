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
public class L94BinaryTreeInOrderTrav2 {
    private List<Integer> result = new ArrayList<>();
    private Stack<TreeNode> stack = new Stack<>();

    enum Color {
        White,
        Gray
    }

    /**
     * 染色方法
     * @param root
     * @return
     */
    public List<Integer> inorderTraversalColor(TreeNode root) {
        Stack<Object[]> colorStack = new Stack<>();
        colorStack.push(new Object[]{root, Color.White});

        while (!colorStack.empty()) {
            Object[] pop = colorStack.pop();
            TreeNode node = (TreeNode) pop[0];
            if (node == null) {
                continue;
            }

            if (pop[1].equals(Color.White)) {
                if (node.right != null) {
                    colorStack.push(new Object[]{node.right, Color.White});
                }

                colorStack.push(new Object[]{node, Color.Gray});

                if (node.left != null) {
                    colorStack.push(new Object[]{node.left, Color.White});
                }
            } else {
                result.add(node.val);
            }
        }

        return result;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        TreeNode p = root;

        while (p != null || !stack.empty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                TreeNode pop = stack.pop();
                result.add(pop.val);
                p = pop.right;
            }

        }

        return result;
    }

    public List<Integer> inorderTraversal1(TreeNode root) {
        recursion(root);
        return result;
    }

    private void recursion(TreeNode node) {
        if (node == null) {
            return;
        }

        recursion(node.left);
        result.add(node.val);
        recursion(node.right);
    }


}
