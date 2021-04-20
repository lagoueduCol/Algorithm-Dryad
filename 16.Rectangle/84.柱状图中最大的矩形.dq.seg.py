# -*- coding: utf-8 -*-
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
    def __init__(self):
        # 表示线段树的数组self.treeArray[]
        # 数组里面的值表示区间里面的最小值
        self.treeArray = None
        self.heights = None

    def leftNodePos(self, rootPos):
        return (rootPos << 1) + 1

    def rightNodePos(self, rootPos):
        return (rootPos << 1) + 2

    # self.treeArray[rootPos] 将会记录数组[start, end]
    # 这个区间上的信息。在本题中，信息为区间上的最小值
    def buildTree(self, rootPos, start, end):
        # 范围为空
        if (start > end):
            return
        # 如果区间：只有一个数
        if (start == end):
            self.treeArray[rootPos] = start
        else:
            # 否则需要将区间分为两半
            mid = start + ((end - start) >> 1)
            self.buildTree(self.leftNodePos(rootPos), start, mid)
            self.buildTree(self.rightNodePos(rootPos), mid + 1, end)

            # 构建成功之后，需要利用左子树的信息和右子树的信息来
            # 来更新 [start, end] rootNode 的信息
            leftRangeMinIndex = self.treeArray[self.leftNodePos(rootPos)]
            rightRangeMinIndex = self.treeArray[self.rightNodePos(rootPos)]
            if self.heights[leftRangeMinIndex] < self.heights[rightRangeMinIndex]:
                self.treeArray[rootPos] = leftRangeMinIndex
            else:
                self.treeArray[rootPos] = rightRangeMinIndex

    def queryTree(self, rootPos, start, end, queryStart, queryEnd):
        """
        * 查询区间[queryStart, queryEnd]这个区间上的最小值信息
        * self.treeArray[rootPos]表示区间 [start, end]上的最小值。
        * 可以把前面的三个参数看成
        * class TreeNode {
        *      val        <-- arg: self.treeArray[rootPos]
        *      rangeStart <-- arg: start
        *      rangeEnd:   <-- arg: end
        *      TreeNode left  <-- leftNodePos(rootPos)
        *      TreeNode right: <-- rightNodePos(rootPos)
        * 
        """
        # 无效区间，返回 queryStart
        if (start > end or queryStart > queryEnd):
            return queryStart
        
        # 原则1： 包含于查询区间内部
        if (queryStart <= start and end <= queryEnd):
            return self.treeArray[rootPos]
        
        # 原则2：不相交时，放弃区间信息，这里我们返回最大值
        if (end < queryStart or queryEnd < start):
            return queryStart
        
        # 原则3：当相交的时候，需要将[start, end]进行拆分
        # 由于我们建树的时候，都是平分，所以这里将区间也进行平分
        mid = start + ((end - start) >> 1)
        leftRangeMinIndex = self.queryTree(self.leftNodePos(rootPos), start, mid, queryStart, queryEnd)
        rightRangeMinIndex = self.queryTree(self.rightNodePos(rootPos), mid + 1, end, queryStart, queryEnd)

        if self.heights[leftRangeMinIndex] < self.heights[rightRangeMinIndex]:
            return leftRangeMinIndex
        return rightRangeMinIndex

    # 当我们要更新数组中A[inx] = value的时候
    # 线段树中存储的区间的信息，也是需要更新的
    # 当然，这个函数在这里并没有使用到!!
    def updateTree(self, rootPos, start, end, idx, value):
        # 如果树中的结点不在我们的更新路径上
        if (start > end or idx < start or idx > end):
            return
        
        # 如果已经找到了叶子结点
        if (start == idx and idx == end):
            self.treeArray[rootPos] = idx
            self.heights[idx] = value
            return
        
        # 这里后序遍历
        # 如果是非叶子结点，那么
        # 先更新左右子结点，再更新根结点
        mid = start + ((end - start) >> 1)
        # 更新左子树
        self.updateTree(self.leftNodePos(rootPos), start, mid, idx, value)
        # 更新右子树
        self.updateTree(self.rightNodePos(rootPos), mid + 1, end, idx, value)
        # 更新根结点
        leftRangeMinIndex = self.treeArray[self.leftNodePos(rootPos)]
        rightRangeMinIndex = self.treeArray[self.rightNodePos(rootPos)]
        if self.heights[leftRangeMinIndex] < self.heights[rightRangeMinIndex]:
            self.treeArray[rootPos] = leftRangeMinIndex
        else:
            self.treeArray[rootPos] = rightRangeMinIndex

    # 这里得到一个区域里面的最大矩形面积
    # 这个区间域为[b, e)
    # 注意e是取不到的
    def getRangeMaxArea(self, b, e):
        N = 0 if not self.heights else len(self.heights)
        # 如果为空区间
        if b >= e:
            return 0

        # 如果区间中只有一个元素
        if b + 1 == e:
            return self.heights[b]

        # 如果有多个元素。那么找到范围里面的最小值
        # 如果有多个最小值，那么我们就找离中心最近的那个，尽量把区域进行等分
        mid = b + ((e-b) >> 1)
        minIndex = self.queryTree(0, 0, N - 1, b, e-1)

        # 在使用 最小值 情况下的面积
        useMinIndexArea = self.heights[minIndex] * (e - b)

        # 不用 minIndex 那么就会把区间分为两部分
        leftMaxArea = self.getRangeMaxArea(b, minIndex)
        rightMaxArea = self.getRangeMaxArea(minIndex + 1, e)

        return max(useMinIndexArea, max(leftMaxArea, rightMaxArea))

    def largestRectangleArea(self, A):
        N = 0 if not A else len(A)

        self.heights = A
        self.treeArray = [0] * (N<<2)
        self.buildTree(0, 0, N-1)

        return self.getRangeMaxArea(0, N)

# @lc code=end