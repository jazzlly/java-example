package com.leetcode.list;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class L21MergeTwoListsTest {

    /**
     * 输入：1->2->4, 1->3->4
     * 输出：1->1->2->3->4->4
     */
    @Test
    public void test() {
        ListNode l1 = ListUtils.makeList(1, 2, 4);
        ListNode l2 = ListUtils.makeList(1, 3, 4);

        ListNode node = L21MergeTwoLists2.mergeTwoLists(l1, l2);
        assertThat(ListUtils.dumpList(node))
                .containsExactly(1, 1, 2, 3, 4, 4);
    }

    /**
     * 边界测试
     */
    @Test
    public void boundary() {
        ListNode l1 = ListUtils.makeList(1);
        assertThat(ListUtils.dumpList(
                L21MergeTwoLists2.mergeTwoLists(l1, null)))
                .containsExactly(1);

        ListNode l2 = ListUtils.makeList(2);
        assertThat(ListUtils.dumpList(
                L21MergeTwoLists2.mergeTwoLists(null, l2)))
                .containsExactly(2);
    }
}