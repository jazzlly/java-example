package com.leetcode.list;

import java.util.HashMap;
import java.util.Map;

public class LOF22GetKthFromEnd {

    public ListNode getKthFromEnd1(ListNode head, int k) {
        Map<ListNode, ListNode> prevMap = new HashMap<>();

        ListNode node = head;
        ListNode prev = null;
        if (node != null) {
            prevMap.put(node, prev);
            prev = node;
            node = node.next;
        }

        // prev现在指向tail节点
        for (int i = 0; i < k - 1; i++) {
            if (prev != null) {
                prev = prevMap.get(prev);
            }
        }

        return prev;
    }

    public ListNode getKthFromEnd(ListNode head, int k) {

        ListNode fast = head;
        ListNode slow = head;
        for (int i = 0; i < k; i++) {
            if (fast != null) {
                fast = fast.next;
            }
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }

    public static void main(String[] args) {
        LOF22GetKthFromEnd test = new LOF22GetKthFromEnd();
        ListNode head = ListUtils.makeList(1,2,3,4,5);
        ListNode ret = test.getKthFromEnd(head, 2);

    }

}
