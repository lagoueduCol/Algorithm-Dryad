#
# @lc app=leetcode.cn id=200 lang=python
#
# [200] 岛屿数量
#
# https://leetcode-cn.com/problems/number-of-islands/description/
#
# algorithms
# Medium (52.28%)
# Likes:    979
# Dislikes: 0
# Total Accepted:    206K
# Total Submissions: 393.1K
# Testcase Example:  '[["1","1","1","1","0"],["1","1","0","1","0"],["1","1","0","0","0"],["0","0","0","0","0"]]'
#
# 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
# 
# 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
# 
# 此外，你可以假设该网格的四条边均被水包围。
# 
# 
# 
# 示例 1：
# 
# 
# 输入：grid = [
# ⁠ ["1","1","1","1","0"],
# ⁠ ["1","1","0","1","0"],
# ⁠ ["1","1","0","0","0"],
# ⁠ ["0","0","0","0","0"]
# ]
# 输出：1
# 
# 
# 示例 2：
# 
# 
# 输入：grid = [
# ⁠ ["1","1","0","0","0"],
# ⁠ ["1","1","0","0","0"],
# ⁠ ["0","0","1","0","0"],
# ⁠ ["0","0","0","1","1"]
# ]
# 输出：3
# 
# 
# 
# 
# 提示：
# 
# 
# m == grid.length
# n == grid[i].length
# 1 
# grid[i][j] 的值为 '0' 或 '1'
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
    def numIslands(self, A):
        if not A or not A[0]:
            return 0
        
        R = len(A)
        C = len(A[0])

        blackNumber = 0

        D = [[-1,0],[0,-1],[0,1], [1,0]]

        uf = UF(R * C)

        def get_id(r, c):
            return r * C + c

        for r in range(0, R):
            for c in range(0, C):
                v = A[r][c]
                if v == '1':
                    for d in range(0, len(D)):
                        nr = r + D[d][0]
                        nc = c + D[d][1]
                        if 0 <= nr and nr < R and 0 <= nc and nc < C:
                            if A[nr][nc] == '1':
                                uf.Union(get_id(r,c), get_id(nr,nc))
                else:
                    blackNumber += 1
        
        return uf.count - blackNumber

        
# @lc code=end

