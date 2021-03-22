#
# @lc app=leetcode.cn id=47 lang=python
#
# [47] 全排列 II
#
# https://leetcode-cn.com/problems/permutations-ii/description/
#
# algorithms
# Medium (62.96%)
# Likes:    636
# Dislikes: 0
# Total Accepted:    148K
# Total Submissions: 235.1K
# Testcase Example:  '[1,1,2]'
#
# 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
# 
# 
# 
# 示例 1：
# 
# 
# 输入：nums = [1,1,2]
# 输出：
# [[1,1,2],
# ⁠[1,2,1],
# ⁠[2,1,1]]
# 
# 
# 示例 2：
# 
# 
# 输入：nums = [1,2,3]
# 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
# 
# 
# 
# 
# 提示：
# 
# 
# 1 
# -10 
# 
# 
#

# @lc code=start
class Solution(object):
    def append(self, box, ans):
        ans.append([])
        for x in box:
            ans[-1].append(x)

    def swap(self, box, i, j):
        t = box[i]
        box[i] = box[j]
        box[j] = t

    def backtrace(self, box, start, ans):
        N = 0 if not box else len(box)
        if start >= N:
            self.append(box, ans)
            return
        
        s = set()
        for j in range(start, N):
            if not (box[j] in s):
                s.add(box[j])
                self.swap(box, start, j)
                self.backtrace(box, start + 1, ans)
                self.swap(box, start, j)

    def permuteUnique(self, A):
        ans = []
        self.backtrace(A, 0, ans)
        return ans
# @lc code=end

