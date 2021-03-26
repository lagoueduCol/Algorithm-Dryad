#
# @lc app=leetcode.cn id=130 lang=python
#
# [130] 被围绕的区域
#
# https://leetcode-cn.com/problems/surrounded-regions/description/
#
# algorithms
# Medium (42.26%)
# Likes:    475
# Dislikes: 0
# Total Accepted:    88.5K
# Total Submissions: 209.4K
# Testcase Example:  '[["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]'
#
# 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
# 
# 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
# 
# 示例:
# 
# X X X X
# X O O X
# X X O X
# X O X X
# 
# 
# 运行你的函数后，矩阵变为：
# 
# X X X X
# X X X X
# X X X X
# X O X X
# 
# 
# 解释:
# 
# 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O'
# 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
# 
#

# @lc code=start
class Solution(object):
    def __init__(self):
        self.R = 0
        self.C = 0
        self.dir = [[-1, 0], [1, 0], [0, 1], [0, -1]]
        self.VIS = 'A'

    def dfs(self, A, r, c):
        for d in range(4):
            nr = r + self.dir[d][0]
            nc = c + self.dir[d][1]

            if nr < 0 or nr >= self.R or nc < 0 or nc >= self.C:
                continue
            
            if A[nr][nc] == 'O':
                A[nr][nc] = self.VIS
                self.dfs(A, nr, nc)

    def solve(self, A):
        if not A or len(A) == 0 or len(A[0]) == 0:
            return

        self.R = len(A)
        self.C = len(A[0])

        # 先处理四条边
        for r in range(self.R):
            for c in range(self.C):
                if r == 0 or c == 0 or r == self.R-1 or c == self.C-1:
                    if A[r][c] == 'O':
                        A[r][c] = self.VIS
                        self.dfs(A, r, c)
        
        # 然后再把VIS修改成O
        # 其他的都设置成X
        for r in range(self.R):
            for c in range(self.C):
                if A[r][c] == self.VIS:
                    A[r][c] = 'O'
                else:
                    A[r][c] = 'X'
# @lc code=end

