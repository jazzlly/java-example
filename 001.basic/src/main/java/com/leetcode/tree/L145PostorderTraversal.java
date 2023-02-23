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
}
