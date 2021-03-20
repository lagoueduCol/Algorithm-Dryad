#
# @lc app=leetcode.cn id=39 lang=python
#
# [39] 组合总和
#
# https://leetcode-cn.com/problems/combination-sum/description/
#
# algorithms
# Medium (72.11%)
# Likes:    1225
# Dislikes: 0
# Total Accepted:    223.4K
# Total Submissions: 309.7K
# Testcase Example:  '[2,3,6,7]\n7'
#
# 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
# 
# candidates 中的数字可以无限制重复被选取。
# 
# 说明：
# 
# 
# 所有数字（包括 target）都是正整数。
# 解集不能包含重复的组合。 
# 
# 
# 示例 1：
# 
# 输入：candidates = [2,3,6,7], target = 7,
# 所求解集为：
# [
# ⁠ [7],
# ⁠ [2,2,3]
# ]
# 
# 
# 示例 2：
# 
# 输入：candidates = [2,3,5], target = 8,
# 所求解集为：
# [
# [2,2,2,2],
# [2,3,3],
# [3,5]
# ]
# 
# 
# 
# 提示：
# 
# 
# 1 <= candidates.length <= 30
# 1 <= candidates[i] <= 200
# candidate 中的每个元素都是独一无二的。
# 1 <= target <= 500
# 
# 
#

# @lc code=start
class Solution(object):
    def append(self, box, ans):
        ans.append([])
        for x in box:
            ans[-1].append(x)

    def backtrack(self, A, start, target, boxSum, box, ans):
        N = 0 if not A else len(A)

        if boxSum == target:
            self.append(box, ans)
        
        if boxSum >= target or start >= N:
            return
        
        for i in range(start, N):
            box.append(A[i])
            boxSum += A[i]

            self.backtrack(A, i, target, boxSum, box, ans)

            box.pop()
            boxSum -= A[i]

    def combinationSum(self, A, target):
        box = []
        ans = []

        self.backtrack(A, 0, target, 0, box, ans)
        return ans
# @lc code=end

