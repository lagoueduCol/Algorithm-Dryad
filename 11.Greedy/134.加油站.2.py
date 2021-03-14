#
# @lc app=leetcode.cn id=134 lang=python
#
# [134] 加油站
#
# https://leetcode-cn.com/problems/gas-station/description/
#
# algorithms
# Medium (56.96%)
# Likes:    607
# Dislikes: 0
# Total Accepted:    92.5K
# Total Submissions: 162.3K
# Testcase Example:  '[1,2,3,4,5]\n[3,4,5,1,2]'
#
# 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
# 
# 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
# 
# 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
# 
# 说明: 
# 
# 
# 如果题目有解，该答案即为唯一答案。
# 输入数组均为非空数组，且长度相同。
# 输入数组中的元素均为非负数。
# 
# 
# 示例 1:
# 
# 输入: 
# gas  = [1,2,3,4,5]
# cost = [3,4,5,1,2]
# 
# 输出: 3
# 
# 解释:
# 从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
# 开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
# 开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
# 开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
# 开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
# 开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
# 因此，3 可为起始索引。
# 
# 示例 2:
# 
# 输入: 
# gas  = [2,3,4]
# cost = [3,4,3]
# 
# 输出: -1
# 
# 解释:
# 你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
# 我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
# 开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
# 开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
# 你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
# 因此，无论怎样，你都不可能绕环路行驶一周。
# 
#

# @lc code=start
class Solution(object):
    def getMinRange(self, G, C):
        N = 0 if not G else len(G)

        start = 0
        end = 0
        ans = 0
        
        tmp = 0
        tmp_start = 0
        tmp_end = 0

        for i in range(N):
            if tmp > 0:
                tmp = 0
                tmp_start = i

            tmp += G[i] - C[i]
            tmp_end = i

            if tmp < ans:
                ans = tmp
                start = tmp_start
                end = tmp_end
        
        return [start, end, ans]

    def getMaxRange(self, G, C):
        N = 0 if not G else len(G)

        start = 0
        end = 0
        ans = 0

        tmp_start = 0
        tmp_end = 0
        tmp = 0

        for i in range(N):
            if tmp < 0:
                tmp_start = i
                tmp = 0

            tmp += G[i] - C[i]
            tmp_end = i

            if tmp > ans:
                ans = tmp
                start = tmp_start
                end = tmp_end

        return [start, end, ans]

    def canCompleteCircuit(self, G, C):
        N = 0 if not G else len(G)

        s = 0
        for i in range(N):
            s += G[i] - C[i]
        if s < 0:
            return -1

        small = self.getMinRange(G, C)
        large = self.getMaxRange(G, C)

        if s - small[2] > large[2]:
            return small[1] + 1

        return large[0]
# @lc code=end

