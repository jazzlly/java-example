package com.leetcode.tree;

import java.util.*;

public class L145PostorderTraversal {

    enum Color {
        RED, BLACK
    }

    /**
     * 后序遍历二叉树
     * @param root
     * @return
     */
    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> answer = new ArrayList<>();
        if (root == null) {
            return answer;
        }

        Deque<Object[]> stack = new LinkedList<>();
        TreeNode p = root;

        while (true) {
            if (p.left != null) {
                stack.push(new Object[]{p, Color.RED});
                p = p.left;
                continue;
            }
            if (p.right != null) {
                stack.push(new Object[]{p, Color.BLACK});
                p = p.right;
                continue;
            }

            answer.add(p.val);

            while (!stack.isEmpty()) {
                Object[] pop = stack.pop();
                TreeNode node = (TreeNode) pop[0];
                if (pop[1].equals(Color.RED) && node.right != null) {
                    stack.push(new Object[]{node, Color.BLACK});
                    p = node.right;
                    break;
                }

                answer.add(node.val);
            }
            if (stack.isEmpty()) {
                break;
            }
        }

        return answer;
    }


    /**
     * 使用前序遍历的逻辑修改后序遍历
     * 前序遍历 根 > 左 > 右
     * 后序遍历 左 > 右 > 根
     * 使用如下规则:
     *  1. 将节点插入到集合的前面, 则顺序变成了 右 > 左 > 根
     *  2. 先处理右节点，再处理左节点 , 则顺序变成 左 > 右 > 根
     *
     * @param root
     * @return
     */
    public static List<Integer> postorderTraversal2(TreeNode root) {
        Deque<Integer> result = new LinkedList<>();

        TreeNode p = root;
        Stack<TreeNode> stack = new Stack<>();

        while (true) {
            if (p != null) {
                stack.push(p);
                result.addFirst(p.val);
                p = p.right;
                continue;
            }

            if (stack.empty()) {
                break;
            }

            p = stack.pop().left;
        }

        return (List<Integer>) result;
    }
}
