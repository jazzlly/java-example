package com.leetcode.list;


import org.junit.Test;

public class L142DetectListCycleTest {

    @Test
    public void smoke() {
        ListNode head = ListUtils.makeList(3, 2, 0, -4);
        ListNode second = head.next;
        ListNode tail = head.next.next.next;
        tail.next = second;

        ListUtils.printList(head, 20);

        L142DetectListCycle test = new L142DetectListCycle();
        ListNode ret = test.detectCycle(head);
        System.out.println(ret.val);
    }
}