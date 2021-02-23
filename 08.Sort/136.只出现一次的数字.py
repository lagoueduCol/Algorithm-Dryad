#
# @lc app=leetcode.cn id=136 lang=python
#
# [136] 只出现一次的数字
#
# https://leetcode-cn.com/problems/single-number/description/
#
# algorithms
# Easy (70.74%)
# Likes:    1674
# Dislikes: 0
# Total Accepted:    326.1K
# Total Submissions: 461K
# Testcase Example:  '[2,2,1]'
#
# 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
# 
# 说明：
# 
# 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
# 
# 示例 1:
# 
# 输入: [2,2,1]
# 输出: 1
# 
# 
# 示例 2:
# 
# 输入: [4,1,2,1,2]
# 输出: 4
# 
#

# @lc code=start
class Solution(object):
    def singleNumber(self, A):
        N = len(A) if A else 0

        def split(A, b, e):
            if b >= e:
                return 0
            if b + 1 >= e:
                return A[b]

            m = b + ((e-b)>>1)
            x = A[m]

            i = b
            l = b
            r = e - 1

            while i <= r:
                if A[i] < x:
                    A[l], A[i] = A[i], A[l]
                    i += 1
                    l += 1
                elif A[i] == x:
                    i += 1
                else:
                    A[r], A[i] = A[i], A[r]
                    r -= 1
            
            if i - l == 1:
                return A[l]
            
            if ((l - b) & 0x01) == 1:
                return split(A, b, l)
            return split(A, i, e)

        return split(A, 0, N)
# @lc code=end

