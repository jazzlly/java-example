package com.leetcode.list;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class L445AddTwoNumbers {

    /**
     * 445. 两数相加 II
     * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
     *
     * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
     *
     * 进阶：
     * 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
     *
     * 输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 8 -> 0 -> 7
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        // 思路1 : 反转两个链表, 然后相加，然后反转结果
        ListNode r1 = reverseList(l1);
        ListNode r2 = reverseList(l2);

        ListNode r3 = new ListNode();
        ListNode p = r3;
        int carrage = 0;
        while (r1 != null || r2 != null) {
            int val1 = r1 != null ? r1.val : 0;
            int val2 = r2 != null ? r2.val : 0;

            p.next = new ListNode((val1 + val2 + carrage) % 10);
            carrage = (val1 + val2 + carrage) / 10;

            p = p.next;
            r1 = r1 != null ? r1.next : null;
            r2 = r2 != null ? r2.next : null;
        }
        if (carrage != 0) {
            p.next = new ListNode(carrage);
        }

        return reverseList(r3.next);
    }

    ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode p = head;

        while (p != null) {
            ListNode next = p.next;
            p.next = prev;
            prev = p;
            p = next;
        }
        return prev;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 通过两个linked list保存原始链表数据，使用这两个linked list进行操作
        LinkedList<Integer> ln1 = new LinkedList();
        LinkedList<Integer> ln2 = new LinkedList<>();

        ListNode p1 = l1;
        ListNode p2 = l2;
        while (p1 != null) {
            ln1.addFirst(p1.val);
            p1 = p1.next;
        }
        while (p2 != null) {
            ln2.addFirst(p2.val);
            p2 = p2.next;
        }

        Iterator<Integer> it1 = ln1.iterator();
        Iterator<Integer> it2 = ln2.iterator();

        ListNode r3 = new ListNode();
        int carrage = 0;

        while (it1.hasNext() || it2.hasNext()) {
            int val1 = it1.hasNext() ? it1.next() : 0;
            int val2 = it2.hasNext() ? it2.next() : 0;
            // System.out.println(val1 + "+" + val2 + "+" +carrage);


            ListNode tmp = new ListNode((val1 + val2 + carrage) % 10);
            tmp.next = r3.next;
            r3.next = tmp;

            carrage = (val1 + val2 + carrage) / 10;

        }

        if (carrage != 0) {
            ListNode tmp = new ListNode(carrage);
            tmp.next = r3.next;
            r3.next = tmp;
        }

        return r3.next;
    }


    public static void main(String[] args) {
//        LinkedList<Integer> integers = new LinkedList<>();
//        integers.addFirst(1);
//        integers.addFirst(2);
//        integers.addFirst(3);
//        Iterator it = integers.iterator();
//        while (it.hasNext()) {
//            System.out.println(it.next());
//        }

        L445AddTwoNumbers test = new L445AddTwoNumbers();
        ListUtils.printList(test.addTwoNumbers(
                ListUtils.makeList(2), ListUtils.makeList(4)));

//        ListUtils.printList(test.reverseList(null));
//        ListUtils.printList(test.reverseList(ListUtils.makeList(1)));
//        ListUtils.printList(test.reverseList(ListUtils.makeList(1,2,3)));
        ListUtils.printList(test.addTwoNumbers(null, null));
        ListUtils.printList(test.addTwoNumbers(ListUtils.makeList(3), null));
        ListUtils.printList(test.addTwoNumbers(null, ListUtils.makeList(4)));
        ListUtils.printList(test.addTwoNumbers(
                ListUtils.makeList(2), ListUtils.makeList(4)));
        ListUtils.printList(test.addTwoNumbers(
                ListUtils.makeList(2, 3), ListUtils.makeList(4)));
        ListUtils.printList(test.addTwoNumbers(
                ListUtils.makeList(3), ListUtils.makeList(3, 8)));

        ListUtils.printList(test.addTwoNumbers(
                ListUtils.makeList(9,9,9,3), ListUtils.makeList(8)));
    }
}
