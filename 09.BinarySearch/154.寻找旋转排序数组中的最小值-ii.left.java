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
        // 保证区间中至少有2个元素
        while (l < r) {
            final int m = l + ((r - l) >> 1);
            // 如果A[m] < A[l]
            // 那么A[m]掉在了右边的区间
            // 此时[m + 1, r]这个区间可以不要了
            if (A[m] < A[l]) {
                r = m;
            } else if (A[m] > A[l]) {
                // 当A[m] > A[l]的时候
                // A[m]掉在左边的区间
                // 但是，此时还有三种情况需要处理
                // 1. A[l] < A[r]
                // 2. A[l] == A[r]
                // 3. A[l] > A[r]

                // 我们可以扔掉右边[m+1,r]
                if (A[l] < A[r]) {
                    r = m;
                } else {
                    // 有重复，以及左边较大的情况
                    // 把A[l]扔掉
                    l++;
                }
            } else {
                // 此时A[m] == A[l]
                // 有两种情况
                // 1. m == l
                // 2. m != l
                if (m == l) {
                    // 说明此时最多只有两个数。比如
                    // A[0], A[1]. m = 0
                    // 那么只能谁小移动谁
                    if (A[l] < A[r]) {
                        r--;
                    } else if (A[l] >= A[r]) {
                        l++;
                    }
                } else {
                    // 不相等，但是A[m] == A[l]
                    l++;
                }
            }
        }
        return A[l];
    }
}
// @lc code=end

