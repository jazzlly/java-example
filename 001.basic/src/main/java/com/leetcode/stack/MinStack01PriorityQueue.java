package com.leetcode.stack;

import java.util.PriorityQueue;
import java.util.Stack;

class MinStack01PriorityQueue {

    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    private Stack<Integer> stack = new Stack<>();

    /** initialize your data structure here. */
    public MinStack01PriorityQueue() {
    }

    public void push(int x) {
        minHeap.add(x);
        stack.push(x);
    }

    public void pop() {
        int x = stack.pop();
        minHeap.remove(x);
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minHeap.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */