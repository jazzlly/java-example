package com.leetcode.list;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}