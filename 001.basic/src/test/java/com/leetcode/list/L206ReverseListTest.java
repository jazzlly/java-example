package com.leetcode.list;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class L206ReverseListTest {

    @Test
    public void smoke() {
        ListNode head = ListUtils.makeList(1,2,3,4);
        ListUtils.printList(head);

        ListNode newHead = L206ReverseList.reverseList(head);
        ListUtils.printList(newHead);
    }

    @Test
    public void boundary() {
        assertThat(L206ReverseList.reverseList(null)).isNull();

        ListNode head = ListUtils.makeList(1);
        ListNode head2 = L206ReverseList.reverseList(head);

        assertThat(head2.val).isEqualTo(head.val);
        assertThat(head2.next).isNull();
    }

    @Test
    public void smokeRecursively() {
        ListNode head = ListUtils.makeList(1,2,3,4);
        ListUtils.printList(head);

        ListNode newHead = L206ReverseList.reverseListRecursiveNew(null, head);
        ListUtils.printList(newHead);
    }

    @Test
    public void boundaryRecursively() {
        assertThat(L206ReverseList.reverseListRecursiveNew(null, null)).isNull();

        ListNode head = ListUtils.makeList(1);
        ListNode head2 = L206ReverseList.reverseListRecursiveNew(null, head);

        assertThat(head2.val).isEqualTo(head.val);
        assertThat(head2.next).isNull();
    }


}