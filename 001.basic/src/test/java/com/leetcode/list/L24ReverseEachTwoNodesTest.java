package com.leetcode.list;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class L24ReverseEachTwoNodesTest {

    @Test
    public void smoke() {
        ListNode head = ListUtils.makeList(1,2,3,4,5);
        ListUtils.printList(head);

        ListNode newHead = L24ReverseEachTwoNodes.swapPairs(head);
        ListUtils.printList(newHead);
    }

    @Test
    public void smoke1() {
        ListNode head = ListUtils.makeList(1,2);
        ListUtils.printList(head);

        ListNode newHead = L24ReverseEachTwoNodes.swapPairs(head);
        ListUtils.printList(newHead);
    }

    @Test
    public void smoke2() {
        ListNode head = ListUtils.makeList(1,2,3);
        ListUtils.printList(head);

        ListNode newHead = L24ReverseEachTwoNodes.swapPairs(head);
        ListUtils.printList(newHead);
    }

    @Test
    public void boundary() {
        assertThat(L24ReverseEachTwoNodes.swapPairs(null)).isNull();

        ListNode head = ListUtils.makeList(1);
        ListNode head2 = L24ReverseEachTwoNodes.swapPairs(head);

        assertThat(head2.val).isEqualTo(head.val);
        assertThat(head2.next).isNull();
    }
}