package com.leetcode.list;

import org.junit.Test;

import static org.junit.Assert.*;

public class L25ReverseListKgroupTest {

    @Test
    public void revkSmoke() {
        ListNode head = ListUtils.makeList(4, 5, 6, 7);
        System.out.println(ListUtils.dumpList(head));

        ListNode revk = L25ReverseListKgroup.revk(head, 3);
        System.out.println(ListUtils.dumpList(revk));
    }

    @Test
    public void revkSmokeK1() {
        ListNode head = ListUtils.makeList(4, 5, 6, 7);
        System.out.println(ListUtils.dumpList(head));

        ListNode revk = L25ReverseListKgroup.revk(head, 1);
        System.out.println(ListUtils.dumpList(revk));
    }

    @Test
    public void revkSmokeK2() {
        ListNode head = ListUtils.makeList(4, 5, 6, 7);
        System.out.println(ListUtils.dumpList(head));

        ListNode revk = L25ReverseListKgroup.revk(head, 2);
        System.out.println(ListUtils.dumpList(revk));
    }

    @Test
    public void reverseKGroupSmoke() {
        ListNode head = ListUtils.makeList(1,2,3, 4, 5, 6, 7);
        ListNode listNode = L25ReverseListKgroup.reverseKGroup(head, 3);

        System.out.println(ListUtils.dumpList(listNode));

    }

    @Test
    public void reverseKGroupSmokeK2() {
        ListNode head = ListUtils.makeList(1,2,3, 4, 5, 6, 7);
        ListNode listNode = L25ReverseListKgroup.reverseKGroup(head, 2);

        System.out.println(ListUtils.dumpList(listNode));

    }
}