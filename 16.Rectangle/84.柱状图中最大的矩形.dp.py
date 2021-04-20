#
# @lc app=leetcode.cn id=84 lang=python
#
# [84] 柱状图中最大的矩形
#
# https://leetcode-cn.com/problems/largest-rectangle-in-histogram/description/
#
# algorithms
# Hard (42.74%)
# Likes:    1213
# Dislikes: 0
# Total Accepted:    126.1K
# Total Submissions: 294.1K
# Testcase Example:  '[2,1,5,6,2,3]'
#
# 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
# 
# 求在该柱状图中，能够勾勒出来的矩形的最大面积。
# 
# 
# 
# 
# 
# 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
# 
# 
# 
# 
# 
# 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
# 
# 
# 
# 示例:
# 
# 输入: [2,1,5,6,2,3]
# 输出: 10
# 
#

# @lc code=start

class Solution:
    def largestRectangleArea(self, A):
        N = 0 if not A else len(A)
        if (N == 0):
            return 0

        lm = [0] * N
        rm = [0] * N

        lm[0] = -1
        for i in range(1, N):
            idx = i - 1
            while idx != -1 and A[idx] >= A[i]:
                idx = lm[idx]
            lm[i] = idx

        rm[N - 1] = N
        for i in range(N-2, -1, -1):
            idx = i + 1
            while (idx != N and A[idx] >= A[i]):
                idx = rm[idx]
            rm[i] = idx

        ans = 0
        for i in range(N): 
            ans = max(ans, A[i] * (rm[i] - lm[i] - 1))

        return ans

# @lc code=end

