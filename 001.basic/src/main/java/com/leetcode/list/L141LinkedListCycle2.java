package com.leetcode.list;

/**
 * 给定一个链表，判断链表中是否有环。
 */
public class L141LinkedListCycle2 {

    /**
     * 思路0： 硬做，设定1秒钟，如果没有找到null。就判定有环
     *
     * 思路1: 创建一个hashset, 将每个节点都加入到这个hashset中
     * 遍历节点时，判断当前节点是否在hashset中
     *
     * 思路2：在节点中创建一个标志位，表示节点是否遍历过
     *
     * 思路3：创建两个遍历指针，一个每次前进1步，一个每次前进2步
     * 看看第二个指针是否能够追上第一个指针
     *
     * 证明； 将问题转化为一个连续的追击问题
     * 假设一个有n个节点的有环链表。slow指针每分钟走一个节点，fast指针每分钟走两个节点。
     * 考虑t分钟之后，fast指针能够追上slow指针。
     *  假设 t分钟之后，slow走过t个节点。fast走过2t个节点，如果他们相遇：
     *  则方程 2t = t + n有整数解。
     *  显然 t = n 就是该方程的整数解
     */

    /**
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        ListNode p = head;
        ListNode q = head;

        while (p != null && q != null) {
            p = p.next;
            q = q.next;
            if (q != null) {
                q = q.next;
            } else {
                break;
            }

            if (p == q) {
                return true;
            }
        }

        return false;
    }
}
