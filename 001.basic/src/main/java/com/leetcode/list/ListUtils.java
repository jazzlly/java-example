package com.leetcode.list;

public class ListUtils {
    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }
        System.out.println("");
    }

    public static ListNode makeList(int ... values) {
        ListNode head = ListNode.builder()
                .val(values[0])
                .next(null)
                .build();

        ListNode prev = head;

        for (int i = 1; i < values.length; i++) {
            ListNode node = ListNode.builder()
                    .val(values[i])
                    .next(null)
                    .build();
            prev.next = node;
            prev = node;
        }

        return head;
    }
}
