#
# @lc app=leetcode.cn id=76 lang=python
#
# [76] 最小覆盖子串
#
# https://leetcode-cn.com/problems/minimum-window-substring/description/
#
# algorithms
# Hard (40.63%)
# Likes:    949
# Dislikes: 0
# Total Accepted:    107.9K
# Total Submissions: 265.4K
# Testcase Example:  '"ADOBECODEBANC"\n"ABC"'
#
# 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
# 
# 注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。
# 
# 
# 
# 示例 1：
# 
# 
# 输入：s = "ADOBECODEBANC", t = "ABC"
# 输出："BANC"
# 
# 
# 示例 2：
# 
# 
# 输入：s = "a", t = "a"
# 输出："a"
# 
# 
# 
# 
# 提示：
# 
# 
# 1 
# s 和 t 由英文字母组成
# 
# 
# 
# 进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？
#

# @lc code=start
class Solution(object):
    def minWindow(self, s, t):
        N = 0 if not s else len(s)
        if not s or N == 0 or not t:
            return ""

        H = {}
        for x in t:
            H[ord(x)] = H.get(ord(x), 0) + 1
        
        R = {}
        left = -1
        equal = 0

        start = 1
        end = 0

        for i in range(N):
            c = ord(s[i])
            R[c] = R.get(c, 0) + 1

            if H.get(c, 0) > 0 and R[c] == H[c]:
                equal += 1
            
            while left < i and equal >= len(H):
                if start > end or end - start > i - left:
                    start = left
                    end = i
                
                left += 1
                c = ord(s[left])

                if R.get(c, 0) == H.get(c, 0) and H.get(c, 0) > 0:
                    equal -= 1
                R[c] = R.get(c, 0) - 1
                if (R.get(c, 0) <= 0):
                    R.pop(c)
        
        if start >= end:
            return ""
        
        return s[start + 1:end + 1]
# @lc code=end

