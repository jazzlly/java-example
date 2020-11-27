package com.leetcode.list;

import java.util.HashMap;
import java.util.Map;

public class L138CopyRandomList {
    public Node copyRandomList(Node head) {
        Node newHead = new Node(-1);
        Node p = head;
        Node pn = newHead;
        Map<Node, Node> nodeMap = new HashMap<>();
        // 1
        while (p != null) {
            nodeMap.putIfAbsent(p, new Node(p.val));
            if (p.random != null) {
                nodeMap.putIfAbsent(p.random, new Node(p.random.val));
            }

            pn.next = nodeMap.get(p);
            if (p.random != null) {
                pn.next.random = nodeMap.getOrDefault(p.random, null);
            }

            p = p.next;
            pn = pn.next;
        }

        return newHead.next;
    }

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        System.out.println(map.getOrDefault(null, ""));
    }
}
