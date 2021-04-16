#
# @lc app=leetcode.cn id=459 lang=python
#
# [459] 重复的子字符串
#
# https://leetcode-cn.com/problems/repeated-substring-pattern/description/
#
# algorithms
# Easy (51.07%)
# Likes:    482
# Dislikes: 0
# Total Accepted:    66.8K
# Total Submissions: 130.8K
# Testcase Example:  '"abab"'
#
# 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。
# 
# 示例 1:
# 
# 
# 输入: "abab"
# 
# 输出: True
# 
# 解释: 可由子字符串 "ab" 重复两次构成。
# 
# 
# 示例 2:
# 
# 
# 输入: "aba"
# 
# 输出: False
# 
# 
# 示例 3:
# 
# 
# 输入: "abcabcabcabc"
# 
# 输出: True
# 
# 解释: 可由子字符串 "abc" 重复四次构成。 (或者子字符串 "abcabc" 重复两次构成。)
# 
# 
#

# @lc code=start
class Solution(object):
    def buildNext(self, s):
        N = 0 if not s else len(s)

        next = [0] * (N + 1)

        i = 0
        j = -1

        next[0] = -1

        while i < N:
            if -1 == j or s[i] == s[j]:
                i += 1
                j += 1
                next[i] = j
            else:
                j = next[j]
        
        return next[N]

    def repeatedSubstringPattern(self, s):
        N = 0 if not s else len(s)

        if N <= 1:
            return False

        pmt_len = self.buildNext(s)

        rep_len = N - pmt_len

        if not pmt_len or N % rep_len != 0:
            return False
        
        for i in range(N):
            map_pos = i % rep_len

            if s[i] != s[map_pos]:
                return False
            
        return True
# @lc code=end

