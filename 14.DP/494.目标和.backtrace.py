#
# @lc app=leetcode.cn id=494 lang=python
#
# [494] 目标和
#
# https://leetcode-cn.com/problems/target-sum/description/
#
# algorithms
# Medium (45.05%)
# Likes:    628
# Dislikes: 0
# Total Accepted:    75.7K
# Total Submissions: 167.9K
# Testcase Example:  '[1,1,1,1,1]\n3'
#
# 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或
# -中选择一个符号添加在前面。
# 
# 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
# 
# 
# 
# 示例：
# 
# 输入：nums: [1, 1, 1, 1, 1], S: 3
# 输出：5
# 解释：
# 
# -1+1+1+1+1 = 3
# +1-1+1+1+1 = 3
# +1+1-1+1+1 = 3
# +1+1+1-1+1 = 3
# +1+1+1+1-1 = 3
# 
# 一共有5种方法让最终目标和为3。
# 
# 
# 
# 
# 提示：
# 
# 
# 数组非空，且长度不会超过 20 。
# 初始的数组的和不会超过 1000 。
# 保证返回的最终结果能被 32 位整数存下。
# 
# 
#

# @lc code=start
class Solution(object):
  def __init__(self):
    self.ans = 0

  def dfs(self, A, idx, cur, target):
    if idx >= len(A):
      self.ans += 1 if cur == target else 0
      return

    # 剪枝1
    if (cur > target):
      return

    # 剪枝2 如果已经相等
    if cur == target:
      self.ans += 1
      return

    # 不选A[idx]
    self.dfs(A, idx + 1, cur, target)
    # 选A[idx]
    self.dfs(A, idx + 1, cur + A[idx], target)

  def findTargetSumWays(self, A, S):
    # 思路供参考，python暴力求解太慢!!
    # 会超时!
    total = 0
    for x in A:
        total += x

    S = abs(S)

    # 如果给定的S值太大
    # 不用计算了
    if (S > total):
      return 0

    # 如果我们要取出数最后加减之后为S
    # 我们让和为X的数前面都是+号
    # 和为Y的数，前面都是减号
    # X - abs(Y) = S
    # X + abs(Y) = total
    # 那么最终可以得到
    # X = (S + total) / 2
    # 如果是一个奇数
    # 那么也是没有解的
    if ((S + total) & 0x01) == 1:
      return 0

    # 那么现在问题就变成了选一些数出来，让它们的和等于X
    X = (S + total) >> 1
    # 由于是添加正负号，S是正数还是负数不重要

    # 零的个数
    # 在构成S的基础ans上，如果有zero个0
    # 那么最终解就是ans * (1<<zero)个
    zero = 0
    for x in A:
      zero += 1 if x == 0 else 0

    # 现在问题就变成了需要在数组A中找到非0的数
    # 使得他们的和等于X
    B = []
    for x in A:
      if x <= X and x > 0:
        B.append(x)
    B.sort()

    self.ans = 0
    self.dfs(B, idx=0, cur=0, target=X)

    return self.ans * (1<<zero)

# @lc code=end

