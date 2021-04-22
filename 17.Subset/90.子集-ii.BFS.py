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

        cur = [[]]
        preBeforeUpdateSize = 0

        for i in range(N):
            value = nums[i]
            curSize = len(cur)
            startPos = 0

            if i > 0 and nums[i] == nums[i-1]:
                startPos = preBeforeUpdateSize
            
            for j in range(startPos, curSize):
                cur.append([x for x in cur[j]])
                cur[-1].append(value)
            
            preBeforeUpdateSize = curSize
            
        return cur


# @lc code=end

