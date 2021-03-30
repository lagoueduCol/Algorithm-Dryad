#  https://www.lintcode.com/problem/minimum-risk-path/

import heapq
# 构建
# heapq.heapify(heap)
# pop
# cur = heapq.heappop(heap)
# push
# heapq.heappush(heap, cur.next)

class Solution:
    """
    @param n: maximum index of position.
    @param m: the number of undirected edges.
    @param x: 
    @param y: 
    @param w: 
    @return: return the minimum risk value.
    """
    def getMinRiskValue(self, n, m, x, y, w):
        G = []
        for i in range(n+1):
          G.append([])
        
        # 构建图
        for i in range(m):
          a = x[i]
          b = y[i]
          c = w[i]
          G[a].append([b,c])
          G[b].append([a,c])
        
        Q = []

        INF = 2147483647
        risk = [INF] * (n+1)
        risk[0] = 0

        # push <risk, node>
        heapq.heappush(Q, [0, 0])

        while len(Q) > 0:
          cRisk, cNode = heapq.heappop(Q)
          # 拿当前结点的后续
          for nextInfo in G[cNode]:
            next = nextInfo[0]
            nextRisk = max(cRisk, nextInfo[1])
            if nextRisk < risk[next]:
              risk[next] = nextRisk
              heapq.heappush(Q, [nextRisk, next])
        
        return risk[n]

