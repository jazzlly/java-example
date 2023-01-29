package com.leetcode.list;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class L24ReverseEachTwoNodesTest {

    @Test
    public void smoke() {
        ListNode head = ListUtils.makeList(1,2,3,4,5);
        assertThat(ListUtils.toString(head))
                .isEqualTo("1->2->3->4->5");

        ListNode newHead = L24ReverseEachTwoNodes3.swapPairs(head);
        assertThat(ListUtils.toString(newHead))
                .isEqualTo("2->1->4->3->5");
    }

    @Test
    public void boundNull() {
        ListNode newHead = L24ReverseEachTwoNodes3.swapPairs(null);
        assertThat(ListUtils.toString(newHead)).isEqualTo("");
    }

    @Test
    public void boundOne() {
        ListNode head = ListUtils.makeList(1);
        ListNode newHead = L24ReverseEachTwoNodes3.swapPairs(head);
        assertThat(ListUtils.toString(newHead)).isEqualTo("1");
    }

    @Test
    public void smoke1() {
        ListNode head = ListUtils.makeList(1,2);

        ListNode newHead = L24ReverseEachTwoNodes3.swapPairs(head);
        assertThat(ListUtils.toString(newHead)).isEqualTo("2->1");
    }

    @Test
    public void smoke2() {
        ListNode head = ListUtils.makeList(1,2,3);

        ListNode newHead = L24ReverseEachTwoNodes3.swapPairs(head);
        assertThat(ListUtils.toString(newHead))
                .isEqualTo("2->1->3");
    }
}