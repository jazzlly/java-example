package com.leetcode.array.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 119. 杨辉三角 II
 * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
 *
 * 示例:
 * 输入: 3
 * 输出: [1,3,3,1]
 * 进阶：
 *
 * 你可以优化你的算法到 O(k) 空间复杂度吗？
 *  [1],
 *  [1,1],
 *  [1,2,1],
 *  [1,3,3,1],
 *  [1,4,6,4,1]
 */
public class L119YangHuiTrangle {

    public List<Integer> getRow(int rowIndex) {
        int[] array = new int[rowIndex + 1];
        for (int i = 0; i <= rowIndex; i++) {
            array[i] = 1;
            for (int j = i - 1; j > 0; j--) {
                array[j] = array[j] + array[j - 1];
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            list.add(array[i]);
        }
        return list;
    }
}

