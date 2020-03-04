package com.leetcode.stack;

import java.util.LinkedList;
import java.util.Queue;

public class L225QueueStack {
}

/**
 * 基本方法已经想到了。
 * push的时候直接放到队尾。 pop的时候，将前面的size-1个放到队尾，pop出最后一个。
 * 反过来想:
 * push的时候，直接放到队尾，然后将前面的size-1个放到队尾。
 * pop的时候，直接pop
 */
class MyStack {

    final Queue<Integer> queue = new LinkedList<>();

    /** Initialize your data structure here. */
    public MyStack() {
    }

    /** Push element x onto stack. */
    public void push(int x) {
        queue.add(x);
        for (int i = 0; i < queue.size() - 1; i++) {
            queue.add(queue.remove());
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return queue.remove();
    }

    /** Get the top element. */
    public int top() {
        return queue.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }
}
