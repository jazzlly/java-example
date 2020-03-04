package com.leetcode.stack;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class L225QueueStackTest {
    @Test
    public void smoke() {
        MyStack stack = new MyStack();

        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertThat(stack.pop()).isEqualTo(3);
        stack.push(4);
        assertThat(stack.pop()).isEqualTo(4);
        assertThat(stack.pop()).isEqualTo(2);
        assertThat(stack.pop()).isEqualTo(1);

    }
}