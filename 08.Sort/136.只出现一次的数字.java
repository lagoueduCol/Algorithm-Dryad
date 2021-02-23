/*
 * @lc app=leetcode.cn id=136 lang=java
 *
 * [136] 只出现一次的数字
 *
 * https://leetcode-cn.com/problems/single-number/description/
 *
 * algorithms
 * Easy (70.74%)
 * Likes:    1674
 * Dislikes: 0
 * Total Accepted:    326.1K
 * Total Submissions: 461K
 * Testcase Example:  '[2,2,1]'
 *
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 
 * 说明：
 * 
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * 
 * 示例 1:
 * 
 * 输入: [2,2,1]
 * 输出: 1
 * 
 * 
 * 示例 2:
 * 
 * 输入: [4,1,2,1,2]
 * 输出: 4
 * 
 */

// @lc code=start
class Solution {
    private void swap(int[] A, int i, int j) {
        int t = A[i];
        A[i] = A[j];
        A[j] = t;
    }

    private int threeSplit(int[] A, int b, int e) {
        if (b >= e) {
            return 0;
        }
        final int m = b + ((e-b)>>1);
        final int x = A[m];

        int l = b;
        int i = b;
        int r = e - 1;

        while (i <= r) {
            if (A[i] < x) {
                swap(A, l++, i++);
            } else if (A[i] == x) {
                i++;
            } else {
                swap(A, r--, i);
            }
        }

        if (i - l == 1) return A[l];
        if (((l - b) & 0x01) == 1) {
            return threeSplit(A, b, l);
        }
        return threeSplit(A, i, e);
    }
    public int singleNumber(int[] A) {
        if (A == null || A.length <= 0) {
            return 0;
        }

        return threeSplit(A, 0, A.length);
    }
}
// @lc code=end

