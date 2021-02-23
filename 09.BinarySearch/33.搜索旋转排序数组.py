#
# @lc app=leetcode.cn id=33 lang=python
#
# [33] 搜索旋转排序数组
#
# https://leetcode-cn.com/problems/search-in-rotated-sorted-array/description/
#
# algorithms
# Medium (40.44%)
# Likes:    1176
# Dislikes: 0
# Total Accepted:    219K
# Total Submissions: 541.4K
# Testcase Example:  '[4,5,6,7,0,1,2]\n0'
#
# 升序排列的整数数组 nums 在预先未知的某个点上进行了旋转（例如， [0,1,2,4,5,6,7] 经旋转后可能变为 [4,5,6,7,0,1,2]
# ）。
# 
# 请你在数组中搜索 target ，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
# 
# 
# 
# 示例 1：
# 
# 
# 输入：nums = [4,5,6,7,0,1,2], target = 0
# 输出：4
# 
# 
# 示例 2：
# 
# 
# 输入：nums = [4,5,6,7,0,1,2], target = 3
# 输出：-1
# 
# 示例 3：
# 
# 
# 输入：nums = [1], target = 0
# 输出：-1
# 
# 
# 
# 
# 提示：
# 
# 
# 1 
# -10^4 
# nums 中的每个值都 独一无二
# nums 肯定会在某个点上旋转
# -10^4 
# 
# 
#

# @lc code=start
class Solution(object):
    def search(self, A, x):
        N = len(A) if A else 0
        l = 0
        r = N

        while l < r:
            m = l + ((r-l)>>1)
            if A[m] == x:
                return m
            if A[l] == x:
                return l
            if A[r-1] == x:
                return r-1
            
            if A[m] > A[l]:
                if A[l] < x and x < A[m]:
                    r = m
                else:
                    l = m + 1
            else:
                if A[m] < x and x < A[r-1]:
                    l = m + 1
                else:
                    r = m
        return -1

# @lc code=end

