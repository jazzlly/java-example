package com.leetcode.list;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class L1171RemoveNode {


    public ListNode removeZeroSumSublists(ListNode head) {
        ListNode tmpHead = head;

        boolean removeNode = false;
        do {
            Set<Integer> sumSet = new HashSet<>();
            Map<Integer, ListNode> sumMap = new HashMap<>();

            int sum = 0;
            ListNode p = tmpHead;
            removeNode = false;

            while (p != null) {
                sum = sum + p.val;
                if (sum == 0) {
                    tmpHead = p.next; // 删除前面所有节点
                    removeNode = true;
                } else if (sumSet.contains(sum)) {
                    ListNode tmp = sumMap.get(sum);
                    tmp.next = p.next;
                    removeNode = true;
                } else {
                    sumSet.add(sum);
                    sumMap.put(sum, p);
                }
                p = p.next;
            }
        } while (removeNode);

        return tmpHead;
    }
}
