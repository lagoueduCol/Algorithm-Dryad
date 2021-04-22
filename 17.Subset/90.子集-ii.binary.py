# -*- coding: utf-8 -*-
#
# @lc app=leetcode.cn id=90 lang=python
#
# [90] 子集 II
#
# https://leetcode-cn.com/problems/subsets-ii/description/
#
# algorithms
# Medium (61.92%)
# Likes:    416
# Dislikes: 0
# Total Accepted:    71.4K
# Total Submissions: 115.3K
# Testcase Example:  '[1,2,2]'
#
# 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
# 
# 说明：解集不能包含重复的子集。
# 
# 示例:
# 
# 输入: [1,2,2]
# 输出:
# [
# ⁠ [2],
# ⁠ [1],
# ⁠ [1,2,2],
# ⁠ [2,2],
# ⁠ [1,2],
# ⁠ []
# ]
# 
#

# @lc code=start
class Solution(object):
    def subsetsWithDup(self, nums):
        N = 0 if not nums else len(nums)

        nums = sorted(nums)

        ans = []

        for i in range(1<<N):
            newSubset = []
            isValid = True

            # 需要检查这个i是不是合法的
            for j in range(N):
                curBit = 1 if (i & (1<<j)) != 0 else 0

                if j > 0 and nums[j] == nums[j-1]:
                    preBit = 1 if (i & (1<<(j-1))) != 0 else 0
                    if curBit == 1 and preBit == 0:
                        isValid = False
                        break
                    
                if curBit == 1:
                    newSubset.append(nums[j])

            if isValid:
                ans.append(newSubset)
        
        return ans

# @lc code=end

