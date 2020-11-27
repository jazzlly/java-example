package com.leetcode.list;

import java.util.ArrayList;
import java.util.List;

public class L61RotateRight {
    public ListNode rotateRight1(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        ListNode p = head;
        List<ListNode> array = new ArrayList<>();
        while (p != null) {
            array.add(p);
            p = p.next;
        }

        k = k % array.size();
        if (k == 0) {
            return head;
        }

        array.get(array.size() - k - 1).next = null;
        array.get(array.size() - 1).next = array.get(0);
        return array.get(array.size() - k);
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        int len = 1;
        ListNode p = head;
        while (p.next != null) {
            len++;
            p = p.next;
        }

        k = k % len;
        if (k == 0) {
            return head;
        }

        p.next = head; // 形成环形链表
        int step = len - k;
        while (step > 0) {
            step--;
            p = p.next;
        }

        ListNode newHead = p.next;
        p.next = null;

        return newHead;
    }

    public static void main(String[] args) {
        L61RotateRight test = new L61RotateRight();
        ListUtils.printList(test.rotateRight(
                ListUtils.makeList(1,2,3,4,5), 1));
        ListUtils.printList(test.rotateRight(
                ListUtils.makeList(1,2,3,4,5), 2));
        ListUtils.printList(test.rotateRight(
                ListUtils.makeList(1,2,3,4,5), 3));
    }
}
