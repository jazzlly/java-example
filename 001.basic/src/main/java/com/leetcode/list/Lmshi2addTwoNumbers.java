package com.leetcode.list;

public class Lmshi2addTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        ListNode p = head;
        int carry = 0;

        //
        while (l1 != null || l2 != null) {
            p.next = new ListNode();
            p = p.next;

            int v1 = l1 != null ? l1.val : 0;
            int v2 = l2 != null ? l2.val : 0;
            int res = v1 + v2 + carry;
            p.val = res % 10;
            carry = res / 10;

            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }

        if (carry > 0) {
            p.next = new ListNode(carry);
        }

        return head.next;
    }

    public static void main(String[] args) {
        Lmshi2addTwoNumbers test = new Lmshi2addTwoNumbers();
        ListNode l1 = ListUtils.makeList(9);
        ListNode l2 = ListUtils.makeList(1,9,9,9,9,9,9,9,9,9);



        ListNode l3 = test.addTwoNumbers(l1, l2);
        ListUtils.printList(l3);

    }
}
