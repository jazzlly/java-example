package com.leetcode.dp;

public class BinarySearchTest {

    public static void main(String[] args) {

        //                      0  1  2  3  4  5   6
        int[] nums = new int[] {0, 2, 4, 6, 8, 10, 12};
        int target = 13;

        int l = 0;
        int r = nums.length;
        while (l < r) {
            int m = l + (r-l)/2;
            System.out.println(l+":" + m + ":" + r);

            if (nums[m] == target) {
                System.out.println("found: " + target + "in index: " + m);
                l = m + 1;
                break;
            }

            if (target > nums[m]) {
                l = m + 1;
            }
            if (target < nums[m]) {
                r = m;
            }
        }

        System.out.println("done!");
        System.out.println(l+":" + ":" + r);
    }
}
