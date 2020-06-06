package com.leetcode.stack;

import java.util.Stack;

public class L155MinStack {

    Stack<Integer> stack = new Stack<>();
    Stack<Integer> stackMin = new Stack<>();
    
    public L155MinStack() {
    }
    
    public void push(int x) {
        stack.push(x);

        if (stackMin.isEmpty()) {
            stackMin.push(x);
        } else {
            if (x > stackMin.peek()) {
                stackMin.push(stackMin.peek());
            } else {
                stackMin.push(x);
            }
        }
    }
    
    public void pop() {
        stack.pop();
        stackMin.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return stackMin.peek();
    }
}
