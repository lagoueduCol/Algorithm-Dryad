# -*- coding: utf-8 -*-

# your code here
class Solution(object):
    def __init__(self):
        self.ans = []

    def Clone(self, tmp):
        self.ans = []
        for x in tmp:
            self.ans.append([x[0], x[1]])

    def dfs(self, A, vis, r, c, tmp):
        R = len(A)
        C = len(A[0])

        # 如果遇到终点了
        if (r == R - 1 and c == C - 1):
            if not self.ans or len(self.ans) > len(tmp):
                self.Clone(tmp)
            return
        
        # 查看余下的四个方向
        dir = [[0,1],[0,-1],[1,0],[-1,0]]

        for d in range(4):
            nr = r + dir[d][0]
            nc = c + dir[d][1]

            # 如果越界，那么没有必要访问
            if nr < 0 or nr >= R or nc < 0 or nc >= C:
                continue
            
            # 如果不能访问，那么也跳过
            if (A[nr][nc] == 1 or vis[nr][nc]):
                continue
            
            vis[nr][nc] = True
            tmp.append([nr,nc])

            self.dfs(A, vis, nr, nc, tmp)

            vis[nr][nc] = False
            tmp.pop()

    def findMinPath(self, A):
        # 如果输入不合法
        if not A or len(A) == 0 or len(A[0]) == 0:
            return []
        
        R = len(A)
        C = len(A[0])

        vis = []
        for i in range(R):
            vis.append([False]*C)

        tmp = [[0,0]]
        vis[0][0] = True

        self.dfs(A, vis, 0, 0, tmp)

        tmp.pop()
        vis[0][0] = False

        return self.ans


# Main
A = []

s = Solution()
    
for i in range(5):
    line = raw_input()
    ws = line.split()

    tmp = []
    for x in ws:
        tmp.append(int(x))
    A.append(tmp)

ans = s.findMinPath(A)

for x in ans:
    print '(%s, %s)' % (x[0], x[1])

