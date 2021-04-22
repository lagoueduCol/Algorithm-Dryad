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

    # 这里将两个子集合并成一个
    def merge(self, x, y):
        ans = []
        for v in x:
            ans.append(v)

        for v in y:
            ans.append(v)
        
        return ans

    # 这里处理的是区间[b, e)
    # 左闭右开
    def dq(self, nums, b, e):
        ans = [[]]
        if b >= e:
            return ans
        
        if b + 1 == e:
            ans.append([nums[b]])
            return ans
        
        mid = b + ((e-b)>>1)

        l = self.dq(nums, b, mid)
        r = self.dq(nums, mid, e)

        for x in l:
            for y in r:
                if len(x) == 0 and len(y) == 0:
                    continue
                ans.append(self.merge(x, y))
        
        return ans

    def subsets(self, nums):
        N = 0 if not nums else len(nums)
        return self.dq(nums, 0, N)

# @lc code=end

