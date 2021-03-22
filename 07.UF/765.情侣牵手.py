#
# @lc app=leetcode.cn id=765 lang=python
#
# [765] 情侣牵手
#
# https://leetcode-cn.com/problems/couples-holding-hands/description/
#
# algorithms
# Hard (59.81%)
# Likes:    238
# Dislikes: 0
# Total Accepted:    24.1K
# Total Submissions: 36K
# Testcase Example:  '[0,2,1,3]'
#
# N 对情侣坐在连续排列的 2N 个座位上，想要牵到对方的手。 计算最少交换座位的次数，以便每对情侣可以并肩坐在一起。
# 一次交换可选择任意两人，让他们站起来交换座位。
# 
# 人和座位用 0 到 2N-1 的整数表示，情侣们按顺序编号，第一对是 (0, 1)，第二对是 (2, 3)，以此类推，最后一对是 (2N-2,
# 2N-1)。
# 
# 这些情侣的初始座位  row[i] 是由最初始坐在第 i 个座位上的人决定的。
# 
# 示例 1:
# 
# 
# 输入: row = [0, 2, 1, 3]
# 输出: 1
# 解释: 我们只需要交换row[1]和row[2]的位置即可。
# 
# 
# 示例 2:
# 
# 
# 输入: row = [3, 2, 0, 1]
# 输出: 0
# 解释: 无需交换座位，所有的情侣都已经可以手牵手了。
# 
# 
# 说明:
# 
# 
# len(row) 是偶数且数值在 [4, 60]范围内。
# 可以保证row 是序列 0...len(row)-1 的一个全排列。
# 
# 
#

# @lc code=start

class UF(object):
    def __init__(self, N):
        self.F = [0] * N
        for i in range(0, N, 2):
            self.F[i] = i
            self.F[i+1] = i
        
        # 合并次数
        self.uc = 0

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
            self.uc += 1

class Solution(object):
    def minSwapsCouples(self, A):
        if not A or len(A) == 0:
            return 0
        
        N = len(A)
        uf = UF(N)

        for i in range(0, N, 2):
            uf.Union(A[i], A[i+1])
        
        return uf.uc


        
# @lc code=end

