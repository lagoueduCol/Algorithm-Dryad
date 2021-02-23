/*
 * @lc app=leetcode.cn id=845 lang=cpp
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
 * 存在 0 < i < B.length - 1 使得 B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ...
 * > B[B.length - 1]
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

#include <bits/stdc++.h>

// @lc code=start
class Solution {
   public:
    int longestMountain(vector<int>& A) {
        const int N = A.size();
        if (N < 3) {
            return 0;
        }

        int left = -1;
        // -1表示只有一个元素
        // 0表示正上升
        // 1表示正下降
        int status = -1;
        int preValue = A[0];
        int ans = 0;

        // 题目要求必须至少有3个元素，所以不可能从0开始
        for (int i = 1; i < N; i++) {
            auto x = A[i];

            // 如果要把x加进来
            // 如果里面还只有一个元素
            if (status == -1) {
                if (x > preValue) {
                    // 那么状态改为上升
                    status = 0;
                } else {
                    // 如果相等，或者变小，那么区间只能再变成只有一个元素的了
                    // 状态依然更新为只有一个元素
                    status = -1;
                    // 区间更新为(left, i]
                    left = i - 1;
                }
            }
            // 如果正在上升
            else if (0 == status) {
                if (x > preValue) {
                    // nothing
                } else if (x == preValue) {
                    // 如果相等，那么区间只能再变成只有一个元素的状态了
                    status = -1;
                    left = i - 1;
                } else {
                    // 下降了
                    status = 1;
                }
            }
            // 如果正在下降
            else {
                if (x < preValue) {
                    // nothing
                } else if (x == preValue) {
                    status = -1;
                    left = i - 1;
                } else {
                    // 如果正在上升
                    status = 0;
                    // 注意这里left要变成
                    // (i - 2, i]
                    // 这里已经有两个元素了
                    left = i - 2;
                }
            }

            preValue = x;
            if (status == 1) {
                ans = max(ans, i - left);
            }
        }

        return ans >= 3 ? ans : 0;
    }
};
// @lc code=end
