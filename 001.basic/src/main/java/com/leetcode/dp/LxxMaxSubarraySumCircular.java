package com.leetcode.dp;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 环形子组数的最大和
 * 给定一个由整数数组 A 表示的环形数组 C，求 C 的非空子数组的最大可能和。
 *
 * 在此处，环形数组意味着数组的末端将会与开头相连呈环状。（形式上，当0 <= i < A.length 时 C[i] = A[i]，且当 i >= 0 时 C[i+A.length] = C[i]）
 *
 * 此外，子数组最多只能包含固定缓冲区 A 中的每个元素一次。（形式上，对于子数组 C[i], C[i+1], ..., C[j]，不存在 i <= k1, k2 <= j 其中 k1 % A.length = k2 % A.length）
 *
 *  
 *
 * 示例 1：
 *
 * 输入：[1,-2,3,-2]
 * 输出：3
 * 解释：从子数组 [3] 得到最大和 3
 * 示例 2：
 *
 * 输入：[5,-3,5]
 * 输出：10
 * 解释：从子数组 [5,5] 得到最大和 5 + 5 = 10
 * 示例 3：
 *
 * 输入：[3,-1,2,-1]
 * 输出：4
 * 解释：从子数组 [2,-1,3] 得到最大和 2 + (-1) + 3 = 4
 * 示例 4：
 *
 * 输入：[3,-2,2,-3]
 * 输出：3
 * 解释：从子数组 [3] 和 [3,-2,2] 都可以得到最大和 3
 * 示例 5：
 *
 * 输入：[-2,-3,-1]
 * 输出：-1
 * 解释：从子数组 [-1] 得到最大和 -1
 *  
 *
 * 提示：
 *
 * -30000 <= A[i] <= 30000
 * 1 <= A.length <= 30000
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/dynamic-programming-1-plus/5r2pah/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class LxxMaxSubarraySumCircular {

    public int maxSubarraySumCircular1(int[] A) {
        if (A.length == 1) {
            return A[0];
        }

        int[] array = new int[A.length * 2 - 1];
        System.arraycopy(A, 0, array, 0, A.length);
        System.arraycopy(A, 0, array, A.length, A.length -1);

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < A.length; i++) {
            max = Math.max(max, maxSubArray(array, i, i + A.length));
        }
        return max;
    }

    private int maxSubArray(int[] nums, int begin, int end) {
        int dp = nums[begin];
        int max = dp;
        for (int i = begin + 1; i < end; i++) {
            dp = Math.max(nums[i], dp + nums[i]);
            max = Math.max(max, dp);
        }

        return max;
    }


    public int maxSubarraySumCircular(int[] A) {
        if (A.length == 1) {
            return A[0];
        }

        int dp = -1;
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            dp = A[i] + Math.max(0, dp);
            max = Math.max(max, dp);
            sum += A[i];
        }

        dp = 1;
        int min = max;
        for (int i = 1; i < A.length - 1; i++) {
            dp = Math.min(A[i], dp + A[i]);
            min = Math.min(min, dp);
        }

        return Math.max(max, sum - min);
    }

    public static void main(String[] args) {
        LxxMaxSubarraySumCircular test = new LxxMaxSubarraySumCircular();
        assertThat(test.maxSubarraySumCircular(new int[] {1})).isEqualTo(1);
        assertThat(test.maxSubarraySumCircular(new int[] {-1})).isEqualTo(-1);
        assertThat(test.maxSubarraySumCircular(new int[] {1,-2,3,-2})).isEqualTo(3);
        assertThat(test.maxSubarraySumCircular(new int[] {5,-3,5})).isEqualTo(10);
        assertThat(test.maxSubarraySumCircular(new int[] {3,-1,2,-1})).isEqualTo(4);
        assertThat(test.maxSubarraySumCircular(new int[] {3,-2,2,-3})).isEqualTo(3);
        assertThat(test.maxSubarraySumCircular(new int[] {-2,-3,-1})).isEqualTo(-1);
        assertThat(test.maxSubarraySumCircular(new int[] {5, 6, 7})).isEqualTo(18);
    }
}
