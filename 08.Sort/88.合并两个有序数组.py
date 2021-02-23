#
# @lc app=leetcode.cn id=88 lang=python
#
# [88] 合并两个有序数组
#
# https://leetcode-cn.com/problems/merge-sorted-array/description/
#
# algorithms
# Easy (49.20%)
# Likes:    745
# Dislikes: 0
# Total Accepted:    259.6K
# Total Submissions: 527.7K
# Testcase Example:  '[1,2,3,0,0,0]\n3\n[2,5,6]\n3'
#
# 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
# 
# 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设 nums1 的空间大小等于 m + n，这样它就有足够的空间保存来自
# nums2 的元素。
# 
# 
# 
# 示例 1：
# 
# 
# 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
# 输出：[1,2,2,3,5,6]
# 
# 
# 示例 2：
# 
# 
# 输入：nums1 = [1], m = 1, nums2 = [], n = 0
# 输出：[1]
# 
# 
# 
# 
# 提示：
# 
# 
# nums1.length == m + n
# nums2.length == n
# 0 
# 1 
# -10^9 
# 
# 
#

# @lc code=start
class Solution(object):
    def merge(self, A, m, B, n):
        """
        :type nums1: List[int]
        :type m: int
        :type nums2: List[int]
        :type n: int
        :rtype: None Do not return anything, modify nums1 in-place instead.
        """
        tail = n + m - 1
        i = m - 1
        j = n - 1
        while (i >= 0 or j >= 0):
            if (j < 0 or (i >= 0 and A[i] >= B[j])):
                A[tail] = A[i]
                tail -= 1
                i -= 1
            else:
                A[tail] = B[j]
                tail -= 1
                j -= 1
# @lc code=end

