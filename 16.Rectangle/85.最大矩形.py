#
# @lc app=leetcode.cn id=85 lang=python
#
# [85] 最大矩形
#
# https://leetcode-cn.com/problems/maximal-rectangle/description/
#
# algorithms
# Hard (51.62%)
# Likes:    884
# Dislikes: 0
# Total Accepted:    75.8K
# Total Submissions: 146.7K
# Testcase Example:  '[["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]'
#
# 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
# 
# 
# 
# 示例 1：
# 
# 
# 输入：matrix =
# [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
# 输出：6
# 解释：最大矩形如上图所示。
# 
# 
# 示例 2：
# 
# 
# 输入：matrix = []
# 输出：0
# 
# 
# 示例 3：
# 
# 
# 输入：matrix = [["0"]]
# 输出：0
# 
# 
# 示例 4：
# 
# 
# 输入：matrix = [["1"]]
# 输出：1
# 
# 
# 示例 5：
# 
# 
# 输入：matrix = [["0","0"]]
# 输出：0
# 
# 
# 
# 
# 提示：
# 
# 
# rows == matrix.length
# cols == matrix[0].length
# 0 
# matrix[i][j] 为 '0' 或 '1'
# 
# 
#

# @lc code=start
class Solution(object):
    def compute(self, A):
        N = 0 if not A else len(A)

        # 递增栈
        t = []

        ans = 0

        for i in range(0, N+1):
            x = A[i] if i < N else -1
            while len(t) > 0 and A[t[-1]] > x:
                # 这里以A[idx]为高度
                idx = t[-1]
                height = A[idx]
                t.pop()
                # 根据性质2，右边比A[idx]大的就是[idx + 1... i)
                rightPos = i
                leftPos = t[-1] if len(t) > 0 else -1

                width = rightPos - leftPos - 1
                area = height * width

                ans = max(ans, area)
            t.append(i)
        
        return ans

    def maximalRectangle(self, matrix):
        if not matrix or len(matrix) == 0 or not matrix[0] or len(matrix[0]) == 0:
            return 0

        rows = len(matrix)
        cols = len(matrix[0])

        sum = [0]*(cols+1)
        ans = 0

        for r in range(rows):
            for c in range(cols):
                sum[c] = sum[c] + 1 if matrix[r][c] == '1' else 0
            ans = max(ans, self.compute(sum))

        return ans

# @lc code=end

