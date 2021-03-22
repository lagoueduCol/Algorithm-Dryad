#
# @lc app=leetcode.cn id=547 lang=python
#
# [547] 省份数量
#
# https://leetcode-cn.com/problems/number-of-provinces/description/
#
# algorithms
# Medium (61.41%)
# Likes:    496
# Dislikes: 0
# Total Accepted:    116.6K
# Total Submissions: 189.8K
# Testcase Example:  '[[1,1,0],[1,1,0],[0,0,1]]'
#
# 
# 
# 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c
# 间接相连。
# 
# 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
# 
# 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而
# isConnected[i][j] = 0 表示二者不直接相连。
# 
# 返回矩阵中 省份 的数量。
# 
# 
# 
# 示例 1：
# 
# 
# 输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
# 输出：2
# 
# 
# 示例 2：
# 
# 
# 输入：isConnected = [[1,0,0],[0,1,0],[0,0,1]]
# 输出：3
# 
# 
# 
# 
# 提示：
# 
# 
# 1 
# n == isConnected.length
# n == isConnected[i].length
# isConnected[i][j] 为 1 或 0
# isConnected[i][i] == 1
# isConnected[i][j] == isConnected[j][i]
# 
# 
# 
# 
#

# @lc code=start

class UF(object):
    def __init__(self, N):
        self.F = [0] * N
        self.Cnt = [0] * N
        for i in range(N):
            self.F[i] = i
            self.Cnt[i] = 1

        self.count = N

    def Find(self, x):
        if x == self.F[x]:
            return x
        self.F[x] = self.Find(self.F[x])
        return self.F[x]

    def Union(self, x, y):
        xpar = self.Find(x)
        ypar = self.Find(y)
        if xpar != ypar:
            self.F[xpar] = ypar
            self.Cnt[ypar] += self.Cnt[xpar]
            self.count -= 1

    def Size(self, i):
        return self.Cnt[self.Find(i)]

    def Count(self):
        return self.count

class Solution(object):
    def findCircleNum(self, G):
        if not G:
            return 0
        
        N = len(G)

        uf = UF(N)

        for r in range(N):
            for c in range(r+1,N):
                if G[r][c]:
                    uf.Union(r,c)
        
        return uf.count

# @lc code=end

