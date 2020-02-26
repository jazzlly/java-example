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
public class L24ReverseEachTwoNodes {

    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode prev = null;
        ListNode current = head;
        ListNode next = current.next;

        // 如果list有第二个节点，则第二个节点为新链条头
        head = head.next;

        while (true) {
            current.next = next.next;
            next.next = current;
            if (prev != null) {
                prev.next = next;
            }

            // 三个指针向前移动
            prev = current;
            if (prev == null) {
                break;
            }
            current = prev.next;
            if (current == null) {
                break;
            }
            next = current.next;
            if (next == null) {
                break;
            }
        }

        return head;
    }
}
