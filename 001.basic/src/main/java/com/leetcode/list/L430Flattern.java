package com.leetcode.list;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class L430Flattern {

    public Node flatten(Node head) {
        Stack<Node> stack = new Stack();
        stack.push(head);

        while(!stack.empty()) {
            Node p = stack.pop();

            if (p == null) {
                continue;
            }

            if (p.child == null) {
                if (p.next != null) {
                    stack.push(p.next);
                } else {
                    if (!stack.empty()) {
                        Node end = stack.pop();
                        p.next = end;
                        if (end != null) {
                            end.prev = p;
                        }
                        stack.push(end);
                    }
                }
                continue;
            }

            stack.push(p.next);
            Node child = p.child;
            p.next = child;
            p.child = null;
            child.prev = p;
            stack.push(child);
        }
        return head;
    }


    static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    }

    public static void main(String[] args) {
        Stack<Node> stack = new Stack<>();
        stack.push(null);
        Node node = stack.peek();
        System.out.println(node);

    }
}
