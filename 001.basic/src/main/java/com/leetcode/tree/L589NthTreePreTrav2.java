package com.leetcode.tree;

import java.util.*;

/**
 * 589. N叉树的前序遍历
 * 给定一个 N 叉树，返回其节点值的前序遍历。
 * 当前节点 -> 左子树 -> 右自述
 *
 * 例如，给定一个 3叉树
 * 返回其前序遍历: [1,3,5,6,2,4]。
 *
 * 说明: 递归法很简单，你可以使用迭代法完成此题吗?
 */
public class L589NthTreePreTrav2 {
    /**
     * 迭代解法
     */
    public List<Integer> preorder(Node root) {
        List<Integer> answer = new ArrayList<>();
        Deque<Node> stack = new LinkedList<>();

        if (root == null) {
            return answer;
        }

        stack.push(root);

        while (!stack.isEmpty()) {
            Node p = stack.pop();

            answer.add(p.val);
            if (p.children != null) {
                for (int i = p.children.size() - 1; i >= 0 ; i--) {
                    stack.push(p.children.get(i));
                }
            }
        }

        return answer;
    }

}
