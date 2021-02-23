#
#
# [1696] 跳跃游戏 VI
#
# https://leetcode-cn.com/problems/jump-game-vi/description/
#
# algorithms
# Medium (33.27%)
# Likes:    18
# Dislikes: 0
# Total Accepted:    2.5K
# Total Submissions: 7.4K
# Testcase Example:  '[1,-1,-2,4,-7,3]\n2'
#
# 给你一个下标从 0 开始的整数数组 nums 和一个整数 k 。
# 
# 一开始你在下标 0 处。每一步，你最多可以往前跳 k 步，但你不能跳出数组的边界。也就是说，你可以从下标 i 跳到 [i + 1， min(n - 1,
# i + k)] 包含 两个端点的任意位置。
# 
# 你的目标是到达数组最后一个位置（下标为 n - 1 ），你的 得分 为经过的所有数字之和。
# 
# 请你返回你能得到的 最大得分 。
# 
# 
# 
# 示例 1：
# 
# 
# 输入：nums = [1,-1,-2,4,-7,3], k = 2
# 输出：7
# 解释：你可以选择子序列 [1,-1,4,3] （上面加粗的数字），和为 7 。
# 
# 
# 示例 2：
# 
# 
# 输入：nums = [10,-5,-2,4,0,3], k = 3
# 输出：17
# 解释：你可以选择子序列 [10,4,3] （上面加粗数字），和为 17 。
# 
# 
# 示例 3：
# 
# 
# 输入：nums = [1,-5,-20,4,-1,3,-6,-3], k = 2
# 输出：0
# 
# 
# 
# 
# 提示：
# 
# 
# 1 
# -10^4 
# 
# 
#


from collections import deque

class Solution(object):
    def maxResult(self, A, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: int
        """
        # 处理掉各种边界条件!
        if not A or len(A) == 0 or k <= 0:
            return 0

        # 单调队列，这里并不是严格递减
        Q = deque()

        # 每个位置可以收集到的金币数目
        get = [0] * len(A)

        for i in range(0, len(A)):
            # 在取最大值之前，需要保证单调队列中都是有效值。
            # 也就是都在区间里面的值
            # 当要求get[i]的时候，
            # 单调队列中应该是只能保存[i-k, i-1]这个范围的数
            if i - k > 0:
                if Q and Q[0] == get[i-k-1]:
                    Q.popleft()

            get[i] = (Q[0] + A[i]) if Q else A[i]

            while Q and Q[-1] < get[i]:
                Q.pop()
            Q.append(get[i])

        return get[-1]


