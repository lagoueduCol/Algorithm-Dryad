#
# @lc app=leetcode.cn id=743 lang=python
#
# [743] 网络延迟时间
#
# https://leetcode-cn.com/problems/network-delay-time/description/
#
# algorithms
# Medium (46.62%)
# Likes:    230
# Dislikes: 0
# Total Accepted:    22K
# Total Submissions: 47K
# Testcase Example:  '[[2,1,1],[2,3,1],[3,4,1]]\n4\n2'
#
# 有 n 个网络节点，标记为 1 到 n。
# 
# 给你一个列表 times，表示信号经过 有向 边的传递时间。 times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点，
# wi 是一个信号从源节点传递到目标节点的时间。
# 
# 现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。
# 
# 
# 
# 示例 1：
# 
# 
# 
# 
# 输入：times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
# 输出：2
# 
# 
# 示例 2：
# 
# 
# 输入：times = [[1,2,1]], n = 2, k = 1
# 输出：1
# 
# 
# 示例 3：
# 
# 
# 输入：times = [[1,2,1]], n = 2, k = 2
# 输出：-1
# 
# 
# 
# 
# 提示：
# 
# 
# 1 
# 1 
# times[i].length == 3
# 1 i, vi 
# ui != vi
# 0 i 
# 所有 (ui, vi) 对都 互不相同（即，不含重复边）
# 
# 
#

# @lc code=start

import Queue
from Queue import PriorityQueue

class Solution(object):
    def networkDelayTime(self, times, N, k):
        """
        :type times: List[List[int]]
        :type n: int
        :type k: int
        :rtype: int
        """

        INF = 2147483647
        ans = [INF] * (N + 1)
        ans[k] = 0

        Q = PriorityQueue()
        Q.put(k)
        
        # G表示Graph
        G = []
        for i in range(0, N+1):
            G.append([])

        for e in times:
            G[e[0]].append([e[1], e[2]])

        while Q.qsize() > 0:
            cur = Q.get()
            for p in G[cur]:
                next = p[0]
                cost = p[1]
                if cost + ans[cur] < ans[next]:
                    ans[next] = cost + ans[cur]
                    Q.put(next)
        
        maxValue = -1
        for i in range(1, N+1):
            maxValue = max(maxValue, ans[i])
        
        return -1 if maxValue == INF else maxValue

# @lc code=end

