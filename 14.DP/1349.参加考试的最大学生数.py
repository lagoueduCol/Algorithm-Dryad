# -*- coding: utf-8 -*-
#
# @lc app=leetcode.cn id=1349 lang=python
#
# [1349] 参加考试的最大学生数
#
# https://leetcode-cn.com/problems/maximum-students-taking-exam/description/
#
# algorithms
# Hard (48.76%)
# Likes:    108
# Dislikes: 0
# Total Accepted:    2.6K
# Total Submissions: 5.3K
# Testcase Example:  '[["#",".","#","#",".","#"],[".","#","#","#","#","."],["#",".","#","#",".","#"]]'
#
# 给你一个 m * n 的矩阵 seats 表示教室中的座位分布。如果座位是坏的（不可用），就用 '#' 表示；否则，用 '.' 表示。
# 
# 
# 学生可以看到左侧、右侧、左上、右上这四个方向上紧邻他的学生的答卷，但是看不到直接坐在他前面或者后面的学生的答卷。请你计算并返回该考场可以容纳的一起参加考试且无法作弊的最大学生人数。
# 
# 学生必须坐在状况良好的座位上。
# 
# 
# 
# 示例 1：
# 
# 
# 
# 输入：seats = [["#",".","#","#",".","#"],
# [".","#","#","#","#","."],
# ["#",".","#","#",".","#"]]
# 输出：4
# 解释：教师可以让 4 个学生坐在可用的座位上，这样他们就无法在考试中作弊。 
# 
# 
# 示例 2：
# 
# 输入：seats = [[".","#"],
# ["#","#"],
# ["#","."],
# ["#","#"],
# [".","#"]]
# 输出：3
# 解释：让所有学生坐在可用的座位上。
# 
# 
# 示例 3：
# 
# 输入：seats = [["#",".",".",".","#"],
# [".","#",".","#","."],
# [".",".","#",".","."],
# [".","#",".","#","."],
# ["#",".",".",".","#"]]
# 输出：10
# 解释：让学生坐在第 1、3 和 5 列的可用座位上。
# 
# 
# 
# 
# 提示：
# 
# 
# seats 只包含字符 '.' 和'#'
# m == seats.length
# n == seats[i].length
# 1 <= m <= 8
# 1 <= n <= 8
# 
# 
#

# @lc code=start
class Solution(object):
    def isValidSit(self, j):
        pre_bit = 0
        while j:
            cur_bit = j & 0x01
            if cur_bit and pre_bit:
                return False
            j >>= 1
            pre_bit = cur_bit
        return True

    def buildRow(self, C):
        ret = []
        for x in range(C):
            ret.append(0)
        return ret

    def sitMask(self, A, r):
        # 记录不能坐的情况
        not_sit = 0
        for c in range(len(A[r])):
            if A[r][c] == '#':
                not_sit |= 1 << c
        return not_sit

    def popCount(self, x):
        ret = 0
        while x:
            ret += 1 if x & 0x01 else 0
            x >>= 1
        return ret

    def maxStudents(self, A):
        if not A or not A[0] or len(A) == 0 or len(A[0]) == 0:
            return 0

        # R表示矩阵的行数
        R = len(A)
        # C表示矩阵的列数
        C = len(A[0])

        # 每一行的状态，只与上一行的状态有关，那么，我们在记录
        # 时候，只需要记录上一行的状态就可以了
        # 因此，只需要两行进行迭代
        pre = 0
        cur = 1

        # 首先处理第一行, 也就是把pre = 0给求出来
        # 如果只有一行的时候
        dp = [self.buildRow(1<<C), self.buildRow(1<<C)]

        # 处理第一行
        # 那么对于每一个有效的状态，我们都需要计算其相应的可以坐的人数
        # 注意状态的处理
        # 这里我们要记住第一行已经有多少个位置不能坐人了
        pre_not_sit = self.sitMask(A, 0)

        # 备注：popCount 就是计算一个整数里面有多少个bit为1
        # GCC自带函数

        pre_max_ans = 0
        for i in range(1, (1<<C)):
            # 如果有人坐在了不能坐的位置上，那么这种情况直接跳过
            if (not ((i & pre_not_sit) > 0 or not self.isValidSit(i))):
                # 再看一下，有没有连续的1
                dp[pre][i] = self.popCount(i)
                pre_max_ans = max(pre_max_ans, dp[pre][i])

        for r in range(1, R):
            # 这里开始处理第r行
            cur_not_sit = self.sitMask(A, r)

            dp[cur][0] = pre_max_ans

            for i in range(1, (1<<C)):
                # 如果当前状态里面，与not_sit有重合的，这种情况直接收益为0
                if (((i & cur_not_sit)) or not self.isValidSit(i)):
                    dp[cur][i] = 0
                else:
                    # 如果这种坐法是有效的
                    cnt = self.popCount(i)
                    dp[cur][i] = cnt

                    # 前面可以坐的位置
                    pre_status = (1 << C) - 1
                    # 扣除掉前面不能坐的位置
                    for c in range(0, C):
                        if (((1 << c) & i) != 0):
                            # 扣除掉左上，右上
                            if (c - 1 >= 0):
                                mask = 1 << (c - 1)
                                pre_status &= ~mask

                            if (c + 1 < C):
                                mask = 1 << (c + 1)
                                pre_status &= ~mask

                    # 前面还有不能坐的位置
                    # 那么处理之后:pre_status里面为1的情况，都是可以坐的地方
                    pre_status &= ~pre_not_sit

                    # 那么我们需要看只有这几个位置可以坐的地方
                    for p in range(0, pre_status + 1):
                        # 如果p里面的1，有不在pre_status里面的，那么不能处理
                        if ((p & (~pre_status)) != 0):
                            continue
                        dp[cur][i] = max(dp[cur][i], dp[pre][p] + cnt)

                    pre_max_ans = max(pre_max_ans, dp[cur][i])

            # swap(cur, pre)
            t = cur
            cur = pre
            pre = t

            pre_not_sit = cur_not_sit

        ans = 0
        for i in range(0, (1<<C)):
            ans = max(ans, dp[pre][i])
        return ans
# @lc code=end