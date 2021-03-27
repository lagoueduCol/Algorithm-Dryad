#
# 测试平台 https://leetcode-cn.com/problems/eight-queens-lcci/
#
class Solution(object):
    def __init__(self):
        self.row = None
        self.left = None
        self.right = None
        self.A = None
        self.ans = None
    
    def Build(self, n):
        tmp = []
        for r in range(n):
            tmp.append(False)
        return tmp
    
    def Init(self, n):
        self.row = self.Build(n)
        self.left = self.Build(n+n)
        self.right = self.Build(n+n)
        self.A = []
        for r in range(n):
            tmp = []
            for c in range(n):
                tmp.append('.')
            self.A.append(tmp)

        self.ans = []

    def Clone(self):
        tmp = []
        for r in self.A:
            rr = ""
            for c in r:
                rr = rr + c
            tmp.append(rr)
        self.ans.append(tmp)

    def dfs(self, c, C):
        """
        c: 表示当前处理的列
        C: 表示一共有多少列，当然也是一共有多少行
        """
        if c >= C:
            self.Clone()
            return

        for r in range(C):
            if self.row[r] or self.left[r+c] or self.right[r+C-c]:
                continue

            self.row[r] = True
            self.left[r+c] = True
            self.right[r+C-c] = True
            self.A[r][c] = 'Q'
            self.dfs(c+1, C)
            self.A[r][c] = '.'
            self.row[r] = False
            self.left[r+c] = False
            self.right[r+C-c] = False

    def solveNQueens(self, n):
        self.Init(n)
        self.dfs(0, n)
        return self.ans

