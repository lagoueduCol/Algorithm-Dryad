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

class RightSmall:
  def findRightSmall(self, A):
    if not A or len(A) == 0:
      return []

    # 结果数组
    ans =[0] * len(A)

    # 注意，栈中的元素记录的是下标
    t = []

    for i in range(0, len(A)):
      x = A[i]
      # 每个元素都向左遍历栈中的元素完成消除动作
      while len(t) > 0 and A[t[-1]] > x:
        # 消除的时候，记录一下被谁消除了
        ans[t[-1]] = i
        # 消除时候，值更大的需要从栈中消失
        t.pop()

      # 剩下的入栈
      t.append(i)

    # 栈中剩下的元素，由于没有人能消除他们，因此，只能将结果设置为-1。
    while len(t) > 0:
      ans[t[-1]] = -1
      t.pop()

    return ans

class LeftSmall:
  def findLeftSmall(self, A):
    if not A or len(A) == 0:
      return []

    # 结果数组
    ans =[0] * len(A)

    # 注意，栈中的元素记录的是下标
    t = []

    for i in range(len(A)-1,-1,-1):
      x = A[i]
      # 每个元素都向左遍历栈中的元素完成消除动作
      while len(t) > 0 and A[t[-1]] > x:
        # 消除的时候，记录一下被谁消除了
        ans[t[-1]] = i
        # 消除时候，值更大的需要从栈中消失
        t.pop()

      # 剩下的入栈
      t.append(i)

    # 栈中剩下的元素，由于没有人能消除他们，因此，只能将结果设置为-1。
    while len(t) > 0:
      ans[t[-1]] = -1
      t.pop()

    return ans

class Solution(object):
    def largestRectangleArea(self, A):
        N = len(A) if A else 0

        rightSmall = RightSmall().findRightSmall(A)
        leftSmall = LeftSmall().findLeftSmall(A)

        ans = 0
        for i in range(0, N):
            height = A[i]
            rightPos = N if rightSmall[i] == -1 else rightSmall[i]
            leftPos = leftSmall[i]
            width = rightPos - leftPos - 1
            ans = max(ans, height * width)
        
        return ans

# @lc code=end

