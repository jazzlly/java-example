package com.leetcode.search;

import com.leetcode.tree.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 非整除关系的硬币集合, coins = [10, 9, 1]
 * 求拼出18的最少需要几枚硬币
 *
 */
public class CoinChangeNoGreedy {

    public static final int[] conis = new int[]{10, 9, 1};

    public Node levelOrder(Node root) {
        assert root != null;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Node node = queue.poll();

                List<Node> children = new ArrayList<>();
                for (int coin : conis) {
                    int diff = node.val - coin;
                    if (diff == 0) {
                        Node last = new Node(diff, null, node);
                        printResult(last);
                        return last;
                    } else if (diff > 0) {
                        children.add(new Node(diff, null, node));
                    }
                }
                node.children = children;

                if (node.children != null && !node.children.isEmpty()) {
                    queue.addAll(node.children);
                }
            }
        }

        return null;
    }

    public void printResult(Node children) {
        Node node = children;
        while (node != null) {
            System.out.println(node.val);
            node = node.parent;
        }
    }

    public static void main(String[] args) {
        CoinChangeNoGreedy test = new CoinChangeNoGreedy();

        test.levelOrder(new Node(35));


    }
}
