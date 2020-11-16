package com.leetcode.list;

public class L328OddEvenList {

    // 1 2
    // 1 2 3 4
    public ListNode oddEvenList(ListNode head) {
        ListNode oddHead = new ListNode();
        ListNode eventHead = new ListNode();
        ListNode op = oddHead;
        ListNode ep = eventHead;

        ListNode p = head;
        while (p != null) {
            op.next = p;
            ep.next = p.next;

            op = op.next;
            ep = ep.next;

            p = p.next;
            if (p != null) {
                p = p.next;
            }
        }

        op.next = eventHead.next;
        return oddHead.next;
    }
}
