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
class Solution(object):
    def dfs(self, A, vis, start):
        for next in range(len(A)):
            if not vis[next] and A[start][next] == 1:
                vis[next] = True
                self.dfs(A, vis, next)

    def findCircleNum(self, A):
        if not A or len(A) == 0:
            return 0
        if not A[0] or len(A[0]) == 0:
            return 0
        
        N = len(A)
        vis = [False] * N

        ans = 0

        for start in range(len(A)):
            if not vis[start]:
                ans += 1
                self.dfs(A, vis, start)
        
        return ans

# @lc code=end

