#
# @lc app=leetcode.cn id=416 lang=python
#
# [416] 分割等和子集
#
# https://leetcode-cn.com/problems/partition-equal-subset-sum/description/
#
# algorithms
# Medium (49.67%)
# Likes:    739
# Dislikes: 0
# Total Accepted:    115.5K
# Total Submissions: 232.4K
# Testcase Example:  '[1,5,11,5]'
#
# 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
# 
# 注意:
# 
# 
# 每个数组中的元素不会超过 100
# 数组的大小不会超过 200
# 
# 
# 示例 1:
# 
# 输入: [1, 5, 11, 5]
# 
# 输出: true
# 
# 解释: 数组可以分割成 [1, 5, 5] 和 [11].
# 
# 
# 
# 
# 示例 2:
# 
# 输入: [1, 2, 3, 5]
# 
# 输出: false
# 
# 解释: 数组不能分割成两个元素和相等的子集.
# 
# 
# 
# 
#

# @lc code=start
class Solution(object):
    def canPartition(self, A):
        N = 0 if not A else len(A)
        s = sum(A)
        
        if (s % 2) == 1:
            return False

        # V表示0/1背包中我们需要填充的容量
        V = s // 2
        dp = [False]*(V+1)
        dp[0] = True

        for x in A:
            for node in range(V,x-1,-1):
                oldNode = node - x
                newNode = node
                if dp[oldNode]:
                    dp[newNode] = True
        
        return dp[V]
# @lc code=end

