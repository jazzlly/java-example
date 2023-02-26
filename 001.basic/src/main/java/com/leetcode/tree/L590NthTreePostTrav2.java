package com.leetcode.tree;

import java.util.*;

/**
 * 590. N叉树的后序遍历
 * 给定一个 N 叉树，返回其节点值的后序遍历。
 * 左子树 -> 右子树 -> 根
 *
 * 说明: 递归法很简单，你可以使用迭代法完成此题吗?
 */
public class L590NthTreePostTrav2 {

    public List<Integer> postorder(Node root) {
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
                for (Node child : p.children) {
                    stack.push(child);
                }
            }
        }

        Collections.reverse(answer);
        return answer;
    }

    public List<Integer> postorder1(Node root) {
        List<Integer> answer = new ArrayList<>();
        Deque<Node> stack = new LinkedList<>();
        Set<Node> travelSet = new HashSet<>();

        if (root == null) {
            return answer;
        }

        stack.push(root);

        while (!stack.isEmpty()) {
            Node p = stack.peek();
            if (p.children != null && !travelSet.contains(p)) {
                travelSet.add(p);
                for (int i = p.children.size() - 1; i >= 0 ; i--) {
                    stack.push(p.children.get(i));
                }
            } else {
                answer.add(p.val);
                stack.pop();
            }
        }

        return answer;
    }

}
