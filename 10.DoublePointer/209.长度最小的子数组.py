#
# @lc app=leetcode.cn id=209 lang=python
#
# [209] 长度最小的子数组
#
# https://leetcode-cn.com/problems/minimum-size-subarray-sum/description/
#
# algorithms
# Medium (44.84%)
# Likes:    539
# Dislikes: 0
# Total Accepted:    110.2K
# Total Submissions: 245.9K
# Testcase Example:  '7\n[2,3,1,2,4,3]'
#
# 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的 连续
# 子数组，并返回其长度。如果不存在符合条件的子数组，返回 0。
# 
# 
# 
# 示例：
# 
# 输入：s = 7, nums = [2,3,1,2,4,3]
# 输出：2
# 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
# 
# 
# 
# 
# 进阶：
# 
# 
# 如果你已经完成了 O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。
# 
# 
#

# @lc code=start
class Solution(object):
    def minSubArrayLen(self, s, A):
        N = 0 if not A else len(A)
        left = -1
        sum = 0
        ans = -1
        for i in range(N):
            sum += A[i]
            while left < i and sum >= s:
                if ans == -1 or ans > i - left:
                    ans = i - left
                left += 1
                sum -= A[left]
        
        return max(ans, 0)

# @lc code=end

