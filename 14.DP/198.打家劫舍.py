#
# @lc app=leetcode.cn id=198 lang=python
#
# [198] 打家劫舍
#
# https://leetcode-cn.com/problems/house-robber/description/
#
# algorithms
# Medium (48.32%)
# Likes:    1358
# Dislikes: 0
# Total Accepted:    265.5K
# Total Submissions: 549.4K
# Testcase Example:  '[1,2,3,1]'
#
# 
# 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
# 
# 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
# 
# 
# 
# 示例 1：
# 
# 输入：[1,2,3,1]
# 输出：4
# 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
# 偷窃到的最高金额 = 1 + 3 = 4 。
# 
# 示例 2：
# 
# 输入：[2,7,9,3,1]
# 输出：12
# 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
# 偷窃到的最高金额 = 2 + 9 + 1 = 12 。
# 
# 
# 
# 
# 提示：
# 
# 
# 0 <= nums.length <= 100
# 0 <= nums[i] <= 400
# 
# 
#

# @lc code=start
class Solution(object):
    def rob(self, nums):
        N = 0 if not nums else len(nums)
        if N == 0:
            return 0

        dp = [0] * N
        dp[0] = max(0, nums[0])
        if N == 1:
            return dp[0]

        dp[1] = max(0, max(nums[0], nums[1]))

        for i in range(2, N):
            dp[i] = max(dp[i-1], dp[i-2] + nums[i])

        return dp[N-1]
# @lc code=end


