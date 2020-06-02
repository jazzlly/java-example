package com.leetcode.list;

import java.util.HashSet;
import java.util.Set;

/**
 * 142. 环形链表 II
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 *  如果 pos 是 -1，则在该链表中没有环
 * 
 * 说明：不允许修改给定的链表。
 */
public class L142DetectListCycle {

    public ListNode detectCycle1(ListNode head) {
        ListNode current = head;

        // 思路1: 添加一个set, 将node保存起来
        Set<ListNode> nodeSet = new HashSet<>();
        while (current != null) {
            if (nodeSet.contains(current)) {
                return current;
            }
            nodeSet.add(current);
            current = current.next;
        }
        return null;
    }
    
    /**
     * 两阶段寻找：
     * 1. 判断是否有环， 过程参见题目141
     * 2. 将快指针移动到head， 然后每次快慢指针都移动一次
     *  两个指针相遇的节点，就是入口节点
     * 证明： 假设链表长度为 a + b, a为无环部分，b为环形部分
     *  快慢指针相遇时， 快指针走过f步，慢指针走过s步
     *      f = a + mb (1)
     *      s = a + nb (2)
     *      f = 2s
     * 
     *      (1) - (2) 的到
     *      s = (m-n)b  = xb
     *      f = 2xb
     * 
     *      s在向前走a步的话，即走a + xb步， 就到了链表入环的那一点了
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head.next;
        boolean foundCycle = false;
        while (slow != null && fast != null) {
            if (slow == fast) {
                // 第二轮相遇结束
                if (foundCycle) {
                    return slow;
                }
                // 开始第二轮相遇
                foundCycle = true;
                fast = head;
                slow = slow.next; // jiangrui, errorList: 没有考虑边界条件
                                  // fast移动链表头部后，slow也要向前移动一格
                continue;
            }

            slow = slow.next;
            fast = fast.next;
            if (fast != null && !foundCycle) {
                fast = fast.next;
            }
        }

        return null;
    }

}