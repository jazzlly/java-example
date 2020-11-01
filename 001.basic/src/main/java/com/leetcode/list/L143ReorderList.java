package com.leetcode.list;

import java.util.ArrayList;
import java.util.List;

public class L143ReorderList {

    public void reorderList1(ListNode head) {
        // 将链表拆分成两个链表
        // 将第二个链表反转
        // 将第二个链表插入到第一个链表中

        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;

        while (slow != null && slow.next != null &&
                fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
            }
        }

        // 奇数节点
        if (fast != null) {
            prev = slow;
            slow = slow.next;
        }
        if (prev != null) {
            prev.next = null;
        }

        // slow 目前就是中间节点
        ListNode middle = reverseList(slow);
        // ListUtils.printList(middle);

        slow = head;
        while (slow != null && middle != null) {
            ListNode slowNext = slow.next;
            ListNode middleNext = middle.next;
            slow.next = middle;
            middle.next = slowNext;

            slow = slowNext;
            middle = middleNext;
        }

        // ListUtils.printList(head);
    }


    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode next = head;
        while (next != null) {
            ListNode tmp = next.next;
            next.next = prev;
            prev = next;
            next = tmp;
        }
        return prev;
    }

    public void reorderList(ListNode head) {
        /*
        给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
         */
        // 1, 2, 3, 4
        // 1 - 4 - 2 -3
        // 1, 2, 3
        List<ListNode> list = new ArrayList<>();
        ListNode p = head;
        while (p != null) {
            list.add(p);
            p = p.next;
        }

        for (int j = 0; j < list.size(); j++) {
            if (j < (list.size() + 1) / 2) {
                list.get(j).next = list.get(list.size() - 1 - j);
            } else {
                list.get(j).next = list.get(list.size() - j);
            }
            if (list.get(j).next == list.get(j)) {
                list.get(j).next = null;
            }
        }
        // ListUtils.printList(head);
    }

    public static void main(String[] args) {
        L143ReorderList test = new L143ReorderList();

//        ListNode tmp = test.reverseList(ListUtils.makeList(1,2,3,4,5));
//        ListUtils.printList(tmp);

        ListNode head = ListUtils.makeList(1, 2, 3, 4, 5);
        test.reorderList(head);

        test.reorderList(ListUtils.makeList(1,2,3,4));
    }
}
