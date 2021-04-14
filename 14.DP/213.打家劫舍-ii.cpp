/*
 * @lc app=leetcode.cn id=213 lang=cpp
 *
 * [213] 打家劫舍 II
 *
 * https://leetcode-cn.com/problems/house-robber-ii/description/
 *
 * algorithms
 * Medium (41.35%)
 * Likes:    518
 * Dislikes: 0
 * Total Accepted:    86.3K
 * Total Submissions: 208.7K
 * Testcase Example:  '[2,3,2]'
 *
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈
 * ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 * 
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，能够偷窃到的最高金额。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [2,3,2]
 * 输出：3
 * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [1,2,3,1]
 * 输出：4
 * 解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 * 偷窃到的最高金额 = 1 + 3 = 4 。
 * 
 * 示例 3：
 * 
 * 
 * 输入：nums = [0]
 * 输出：0
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * 0 
 * 
 * 
 */

// @lc code=start
class Solution {
    int robHouse(vector<int> &A, int b, int e) {
        if (b >= e) {
            return 0;
        }

        // dp[i]表示从[b...i]这个范围里面能够得到的最大收益
        int dp[e];
        dp[b] = max(0, A[b]);

        // 如果只有一个元素
        if (e - b == 1) {
            return dp[b];
        }

        dp[b+1] = max(0, max(A[b], A[b+1]));

        for (int i = b + 2; i < e; i++) {
            dp[i] = max(dp[i-1], dp[i-2] + A[i]);
        }

        return dp[e-1];
    }
public:
    int rob(vector<int>& A) {
        // 由于这里的房子是首尾相连，那么
        // 我们需要考虑两种情况，
        // - A[0]不抢: [1...N)
        // - A[0]必然抢: 接下来只需要处理[2...N-1)
        // 那么原来的数组，实际上就变成了两个数组

        // 首先处理特殊情况
        if (A.empty()) {
            return 0;
        }

        return max(robHouse(A, 1, A.size()),
                   A[0] + robHouse(A, 2, A.size() - 1));
    }
};
// @lc code=end

