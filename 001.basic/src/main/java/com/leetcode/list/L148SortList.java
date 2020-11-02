package com.leetcode.list;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class L148SortList {
    public ListNode sortList1(ListNode head) {
        ListNode p = head;
        List<ListNode> array = new ArrayList<>();
        while (p != null) {
            array.add(p);
            p = p.next;
        }

        array.sort(Comparator.comparingInt(o -> o.val));

        for (int i = 0; i < array.size() - 1; i++) {
            array.get(i).next = array.get(i + 1);
        }

        if (array.size() > 0) {
            array.get(array.size() - 1).next = null;
            return array.get(0);
        }
        return null;
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        prev.next = null;
        // ListUtils.printList(head);
        // ListUtils.printList(slow);

        ListNode left = sortList(head);
        ListNode right = sortList(slow);
        return mergeList(left, right);
    }

    public ListNode mergeList(ListNode left, ListNode right) {
        ListNode sentinal = new ListNode();
        ListNode tmp = sentinal;
        while (left != null || right != null) {
            int vl = left != null ? left.val : Integer.MAX_VALUE;
            int vr = right != null ? right.val : Integer.MAX_VALUE;

            tmp.next = new ListNode(Math.min(vl, vr));
            tmp = tmp.next;

            if (vl <= vr) {
                left = left.next;
            } else {
                right = right.next;
            }
        }

        return sentinal.next;
    }

    public static void main(String[] args) {
        L148SortList test = new L148SortList();
        ListUtils.printList(test.sortList(ListUtils.makeList(4,19,14,5,-3,1,8,5,11,15)));

        test.sortList(ListUtils.makeList(1,2,3));
        test.sortList(null);

        ListNode tmp = test.mergeList(ListUtils.makeList(1,3,5,7),
                ListUtils.makeList(2,4,6,8));
        ListUtils.printList(tmp);

        ListUtils.printList(test.mergeList(
                null, null));
        ListUtils.printList(test.mergeList(null, ListUtils.makeList(1)));

        ListUtils.printList(test.sortList(ListUtils.makeList(1)));
        ListUtils.printList(test.sortList(ListUtils.makeList(3,2,1)));
        ListUtils.printList(test.sortList(ListUtils.makeList(3,2)));

    }
}
