#
# @lc app=leetcode.cn id=34 lang=python
#
# [34] 在排序数组中查找元素的第一个和最后一个位置
#
# https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/
#
# algorithms
# Medium (42.17%)
# Likes:    826
# Dislikes: 0
# Total Accepted:    203.3K
# Total Submissions: 482.1K
# Testcase Example:  '[5,7,7,8,8,10]\n8'
#
# 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
# 
# 如果数组中不存在目标值 target，返回 [-1, -1]。
# 
# 进阶：
# 
# 
# 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
# 
# 
# 
# 
# 示例 1：
# 
# 
# 输入：nums = [5,7,7,8,8,10], target = 8
# 输出：[3,4]
# 
# 示例 2：
# 
# 
# 输入：nums = [5,7,7,8,8,10], target = 6
# 输出：[-1,-1]
# 
# 示例 3：
# 
# 
# 输入：nums = [], target = 0
# 输出：[-1,-1]
# 
# 
# 
# 提示：
# 
# 
# 0 
# -10^9 
# nums 是一个非递减数组
# -10^9 
# 
# 
#

# @lc code=start
class Solution(object):
    def searchRange(self, A, target):
        """
        :type A: List[int]
        :type target: int
        :rtype: List[int]
        """

        if not A or len(A) == 0:
            return [-1,-1]
        
        N = len(A)

        def lower_bound(A, target):
            l = 0
            r = len(A)
            while l < r:
                m = l + ((r-l)>>1)
                if A[m] < target:
                    l = m + 1
                else:
                    r = m
            return l
        
        def upper_bound(A, target):
            l = 0
            r = len(A)
            while l < r:
                m = l + ((r-l)>>1)
                if A[m] <= target:
                    l = m + 1
                else:
                    r = m
            return l
        
        l = lower_bound(A, target)
        r = upper_bound(A, target)
        if l == r:
            return [-1,-1]
        return [l, r-1]
# @lc code=end

