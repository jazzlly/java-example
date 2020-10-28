package com.leetcode.list;

import java.util.ArrayList;
import java.util.List;

/**
 * 234. 回文链表
 * 请判断一个链表是否为回文链表。
 *
 * 示例 1:
 *
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 *
 * 输入: 1->2->2->1
 * 输出: true
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 */
public class L234HuiwenList {
    public boolean isPalindrome1(ListNode head) {
        ListNode p = head;
        List<Integer> list = new ArrayList<>();

        while (p != null) {
            list.add(p.val);
            p = p.next;
        }

        for (int i = 0; i < list.size() / 2; i++) {
            if (!list.get(i).equals(list.get(list.size() - i - 1))) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome2(ListNode head) {
        // todo: merge condition
        if (head == null || head.next == null) {
            return true;
        }

        ListNode p = head;
        int size = 0;

        while (p != null) {
            size++;
            p = p.next;
        }

        p = head;
        ListNode q = head;
        for (int i = 0; i < (size + 1)/2; i++) {
            q = q.next;
        }

        q = reverseList(q);

        for (int i = 0; i < size/2; i++) {
            if (p.val != q.val) {
                return false;
            }
            p = p.next;
            q = q.next;
        }
        return true;
    }

    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode next = null;

        while (head != null) {
            next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }

        return prev;
    }

    /**
     * 快慢指针方法， 通过慢指针反转前半部分链表
     * 然后前后两半链表遍历比较
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        ListNode prev = null;
        ListNode next = null;

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;

            // 反转slow
            next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;
        }

        if (fast != null && next != null) {
            // 基数节点场景, 忽略掉中位节点
            next = next.next;
        }

        fast = next;
        slow = prev;
        while (slow != null && fast != null) {
            if (slow.val != fast.val) {
                return false;
            }
            slow = slow.next;
            fast = fast.next;
        }
        return true;
    }
}
