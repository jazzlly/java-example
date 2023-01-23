package com.leetcode.list;

import java.util.List;

/**
 * 使用迭代和递归两种方式反转链表
 */
public class L206ReverseList2 {

    public ListNode reverseList(ListNode head) {
        ListNode p = null;
        ListNode q = head;
        while (q != null) {
            ListNode tmp = q.next;
            q.next = p;
            p = q;
            q = tmp;
        }

        return p;
    }
}