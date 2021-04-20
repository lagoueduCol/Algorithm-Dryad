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
class Solution(object):
    def getMaxArea(self, heights, b, e):
        if (b >= e):
            return 0
        
        if (b + 1 == e):
            return heights[b]
        
        mid = b + ((e-b) >> 1)
        leftArea = self.getMaxArea(heights, b, mid)
        rightArea = self.getMaxArea(heights, mid + 1, e)

        l = mid - 1
        r = mid + 1
        minHeight = heights[mid]
        area = heights[mid]

        while l >= b or r < e:
            if r >= e or l >= b and heights[l] > heights[r]:
                minHeight = min(minHeight, heights[l])
                l -= 1
            else:
                minHeight = min(minHeight, heights[r])
                r += 1
            
            area = max(area, minHeight * (r - l - 1))
        
        return max(area, max(leftArea, rightArea))

    def largestRectangleArea(self, heights):
        N = 0 if not heights else len(heights)
        return self.getMaxArea(heights, 0, N)
# @lc code=end

