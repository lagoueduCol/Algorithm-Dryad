#
# @lc app=leetcode.cn id=28 lang=python
#
# [28] 实现 strStr()
#
# https://leetcode-cn.com/problems/implement-strstr/description/
#
# algorithms
# Easy (39.71%)
# Likes:    773
# Dislikes: 0
# Total Accepted:    326.5K
# Total Submissions: 822.2K
# Testcase Example:  '"hello"\n"ll"'
#
# 实现 strStr() 函数。
# 
# 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置
# (从0开始)。如果不存在，则返回  -1。
# 
# 示例 1:
# 
# 输入: haystack = "hello", needle = "ll"
# 输出: 2
# 
# 
# 示例 2:
# 
# 输入: haystack = "aaaaa", needle = "bba"
# 输出: -1
# 
# 
# 说明:
# 
# 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
# 
# 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
# 
#

# @lc code=start
class Solution(object):
    # 先不管这个函数的实现
    def buildPMT(self, sub):
        N = 0 if not sub else len(sub)
        next = [0] * (N + 1)

        i = 0
        j = -1
        next[0] = -1

        while i < N:
            if -1 == j or sub[i] == sub[j]:
                i += 1
                j += 1
                next[i] = j
            else:
                j = next[j]

        return next[1:]

    def strStr(self, main, sub):
        alen = 0 if not main else len(main)
        blen = 0 if not sub else len(sub)

        pmt = self.buildPMT(sub)

        i = 0
        j = 0
        while i < alen and j < blen:
            if main[i] == sub[j]:
                i += 1
                j += 1
            else:
                if j == 0:
                    i += 1
                else:
                    j = pmt[j-1]

        return i - blen if j == blen else -1

# @lc code=end

