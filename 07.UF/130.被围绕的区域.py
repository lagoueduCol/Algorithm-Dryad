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
    def solve(self, A):
        if not A or not A[0]:
            return
        
        R = len(A)
        C = len(A[0])

        def get_id(r, c):
            return r * C + c

        uf = UF(R * C + 1)

        vNode = R * C

        # 遍历边界上的'O'
        for c in range(0, C):
            if A[0][c] == 'O':
                uf.Union(get_id(0,c), vNode)
            if A[R-1][c] == 'O':
                uf.Union(get_id(R-1,c), vNode)

        for r in range(0, R):
            if A[r][0] == 'O':
                uf.Union(get_id(r,0), vNode)
            if A[r][C-1] == 'O':
                uf.Union(get_id(r, C-1), vNode)
        
        # 遍历的时候，只需要查看自己的右边和下面
        D = [[1,0], [0,1]]

        # 开始遍历整个图
        for r in range(0, R):
            for c in range(0, C):
                if A[r][c] == 'O':
                    for d in range(0,2):
                        nr = r + D[d][0]
                        nc = c + D[d][1]
                        if 0 <= nr and nr < R and 0 <= nc and nc < C:
                            if A[nr][nc] == 'O':
                                uf.Union(get_id(r,c), get_id(nr,nc))
        
        # 开始遍历图上的点，如果与vNode不在同一个集合里面，设置为'X'
        for r in range(0, R):
            for c in range(0, C):
                if A[r][c] == 'O':
                    if not uf.Find(get_id(r,c)) == uf.Find(vNode):
                        A[r][c] = 'X'

# @lc code=end

