/*
 * @lc app=leetcode.cn id=713 lang=cpp
 *
 * [713] 乘积小于K的子数组
 *
 * https://leetcode-cn.com/problems/subarray-product-less-than-k/description/
 *
 * algorithms
 * Medium (37.56%)
 * Likes:    224
 * Dislikes: 0
 * Total Accepted:    12.1K
 * Total Submissions: 32.2K
 * Testcase Example:  '[10,5,2,6]\n100'
 *
 * 给定一个正整数数组 nums。
 *
 * 找出该数组内乘积小于 k 的连续的子数组的个数。
 *
 * 示例 1:
 *
 *
 * 输入: nums = [10,5,2,6], k = 100
 * 输出: 8
 * 解释: 8个乘积小于100的子数组分别为: [10], [5], [2], [6], [10,5], [5,2],
 * [2,6], [5,2,6]。 需要注意的是 [10,5,2] 并不是乘积小于100的子数组。
 *
 *
 * 说明:
 *
 *
 * 0 < nums.length <= 50000
 * 0 < nums[i] < 1000
 * 0 <= k < 10^6
 *
 *
 */

#include <bits/stdc++.h>

// @lc code=start
class Solution {
   public:
    int numSubarrayProductLessThanK(vector<int>& A, int k) {
        const int N = A.size();

        int left = -1;
        uint64_t s = 1;
        int ans = 0;
        for (int i = 0; i < N; i++) {
            const uint64_t x = A[i];
            s *= x;

            // 坏了才移动
            while (s >= k && left < i) {
                s /= A[++left];
            }

            // 计算子数组的长度
            const int len = i - left;
            // 那么以x 为右端点的区间的个数就是
            ans += len;
        }

        return ans;
    }
};
// @lc code=end
