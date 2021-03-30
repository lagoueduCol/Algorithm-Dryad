#
# @lc app=leetcode.cn id=713 lang=python
#
# [713] 乘积小于K的子数组
#
# https://leetcode-cn.com/problems/subarray-product-less-than-k/description/
#
# algorithms
# Medium (37.56%)
# Likes:    224
# Dislikes: 0
# Total Accepted:    12.1K
# Total Submissions: 32.2K
# Testcase Example:  '[10,5,2,6]\n100'
#
# 给定一个正整数数组 nums。
# 
# 找出该数组内乘积小于 k 的连续的子数组的个数。
# 
# 示例 1:
# 
# 
# 输入: nums = [10,5,2,6], k = 100
# 输出: 8
# 解释: 8个乘积小于100的子数组分别为: [10], [5], [2], [6], [10,5], [5,2], [2,6], [5,2,6]。
# 需要注意的是 [10,5,2] 并不是乘积小于100的子数组。
# 
# 
# 说明:
# 
# 
# 0 < nums.length <= 50000
# 0 < nums[i] < 1000
# 0 <= k < 10^6
# 
# 
#

# @lc code=start
class Solution(object):
    def numSubarrayProductLessThanK(self, A, k):
        N = 0 if not A else len(A)
        s = 1
        left = -1
        ans = 0

        for i in range(N):
            s *= A[i]
            while s >= k and left < i:
                left += 1
                s /= A[left]
            
            ans += i - left
        
        return ans
        
# @lc code=end

