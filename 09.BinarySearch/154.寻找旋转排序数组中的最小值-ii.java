/*
 * @lc app=leetcode.cn id=154 lang=java
 *
 * [154] 寻找旋转排序数组中的最小值 II
 */

// @lc code=start
class Solution {
    public int findMin(int[] A) {
        final int N = A == null ? 0 : A.length;
        // 由于要使用A[r-1]
        // 这里直接用了闭区间[l, r]
        int l = 0, r = N - 1;
        // 保证区间中至少有一个元素
        while (l < r) {
            final int m = l + ((r - l) >> 1);
            // 如果A[m] < A[r]
            // 表示A[m]掉在了右边的区间
            // 那么此时[m + 1, r]可以不要了
            if (A[m] < A[r]) {
                // 保留区间[l, m]
                r = m;
            } else if (A[m] > A[r]) {
                // 当A[m] > A[r]时
                // A[m]掉在左边的区间
                // 此时[l, m]可以不要了
                // 保留区间[m + 1, r]
                l = m + 1;
            } else {
                // 如果与右边元素相等，此时r与m不相等的
                // 那么扔掉A[r]
                r--;
            }
        }
        return A[l];
    }
}
// @lc code=end

