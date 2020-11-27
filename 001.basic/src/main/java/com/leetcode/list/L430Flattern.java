package com.leetcode.list;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class L430Flattern {

    public Node flatten1(Node head) {
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

    public Node flatten(Node head) {
        recursive(head);
        return head;
    }

    Node recursive(Node p) {

        while (p != null) {
            if (p.child != null) {
                if (p.next != null) {
                    p = p.next;
                    continue;
                } else {
                    return p;
                }
            }

            if (p.child != null) {
                Node next = p.next;
                p.next = p.child;
                p.child.prev = p;
                p.child = null;

                Node tail = recursive(p.next);
                tail.next = next;
                if (next != null) {
                    next.prev = tail;
                }
                p = p.next;
            }
        }
        return null;
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
