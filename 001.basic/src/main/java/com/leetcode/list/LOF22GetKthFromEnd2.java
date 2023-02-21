package com.leetcode.list;

import java.util.HashMap;
import java.util.Map;

public class LOF22GetKthFromEnd2 {

    public static ListNode getKthFromEnd(ListNode head, int k) {

        ListNode fast = head;
        ListNode slow = new ListNode();
        slow.next = head;

        for (int i = 0; i < k - 1; i++) {
            if (fast == null) {
                return null;
            }
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }
}
