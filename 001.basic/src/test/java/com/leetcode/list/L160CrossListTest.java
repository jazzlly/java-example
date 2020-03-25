package com.leetcode.list;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class L160CrossListTest {

    @Test
    public void smoke() {
        ListNode c1 = ListUtils.makeList(8, 4, 5);

        ListNode a1 = new ListNode(4);
        ListNode a2 = new ListNode(1);
        ListUtils.makeList(a1, a2, c1);

        ListNode b1 = new ListNode(5);
        ListNode b2 = new ListNode(0);
        ListNode b3 = new ListNode(1);
        ListUtils.makeList(b1, b2, b3, c1);

        assertThat(new L160CrossList().getIntersectionNode(a1, b1))
                .isEqualTo(c1);
    }

    @Test
    public void smokeNoOk() {
        ListNode a1 = new ListNode(4);
        ListNode a2 = new ListNode(1);
        ListUtils.makeList(a1, a2);

        ListNode b1 = new ListNode(5);
        ListNode b2 = new ListNode(0);
        ListNode b3 = new ListNode(1);
        ListUtils.makeList(b1, b2, b3);

        assertThat(new L160CrossList().getIntersectionNode(a1, b1))
                .isEqualTo(null);

    }




}