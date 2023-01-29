package com.leetcode.list;

/**
 * 24. 两两交换链表中的节点
 *
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 示例:
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 */
public class L24ReverseEachTwoNodes3 {

    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode prev = null;
        ListNode p = head;
        ListNode q = head.next;

        head = head.next;
        while (p != null && q != null) {
            if (prev != null) {
                prev.next = p.next;
            }

            p.next = q.next;
            q.next = p;

            prev = p;
            if (p != null) {
                p = p.next;
            }
            if (p != null) {
                q = p.next;
            }
        }

        return head;
    }
}
