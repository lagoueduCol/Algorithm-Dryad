#
# @lc app=leetcode.cn id=399 lang=python
#
# [399] 除法求值
#
# https://leetcode-cn.com/problems/evaluate-division/description/
#
# algorithms
# Medium (59.38%)
# Likes:    468
# Dislikes: 0
# Total Accepted:    32.1K
# Total Submissions: 54.1K
# Testcase Example:  '[["a","b"],["b","c"]]\n' +
  '[2.0,3.0]\n' +
  '[["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]'
#
# 给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，其中 equations[i] = [Ai, Bi] 和
# values[i] 共同表示等式 Ai / Bi = values[i] 。每个 Ai 或 Bi 是一个表示单个变量的字符串。
# 
# 另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj =
# ? 的结果作为答案。
# 
# 返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。如果问题中出现了给定的已知条件中没有出现的字符串，也需要用 -1.0
# 替代这个答案。
# 
# 注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。
# 
# 
# 
# 示例 1：
# 
# 
# 输入：equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries =
# [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
# 输出：[6.00000,0.50000,-1.00000,1.00000,-1.00000]
# 解释：
# 条件：a / b = 2.0, b / c = 3.0
# 问题：a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
# 结果：[6.0, 0.5, -1.0, 1.0, -1.0 ]
# 
# 
# 示例 2：
# 
# 
# 输入：equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0],
# queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
# 输出：[3.75000,0.40000,5.00000,0.20000]
# 
# 
# 示例 3：
# 
# 
# 输入：equations = [["a","b"]], values = [0.5], queries =
# [["a","b"],["b","a"],["a","c"],["x","y"]]
# 输出：[0.50000,2.00000,-1.00000,-1.00000]
# 
# 
# 
# 
# 提示：
# 
# 
# 1 
# equations[i].length == 2
# 1 i.length, Bi.length 
# values.length == equations.length
# 0.0 < values[i] 
# 1 
# queries[i].length == 2
# 1 j.length, Dj.length 
# Ai, Bi, Cj, Dj 由小写英文字母与数字组成
# 
# 
#

# @lc code=start

class UF(object):
    def __init__(self, N):
      # 一开始，每个人都是自己的1倍
      self.C = [1.0] * N

      # F[]数组里面一开始自己是一个集合
      self.F = [0] * N
      for i in range(N):
        self.F[i] = i
      

    def Find(self, x):
      """
      返回Find()函数在集合中的
      1. 根结点
      2. x结点 与 root结点 的比例
      """
      b = x
      base = 1.0

      while x != self.F[x]:
        base *= self.C[x]
        x = self.F[x]
      
      root = x
      while self.F[b] != root:
        next = base / self.C[b]
        self.C[b] = base
        base = next

        par = self.F[b]
        self.F[b] = root
        b = par

      return root

    def Union(self, x, y, rate):
      """
      传进来的参数表达的含义是 x / y = rate
      """
      xpar = self.Find(x)
      ypar = self.Find(y)
      self.C[xpar] = rate * self.C[y] / self.C[x]
      self.F[xpar] = ypar

class Solution(object):
    def calcEquation(self, ES, VS, QS):
      """
      ES = ["a", "b"]
      VS = ["2"] => a/b = 2
      QS = ["b", "a"] => b/a = 0.5
      """

      # 首先拿到所有的单词
      # 并且对每个单词进行编号
      d = {}
      for E in ES:
        a, b = E
        if -1 == d.get(a, -1):
          d[a] = len(d)
        if -1 == d.get(b, -1):
          d[b] = len(d)

      
      # 得到单词的个数之后，
      # 就是总数
      N = len(d)
      uf = UF(N)
      N = len(ES)

      for i in range(N):
        a, b = ES[i]
        v = VS[i]
        uf.Union(d[a], d[b], v)

      ans = []
      for q in QS:
        a, b = q
        if d.get(a, -1) == -1 or d.get(b, -1) == -1:
          ans.append(-1)
          continue

        apar = uf.Find(d[a])
        bpar = uf.Find(d[b])

        if apar == bpar:
          aRate = uf.C[d[a]]
          bRate = uf.C[d[b]]
          ans.append(aRate / bRate)
        else:
          ans.append(-1)
      
      return ans
# @lc code=end

