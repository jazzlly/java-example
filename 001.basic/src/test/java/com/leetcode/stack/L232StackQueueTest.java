package com.leetcode.stack;

import org.junit.Test;

import java.util.EmptyStackException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class L232StackQueueTest {

    @Test
    public void smoke() {
        MyQueue queue = new MyQueue();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        assertThat(queue.pop()).isEqualTo(1);
        assertThat(queue.pop()).isEqualTo(2);
        queue.push(4);
        assertThat(queue.pop()).isEqualTo(3);
        assertThat(queue.pop()).isEqualTo(4);
    }


    @Test
    public void smoke1() {
        MyQueue queue = new MyQueue();
        queue.push(1);
        queue.push(2);
        assertThat(queue.pop()).isEqualTo(1);
        assertThat(queue.pop()).isEqualTo(2);
        queue.push(4);
        assertThat(queue.pop()).isEqualTo(3);
        assertThat(queue.pop()).isEqualTo(4);
    }

    @Test
    public void boundary() {
        MyQueue queue = new MyQueue();

        assertThat(queue.empty()).isTrue();

        assertThatThrownBy(() -> queue.pop())
                .isInstanceOf(EmptyStackException.class);

        queue.push(123);
        assertThat(queue.pop()).isEqualTo(123);
        assertThat(queue.empty()).isTrue();
    }
}