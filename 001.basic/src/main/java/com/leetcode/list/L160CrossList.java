package com.leetcode.list;

import java.util.HashSet;
import java.util.Set;

/**
 * 160. 相交链表
 * 编写一个程序，找到两个单链表相交的起始节点。
 */
public class L160CrossList {

    /**
     * 双指针法
     */
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        ListNode p = headA;
        ListNode q = headB;

        while (p != q) {
            p = p != null ? p.next : headB;
            q = q != null ? q.next : headA;
        }
        return p;
    }

    /**
     * set方法： 将一条链条所有节点加入set. 遍历另外一个链表，找到第一个相同的node
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();

        ListNode p = headA;
        while (p != null) {
            set.add(p);
            p = p.next;
        }

        ListNode q = headB;
        while (q != null) {
            if (set.contains(q)) {
                return q;
            }
            q = q.next;
        }

        return null;
    }
}
