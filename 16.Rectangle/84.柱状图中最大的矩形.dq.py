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
    # 这里得到一个区域里面的最大矩形面积
    # 这个区间域为[b, e)
    # 注意e是取不到的
    def getRangeMaxArea(self, heights, b, e):
        # 如果为空区间
        if b >= e:
            return 0

        # 如果区间中只有一个元素
        if b + 1 == e:
            return heights[b]

        # 如果有多个元素。那么找到范围里面的最小值
        # 如果有多个最小值，那么我们就找离中心最近的那个，尽量把区域进行等分
        mid = b + ((e-b) >> 1)
        minIndex = b

        for i in range(b + 1, e):
            if heights[i] < heights[minIndex]:
                minIndex = i
            elif (heights[i] == heights[minIndex]):
                # 多个最小值，那么谁离mid更近，我们用谁
                if abs(mid - i) < abs(mid - minIndex):
                    minIndex = i

        # 在使用 最小值 情况下的面积
        useMinIndexArea = heights[minIndex] * (e - b)

        # 不用 minIndex 那么就会把区间分为两部分
        leftMaxArea = self.getRangeMaxArea(heights, b, minIndex)
        rightMaxArea = self.getRangeMaxArea(heights, minIndex + 1, e)

        return max(useMinIndexArea, max(leftMaxArea, rightMaxArea))
    

    def largestRectangleArea(self, A):
        N = 0 if not A else len(A)
        return self.getRangeMaxArea(A, 0, N)
# @lc code=end

