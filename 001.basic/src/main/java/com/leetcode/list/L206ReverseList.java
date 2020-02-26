package com.leetcode.list;

/**
 * 使用迭代和递归两种方式反转链表
 */
public class L206ReverseList {

    /**
     * 使用迭代方式反转链表
     * @param head 链表头节点
     * @return 反转后的头节点
     */
    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode next = null;

        while (head != null) {
            next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }

        return prev;
    }


    /**
     * 使用递归方式实现反转链表
     * @param head 辅助头节点, 开始传入null
     * @param left 头节点的后继节点
     * @return 反转后的头节点
     */
    public static ListNode reverseListRecursiveNew(ListNode head, ListNode left) {
        if (left == null) {
            return head;
        } else  {
            ListNode newHead = reverseListRecursiveNew(left, left.next);
            left.next = head;
            return newHead;
        }
    }
}