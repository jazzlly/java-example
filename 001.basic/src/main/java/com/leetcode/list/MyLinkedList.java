package com.leetcode.list;

import static org.assertj.core.api.Assertions.assertThat;

public class MyLinkedList {

    ListNode head = new ListNode();

    /** Initialize your data structure here. */
    public MyLinkedList() {
    }

    /** Get the value of the index-th node in the linked list.
     * If the index is invalid, return -1. */
    public int get(int index) {
        ListNode p = head.next;

        // 1 2
        while (index > 0 && p != null) {
            p = p.next;
            index--;
        }

        if (p != null) {
            return p.val;
        } else {
            return -1;
        }
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        ListNode node = new ListNode(val);
        node.next = head.next;
        head.next = node;
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        ListNode prev = head;
        // h 1 2
        while (prev.next != null) {
            prev = prev.next;
        }
        ListNode node = new ListNode(val);
        prev.next = node;
    }

    /** Add a node of value val before the index-th node in the linked list.
     * If index equals to the length of linked list,
     * the node will be appended to the end of linked list.
     * If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        ListNode prev = head;
        while (index > 0 && prev != null) {
            prev = prev.next;
            index--;
        }

        if (prev == null) {
            return;
        }

        ListNode node = new ListNode(val);
        node.next = prev.next;
        prev.next = node;
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        // 2 1 0
        // h 0 1 2
        ListNode prev = head;
        while (index > 0 && prev != null) {
            prev = prev.next;
            index--;
        }

        if (prev == null || prev.next == null) {
            return;
        }

        prev.next = prev.next.next;
    }

    public static void main(String[] args) {
        MyLinkedList test = new MyLinkedList();

        // assert empty list
        assertThat(test.get(0)).isEqualTo(-1);
        assertThat(test.get(1)).isEqualTo(-1);

        test.addAtHead(0);
        assertThat(test.get(0)).isEqualTo(0);

        test.addAtHead(1);
        assertThat(test.get(0)).isEqualTo(1);
        assertThat(test.get(1)).isEqualTo(0);

        test.deleteAtIndex(0);
        assertThat(test.get(0)).isEqualTo(0);

        test.deleteAtIndex(0);
        assertThat(test.get(0)).isEqualTo(-1);

        test.addAtTail(0);
        test.addAtTail(1);
        assertThat(test.get(0)).isEqualTo(0);
        assertThat(test.get(1)).isEqualTo(1);
    }
}
