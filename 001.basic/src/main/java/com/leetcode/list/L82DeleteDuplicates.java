package com.leetcode.list;

import static org.assertj.core.api.Assertions.assertThat;

public class L82DeleteDuplicates {

    /**        pd    p  p
     * 输入: 1->2->3->3->4->4->5
     * 输入: 1->2->4->4->5
     *            p
     * 输出: 1->2->5
     *
     * 输入: 1->1->1->2->3
     * 输出: 2->3
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode newHead = new ListNode();
        ListNode prev = newHead;
        ListNode p = head;

        boolean skip = false;
        while (p != null) {
            while (p.next != null && p.val == p.next.val) {
                p = p.next;
                skip = true;
                continue;
            }

            if (skip) {
                skip = false;
                p = p.next;
                continue;
            }

            // 常规情况 p.val != p.next.val
            prev.next = p;
            prev = p;
            p = p.next;
            prev.next = null;
        }
        return newHead.next;
    }

    public static void main(String[] args) {
        L82DeleteDuplicates test = new L82DeleteDuplicates();

        assertThat(test.deleteDuplicates(null)).isNull();;
        assertThat(test.deleteDuplicates(ListUtils.makeList(1,1))).isNull();
        assertThat(test.deleteDuplicates(ListUtils.makeList(1,1,1))).isNull();
        assertThat(test.deleteDuplicates(ListUtils.makeList(1,1,2,2))).isNull();

        ListUtils.printList(test.deleteDuplicates(ListUtils.makeList(1)));
        ListUtils.printList(test.deleteDuplicates(ListUtils.makeList(1,2,3,3,4,4,5)));

        ListUtils.printList(test.deleteDuplicates(ListUtils.makeList(1, 1, 2)));
        ListUtils.printList(test.deleteDuplicates(ListUtils.makeList(1, 2, 2)));

        assertThat(test.deleteDuplicates(ListUtils.makeList(1,1,2,2))).isNull();


    }
}
