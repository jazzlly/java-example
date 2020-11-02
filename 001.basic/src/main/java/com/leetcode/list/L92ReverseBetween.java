package com.leetcode.list;

public class L92ReverseBetween {

    /**
     * 92. 反转链表 II
     * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
     *
     * 说明:
     * 1 ≤ m ≤ n ≤ 链表长度。
     *
     * 示例:
     *
     * 输入: 1->[2->3->4]->5->NULL, m = 2, n = 4
     *  null   1->2->3->4->5->NULL
     *  null < 1 <2 < 3 < 4   5
     *        f         prev  p
     * 输入: 1->[<-2<-3<-4]  5->NULL, m = 2, n = 4
     *      1 > < 2 > 3 < 4  5 > null
     *            f      prev p
     * 输出: 1->[4->3->2]->5->NULL
     *
     *   1->< 2 < 3 < 4 < 5  ->NULL
     *       f         prev    p

     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode prev = null;
        ListNode p = head;
        int pos = 1;

        ListNode first = null;

        while (p != null && pos <= n) {
            if (pos == m) {
                first = p;
            }

            ListNode next = p.next;

            if (pos >= m) {
                p.next = prev;
            }

            prev = p;
            p = next;
            pos++;
        }

        if (first.next != null) {
            first.next.next = prev;
        } else {
            head = prev;
        }

        first.next = p;
        return head;
    }

    public static void main(String[] args) {
        L92ReverseBetween test = new L92ReverseBetween();

        ListUtils.printList(test.reverseBetween(
                ListUtils.makeList(1,2,3,4,5), 2, 4));
        ListUtils.printList(test.reverseBetween(
                ListUtils.makeList(1,2,3,4,5), 1, 4));
        ListUtils.printList(test.reverseBetween(
                ListUtils.makeList(1,2,3,4,5), 2, 5));


    }

}
