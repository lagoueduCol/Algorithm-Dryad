#
# @lc app=leetcode.cn id=45 lang=python
#
# [45] 跳跃游戏 II
#
# https://leetcode-cn.com/problems/jump-game-ii/description/
#
# algorithms
# Hard (38.45%)
# Likes:    863
# Dislikes: 0
# Total Accepted:    108.6K
# Total Submissions: 281.7K
# Testcase Example:  '[2,3,1,1,4]'
#
# 给定一个非负整数数组，你最初位于数组的第一个位置。
# 
# 数组中的每个元素代表你在该位置可以跳跃的最大长度。
# 
# 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
# 
# 示例:
# 
# 输入: [2,3,1,1,4]
# 输出: 2
# 解释: 跳到最后一个位置的最小跳跃数是 2。
# 从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
# 
# 
# 说明:
# 
# 假设你总是可以到达数组的最后一个位置。
# 
#

# @lc code=start
class Solution(object):
    def jump(self, A):
        N = 0 if not A else len(A)

        ans = 0
        i = 0

        while i < N and i + A[i] < N - 1:
            old = i + A[i]
            j = i + 1
            pos = old
            while j <= old:
                if j + A[j] > pos:
                    i = j
                    pos = j + A[j]
                j += 1

            if pos == old:
                return -1
            
            ans += 1
        
        return ans + (1 if (i < N - 1) else 0)
# @lc code=end

