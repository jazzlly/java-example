package com.leetcode.list;

public class L25ReverseListKgroup2 {

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode prev = null;
        ListNode p = head;

        while (p != null) {
            ListNode q = revk(p, k);
            if (q == null) {
                break;
            }

            if (prev != null) {
                prev.next = q;
            } else {
                head = q;
            }

            prev = p;
            p = p.next;
        }

        return head;
    }

    public static ListNode revk(ListNode head, int k) {
        if (k <= 1) {
            return head;
        }

        ListNode p = head;
        int cnt = k;

        while (p != null && --cnt > 0) {
            p = p.next;
        }

        if (p == null || cnt > 0) {
            return null;
        }

        // prev = tail.next
        ListNode prev = p.next;
        p = head;
        cnt = k;

        while (p != null && cnt-- > 0) {
            ListNode q = p.next;
            p.next = prev;

            prev = p;
            p = q;
        }

        return prev;
    }
}
