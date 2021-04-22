#
# @lc app=leetcode.cn id=78 lang=python
#
# [78] 子集
#
# https://leetcode-cn.com/problems/subsets/description/
#
# algorithms
# Medium (79.61%)
# Likes:    1061
# Dislikes: 0
# Total Accepted:    212.1K
# Total Submissions: 266.5K
# Testcase Example:  '[1,2,3]'
#
# 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
# 
# 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
# 
# 
# 
# 示例 1：
# 
# 
# 输入：nums = [1,2,3]
# 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
# 
# 
# 示例 2：
# 
# 
# 输入：nums = [0]
# 输出：[[],[0]]
# 
# 
# 
# 
# 提示：
# 
# 
# 1 
# -10 
# nums 中的所有元素 互不相同
# 
# 
#

# @lc code=start
class Solution(object):
    def subsets(self, nums):
        N = 0 if not nums else len(nums)

        ans = []

        for i in range((1<<N)):

            ans.append([])
            for j in range(N):
                mask = 1 << j

                if ((i & mask) != 0):
                    ans[-1].append(nums[j])
        
        return ans

# @lc code=end

