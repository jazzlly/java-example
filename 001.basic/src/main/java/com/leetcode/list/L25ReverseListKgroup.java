package com.leetcode.list;

public class L25ReverseListKgroup {

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
        ListNode tmp = head;
        int step = k - 1;
        while (tmp != null && step > 0) {
            tmp = tmp.next;
            step--;
        }
        if (step > 0 || tmp == null) {
            return null;
        }

        ListNode prev = null;
        ListNode p = head;
        step = k;
        while (p != null && step-- > 0) {
            ListNode q = p.next;
            p.next = prev;

            prev = p;
            p = q;
        }

        // tmp为队尾，tmp.next指向下一个队列
        head.next = p;

        return prev;
    }
}
