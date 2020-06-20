package com.leetcode.tree;

import java.util.*;

/**
 * 429. N叉树的层序遍历
 * 给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。
 *
 */
public class L429NthTreeLevelTrav {

    public List<List<Integer>> levelOrder1(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> currentLevel = new ArrayList<>(size);
            result.add(currentLevel);

            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                currentLevel.add(node.val);

                if (node.children != null) {
                    queue.addAll(node.children);
                }
            }
        }
        return result;
    }

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            List<Node> nodes = new ArrayList<>();

            while (!queue.isEmpty()) {
                Node node = queue.poll();
                level.add(node.val);
                nodes.add(node);
            }
            ret.add(level);

            for (Node node : nodes) {
                if (node.children == null) {
                    continue;
                }
                for (Node child : node.children) {
                    queue.add(child);
                }
            }
        }
        return ret;
    }

















}
