#
# @lc app=leetcode.cn id=845 lang=python
#
# [845] 数组中的最长山脉
#
# https://leetcode-cn.com/problems/longest-mountain-in-array/description/
#
# algorithms
# Medium (41.70%)
# Likes:    174
# Dislikes: 0
# Total Accepted:    33.3K
# Total Submissions: 79.9K
# Testcase Example:  '[2,1,4,7,3,2,5]'
#
# 我们把数组 A 中符合下列属性的任意连续子数组 B 称为 “山脉”：
# 
# 
# B.length >= 3
# 存在 0 < i < B.length - 1 使得 B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... >
# B[B.length - 1]
# 
# 
# （注意：B 可以是 A 的任意子数组，包括整个数组 A。）
# 
# 给出一个整数数组 A，返回最长 “山脉” 的长度。
# 
# 如果不含有 “山脉” 则返回 0。
# 
# 
# 
# 示例 1：
# 
# 输入：[2,1,4,7,3,2,5]
# 输出：5
# 解释：最长的 “山脉” 是 [1,4,7,3,2]，长度为 5。
# 
# 
# 示例 2：
# 
# 输入：[2,2,2]
# 输出：0
# 解释：不含 “山脉”。
# 
# 
# 
# 
# 提示：
# 
# 
# 0 <= A.length <= 10000
# 0 <= A[i] <= 10000
# 
# 
#

# @lc code=start
class Solution(object):
    def longestMountain(self, A):
        """
        :type A: List[int]
        :rtype: int
        """

        N = len(A) if A else 0
        if N < 3:
            return 0

        left = -1
        ans = 0

        status = -1
        preValue = A[0]

        for i in range(1, N):
            x = A[i]

            if status == -1:
                # 如果只有一个元素
                # 那么只有上升才不会破坏区间状态
                if x > preValue:
                    # 把状态修改为上升
                    status = 0
                else:
                    # 重新设置起点
                    left = i - 1
            elif status == 0:
                # 如果现在正在上升
                if x == preValue:
                    status = -1
                    left = i - 1
                elif x < preValue:
                    status = 1
            else:
                # 如果现在正在下降

                if x == preValue:
                    # 如果值相等
                    status = -1
                    left = i - 1
                elif x > preValue:
                    # 如果值比前面的大
                    # 那么注意，这里已经有两个上升元素了
                    status = 0
                    left = i - 2
            
            preValue = x

            if status == 1:
                ans = max(ans, i - left)
        
        return ans if ans >= 3 else 0


# @lc code=end

