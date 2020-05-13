package com.leetcode.stack;

import java.util.Stack;

class MinStack {

    private Stack<Integer> stack = new Stack<>();
    // 保存当前堆栈中的最小值
    private Stack<Integer> minStack = new Stack<>();

    /** initialize your data structure here. */
    public MinStack() {
        minStack.push(Integer.MAX_VALUE);
    }

    public void push(int x) {
        stack.push(x);
        minStack.push(x < minStack.peek() ? x : minStack.peek());
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}