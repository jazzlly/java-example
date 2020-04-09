package com.leetcode.array.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 118. 杨辉三角
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 * 示例: 输入: 5
 * 输出:
 * [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 * ]
 */
public class L118YangHuiTrangle {
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> generate(int numRows) {
        for (int i = 0; i < numRows; i++) {
            List<Integer> line = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                line.add(getResult(i, j));
            }
            result.add(line);
        }
        return result;
    }

    private Integer getResult(int row, int col) {
        int prevRow = row - 1;
        int prevCol = col - 1;
        if (prevRow < 0 || prevCol < 0) {
            return 1;
        }

        List<Integer> prevLine = result.get(prevRow);
        int a = prevCol >= 0 ? prevLine.get(prevCol) : 0;
        int b = col < prevLine.size() ? prevLine.get(col) : 0;
        return a + b;
    }
}
