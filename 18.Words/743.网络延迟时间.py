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
class Solution(object):
    def networkDelayTime(self, times, n, k):
        """
        :type times: List[List[int]]
        :type n: int
        :type k: int
        :rtype: int
        """

        INF = 2147483647 >> 4

        dist = [INF] * (n + 1)
        dist[k] = 0

        for updateTimes in range(n+1):
            for edge in times:
                start, end, cost = edge
                dist[end] = min(dist[end], dist[start] + cost)
        
        maxTime = 0
        for i in range(1,n+1):
            maxTime = max(maxTime, dist[i])
        
        return -1 if maxTime >= INF else maxTime
# @lc code=end

