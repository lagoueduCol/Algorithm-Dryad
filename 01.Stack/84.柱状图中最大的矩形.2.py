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


# 思路：
#
# 2个重要的性质：
#
# 一个递增栈里面存放的是数组的下标 stack = [i, j]
#
# 性质1: 对于j而言，下标[i+1 ... j-1] 这里面的元素的A[x]值都 >= A[j]
#       对于i而言，(-1, i-1] 这里面元素的值都 >= A[i];
#
# 如果此时A[k]要入栈。并且A[k] < A[j]，要将A[j]出栈。
#
# 性质2: A[j+1 ... k-1]这个区间里面的元素都大于A[j]
#

# @lc code=start
class Solution(object):
    def largestRectangleArea(self, A):
        N = 0 if not A else len(A)

        # 递增栈
        t = []

        ans = 0

        for i in range(0, N+1):
            x = A[i] if i < N else -1
            while len(t) > 0 and A[t[-1]] > x:
                # 这里以A[idx]为高度
                idx = t[-1]
                height = A[idx]
                t.pop()
                # 根据性质2，右边比A[idx]大的就是[idx + 1... i)
                rightPos = i
                leftPos = t[-1] if len(t) > 0 else -1

                width = rightPos - leftPos - 1
                area = height * width

                ans = max(ans, area)
            t.append(i)
        
        return ans

        
# @lc code=end

