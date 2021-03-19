#
# @lc app=leetcode.cn id=77 lang=python
#
# [77] 组合
#
# https://leetcode-cn.com/problems/combinations/description/
#
# algorithms
# Medium (76.55%)
# Likes:    526
# Dislikes: 0
# Total Accepted:    143.1K
# Total Submissions: 186.8K
# Testcase Example:  '4\n2'
#
# 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
# 
# 示例:
# 
# 输入: n = 4, k = 2
# 输出:
# [
# ⁠ [2,4],
# ⁠ [3,4],
# ⁠ [2,3],
# ⁠ [1,2],
# ⁠ [1,3],
# ⁠ [1,4],
# ]
# 
#

# @lc code=start
class Solution(object):
    def append(self, box, ans):
        ans.append([])
        for x in box:
            ans[-1].append(x)

    def backtrack(self, start, end, k, box, ans):
        if len(box) == k:
            self.append(box, ans)
        
        if len(box) >= k or start >= end:
            return
        
        for i in range(start, end):
            box.append(i)
            self.backtrack(i + 1, end, k, box, ans)
            box.pop()

    def combine(self, n, k):
        box = []
        ans = []
        self.backtrack(1, n + 1, k, box, ans)

        return ans
# @lc code=end

