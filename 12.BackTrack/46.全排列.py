#
# @lc app=leetcode.cn id=46 lang=python
#
# [46] 全排列
#
# https://leetcode-cn.com/problems/permutations/description/
#
# algorithms
# Medium (77.67%)
# Likes:    1222
# Dislikes: 0
# Total Accepted:    277.9K
# Total Submissions: 357.4K
# Testcase Example:  '[1,2,3]'
#
# 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
# 
# 示例:
# 
# 输入: [1,2,3]
# 输出:
# [
# ⁠ [1,2,3],
# ⁠ [1,3,2],
# ⁠ [2,1,3],
# ⁠ [2,3,1],
# ⁠ [3,1,2],
# ⁠ [3,2,1]
# ]
# 
#

# @lc code=start
class Solution(object):
    def append(self, box, ans):
        ans.append([])
        for x in box:
            ans[-1].append(x)

    def backtrack(self, A, i, used, box, ans):
        N = 0 if not A else len(A)

        if len(box) == N:
            self.append(box, ans)

        if i >= N:
            return
        
        for j in range(0, N):
            if not used[j]:
                used[j] = True
                box.append(A[j])
                self.backtrack(A, i + 1, used, box, ans)
                box.pop()
                used[j] = False

    def permute(self, A):
        N = 0 if not A else len(A)
        if N == 0:
            return []

        box = []
        ans = []
        used = [False] * N

        self.backtrack(A, 0, used, box, ans)

        return ans
# @lc code=end

