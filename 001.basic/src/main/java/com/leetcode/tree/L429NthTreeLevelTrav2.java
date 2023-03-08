package com.leetcode.tree;

import java.util.*;

/**
 * 429. N叉树的层序遍历
 * 给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。
 *
 */
public class L429NthTreeLevelTrav2 {

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> answer = new ArrayList<>();
        if (root == null) {
            return answer;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int count;

        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            answer.add(level);

            count = queue.size();
            for (int i = 0; i < count; i++) {
                Node poll = queue.poll();
                level.add(poll.val);
                if (poll.children != null && poll.children.size() > 0) {
                    for (Node child : poll.children) {
                        queue.add(child);
                    }
                }
            }
        }
        return answer;
    }
}
