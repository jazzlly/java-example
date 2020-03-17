package com.leetcode.queue;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MyCircularDequeTest {

    @Test
    public void smoke() {
        MyCircularDeque2 deque = new MyCircularDeque2(3);
        assertThat(deque.isEmpty()).isTrue();
        assertThat(deque.isFull()).isFalse();

        /*
        assertThatThrownBy(() -> {
            deque.getFront();
        }).isInstanceOf(NoSuchElementException.class);

        assertThatThrownBy(() -> {
            deque.getRear();
        }).isInstanceOf(NoSuchElementException.class);
         */

        deque.insertLast(1);
        deque.insertLast(2);
        deque.insertFront(3);
        assertThat(deque.insertLast(8)).isFalse();
        assertThat(deque.insertFront(8)).isFalse();

        assertThat(deque.getRear()).isEqualTo(2);
        assertThat(deque.getFront()).isEqualTo(3);
    }

    @Test
    public void smoke2() {
        MyCircularDeque2 deque = new MyCircularDeque2(4);

        deque.insertFront(9);
        deque.deleteLast();
        deque.getRear();
        assertThat(deque.insertLast(8)).isFalse();
        assertThat(deque.insertFront(8)).isFalse();

        assertThat(deque.getRear()).isEqualTo(2);
        assertThat(deque.getFront()).isEqualTo(3);


    }
}