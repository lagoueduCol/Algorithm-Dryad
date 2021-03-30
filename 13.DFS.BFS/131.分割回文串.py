#
# @lc app=leetcode.cn id=131 lang=python
#
# [131] 分割回文串
#
# https://leetcode-cn.com/problems/palindrome-partitioning/description/
#
# algorithms
# Medium (72.74%)
# Likes:    663
# Dislikes: 0
# Total Accepted:    96.4K
# Total Submissions: 132.5K
# Testcase Example:  '"aab"'
#
# 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
# 
# 回文串 是正着读和反着读都一样的字符串。
# 
# 
# 
# 示例 1：
# 
# 
# 输入：s = "aab"
# 输出：[["a","a","b"],["aa","b"]]
# 
# 
# 示例 2：
# 
# 
# 输入：s = "a"
# 输出：[["a"]]
# 
# 
# 
# 
# 提示：
# 
# 
# 1 
# s 仅由小写英文字母组成
# 
# 
#

# @lc code=start

class Solution(object):
    def Clone(self, box, ans):
        tmp = []
        for x in box:
            tmp.append(x)
        ans.append(tmp)
    
    def backtrace(self, s, dp, start, box, ans):
        N = len(s)
        if start == N:
            self.Clone(box, ans)
            return
        
        for cut in range(start, N):
            if dp[start][cut]:
                word = s[start:cut+1]
                box.append(word)
                self.backtrace(s, dp, cut + 1, box, ans)
                box.pop()

    def Build(self, s, dp):
        N = len(s)

        for i in range(N):
            dp[i][i] = True
        
        for i in range(N-1):
            dp[i][i+1] = s[i] == s[i+1]
        
        for x in range(2, N):
            for i in range(0, N - x):
                j = i + x
                dp[i][j] = s[i] == s[j] and dp[i+1][j-1]

    def partition(self, s):
        if not s or len(s) == 0:
            return []
        N = len(s)
        dp = [[False for i in range(N)] for j in range(N)]

        self.Build(s, dp)

        box = []
        ans = []

        self.backtrace(s, dp, 0, box, ans)

        return ans
# @lc code=end

