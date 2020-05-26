package com.leetcode.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class L836RectIntection {
    public boolean isRectangleOverlap1(int[] rec1, int[] rec2) {

        // 如果x, y轴没有任何交点，则面积为0
        if (rec1[2] < rec2[0] || rec2[2] < rec1[0] ||
            rec1[3] < rec2[1] || rec2[3] < rec1[1]) {
            return false;
        }

        // x轴点排序后, 第3个点坐标减去第2个点坐标为相交矩形的宽度
        // y轴点排序后, 第3个点坐标减去第2个点坐标为相交矩形的高度
        List<Integer> xlist = Arrays.asList(rec1[0], rec1[2], rec2[0], rec2[2]);
        List<Integer> ylist = Arrays.asList(rec1[1], rec1[3], rec2[1], rec2[3]);
        Collections.sort(xlist);
        Collections.sort(ylist);
        
        long width = xlist.get(2) - xlist.get(1);
        long height = ylist.get(2) - ylist.get(1);
        return width * height > 0L;        
    }

    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        // 如果x, y轴没有任何交点，或只有一个交点, 则面积为0
        return !(rec1[2] <= rec2[0] || rec2[2] <= rec1[0] ||
            rec1[3] <= rec2[1] || rec2[3] <= rec1[1]);
    }
    
    /**
     * 矩形 rec1 和 rec2 的水平边投影到 xx 轴上的线段分别为 (rec1[0], rec1[2]) 
     * 和 (rec2[0], rec2[2])。根据数学知识我们可以知道，
     * 当 min(rec1[2], rec2[2]) > max(rec1[0], rec2[0]) 时，这两条线段有交集。
     * 对于矩形 rec1 和 rec2 的竖直边投影到 yy 轴上的线段，同理可以得到，
     * 当 min(rec1[3], rec2[3]) > max(rec1[1], rec2[1]) 时，这两条线段有交集。
         */
    public boolean isRectangleOverlap2(int[] rec1, int[] rec2) {
        return (Math.min(rec1[2], rec2[2]) > Math.max(rec1[0], rec2[0]) &&
                Math.min(rec1[3], rec2[3]) > Math.max(rec1[1], rec2[1]));
    }

    public static void main(String[] args) {
        L836RectIntection test = new L836RectIntection();

        /*
        System.out.println(test.isRectangleOverlap(
            new int[] {0,0,2,2}, new int[] {1,1,3,3}));

        System.out.println(test.isRectangleOverlap(
            new int[] {0,0,1,1}, new int[] {1,0,2,1}));
        */

            // [-193634870,-175701756,958697367,607619635]
// [91619846,10349052,822028072,696611776]
        System.out.println(test.isRectangleOverlap(
            new int[] {-193634870,-175701756,958697367,607619635}, 
            new int[] {91619846,10349052,822028072,696611776}));
    }
}