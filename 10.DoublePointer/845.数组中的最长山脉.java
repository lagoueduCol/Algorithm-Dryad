/*
 * @lc app=leetcode.cn id=845 lang=java
 *
 * [845] 数组中的最长山脉
 *
 * https://leetcode-cn.com/problems/longest-mountain-in-array/description/
 *
 * algorithms
 * Medium (41.70%)
 * Likes:    174
 * Dislikes: 0
 * Total Accepted:    33.3K
 * Total Submissions: 79.9K
 * Testcase Example:  '[2,1,4,7,3,2,5]'
 *
 * 我们把数组 A 中符合下列属性的任意连续子数组 B 称为 “山脉”：
 * 
 * 
 * B.length >= 3
 * 存在 0 < i < B.length - 1 使得 B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... >
 * B[B.length - 1]
 * 
 * 
 * （注意：B 可以是 A 的任意子数组，包括整个数组 A。）
 * 
 * 给出一个整数数组 A，返回最长 “山脉” 的长度。
 * 
 * 如果不含有 “山脉” 则返回 0。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：[2,1,4,7,3,2,5]
 * 输出：5
 * 解释：最长的 “山脉” 是 [1,4,7,3,2]，长度为 5。
 * 
 * 
 * 示例 2：
 * 
 * 输入：[2,2,2]
 * 输出：0
 * 解释：不含 “山脉”。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 0 <= A.length <= 10000
 * 0 <= A[i] <= 10000
 * 
 * 
 */

// @lc code=start
class Solution {
    public int longestMountain(int[] A) {
        final int N = A == null ? 0 : A.length;
        if (N < 3) {
            return 0;
        }

        int left = -1;

        // 区间的信息状态
        // 1. 前面一个值的大小
        int preValue = A[0];
        // 2. 区间的状态
        int status = -1; // -1表示只有一个元素，0表示上升, 1表示下降
        // 一个有效的区间，里面应该只有一个山峰

        // 记录最长的区间
        int ans = 0;

        for (int i = 1; i < N; i++) {
            final int x = A[i];

            // 如果只有一个元素
            if (status == -1) {
                if (x > preValue) {
                    status = 0;
                } else {
                    left = i - 1;
                }
            } else {
                // 当前状态正在上升
                if (status == 0) {
                    if (x > preValue) {
                        // nothing
                    } else if (x == preValue) {
                        status = -1;
                        left = i - 1;
                    } else {
                        // 状态变成下降
                        status = 1;
                    }
                } else {
                    // 当前状态正在下降
                    if (x < preValue) {
                        // nothing
                    } else if (x == preValue) {
                        status = -1;
                        left = i - 1;
                    } else {
                        // 值又变大
                        left = i - 2;
                        status = 0;
                    }
                }
            }
            preValue = x;

            if (status == 1) {
                ans = Math.max(ans, i - left);
            }
        }

        // 要求长度至少为3
        return ans >= 3 ? ans : 0;

    }
}
// @lc code=end

