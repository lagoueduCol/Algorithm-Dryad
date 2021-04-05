#
# @lc app=leetcode.cn id=322 lang=python
#
# [322] 零钱兑换
#
# https://leetcode-cn.com/problems/coin-change/description/
#
# algorithms
# Medium (42.96%)
# Likes:    1160
# Dislikes: 0
# Total Accepted:    201.9K
# Total Submissions: 469.8K
# Testcase Example:  '[1,2,5]\n11'
#
# 给定不同面额的硬币 coins 和一个总金额
# amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
# 
# 你可以认为每种硬币的数量是无限的。
# 
# 
# 
# 示例 1：
# 
# 
# 输入：coins = [1, 2, 5], amount = 11
# 输出：3 
# 解释：11 = 5 + 5 + 1
# 
# 示例 2：
# 
# 
# 输入：coins = [2], amount = 3
# 输出：-1
# 
# 示例 3：
# 
# 
# 输入：coins = [1], amount = 0
# 输出：0
# 
# 
# 示例 4：
# 
# 
# 输入：coins = [1], amount = 1
# 输出：1
# 
# 
# 示例 5：
# 
# 
# 输入：coins = [1], amount = 2
# 输出：2
# 
# 
# 
# 
# 提示：
# 
# 
# 1 
# 1 
# 0 
# 
# 
#

# @lc code=start
class Solution(object):
    def coinChange(self, coins, amount):
        # 最大取整数的最大值, 防止溢出 / 4
        # python 不会溢出，但是小一点的数可以不触发
        # 大数运算
        INF = 2147483647 / 4

        # 首先设置所有的数都不可解
        dp = [INF] * (amount + 1)

        # 设置初始条件
        dp[0] = 0

        # 开始正向推导
        for i in range(0, amount+1):
            for y in coins:
                if y <= amount and i + y < amount + 1:
                    dp[i + y] = min(dp[i+y], dp[i]+1)
        
        return -1 if dp[amount] >= INF else dp[amount]
# @lc code=end

