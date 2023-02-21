package com.leetcode.list;

import org.junit.Test;

import static org.junit.Assert.*;

public class LOF22GetKthFromEndTest {

    @Test
    public void smoke() {
        ListNode listNode = ListUtils.makeList(1, 2, 3, 4, 5, 6);
        ListNode node = LOF22GetKthFromEnd2.getKthFromEnd(listNode, 6);

        ListUtils.printList(node);

    }
}