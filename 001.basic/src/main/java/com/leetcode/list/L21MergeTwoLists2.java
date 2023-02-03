package com.leetcode.list;

/**
 * 21. 合并两个有序链表
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * 示例：
 *
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 */
public class L21MergeTwoLists2 {

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = new ListNode();
        ListNode p0 = head;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                p0.next = list1;
                p0 = list1;
                list1 = list1.next;
            } else {
                p0.next = list2;
                p0 = list2;
                list2 = list2.next;
            }
        }

        if (list1 != null) {
            p0.next = list1;
        }
        if (list2 != null) {
            p0.next = list2;
        }

        return head.next;
    }
}
