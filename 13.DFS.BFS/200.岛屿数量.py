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
class Solution(object):
    def __init__(self):
        self.R = 0
        self.C = 0
        self.dir = [[-1,0],[1,0],[0,-1],[0,1]]

    def dfs(self, A, r, c):
        for d in range(4):
            nr = r + self.dir[d][0]
            nc = c + self.dir[d][1]
            if nr < 0 or nr >= self.R or nc < 0 or nc >= self.C:
                continue
            if A[nr][nc] == '1':
                A[nr][nc] = '2'
                self.dfs(A, nr, nc)

    def numIslands(self, A):
        if not A or not A[0] or \
            len(A) == 0 or len(A[0]) == 0:
            return 0

        self.R = len(A)
        self.C = len(A[0])

        ans = 0

        for r in range(self.R):
            for c in range(self.C):
                if A[r][c] == '1':
                    ans += 1
                    A[r][c] = '2'
                    self.dfs(A, r, c)

        return ans
# @lc code=end

