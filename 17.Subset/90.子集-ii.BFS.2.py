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

        cur = [[]]

        cnt = {}
        for x in nums:
            cnt[x] = cnt.get(x, 0) + 1

        for value,number in cnt.items():
            # 我们把新生成的next的里面的item放到cur的后面
            # 所以我们需要记录一下cur当前的大小
            curSize = len(cur)

            for i in range(curSize):
                subset = cur[i]

                # 生成新的子集
                newSubset = [x for x in subset]

                # 然后利用value, number来更新subset
                for j in range(number):
                    newSubset.append(value)
                    cur.append([x for x in newSubset])
        
        return cur

# @lc code=end
