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
    def minWindow(self, A, B):
        """
        :type A: str
        :type B: str
        :rtype: str
        """

        alen = len(A) if A else 0
        blen = len(B) if B else 0

        SZ = 256
        bcnt = [0]* SZ

        for i in range(0, blen):
            bcnt[ord(B[i])] += 1
        
        buniq = 0
        for i in range(0, SZ):
            buniq += 1 if bcnt[i] > 0 else 0
        
        def getC(len):
            acnt = [0] * SZ
            large_cnt = 0
            for i in range(0, alen):
                c = ord(A[i])

                acnt[c] += 1
                if acnt[c] == bcnt[c]:
                    large_cnt += 1
                
                if i < len - 1:
                    continue
                
                if large_cnt >= buniq:
                    return 0, A[i + 1 - len: i + 1]
                
                old = ord(A[i + 1 - len])
                if bcnt[old] > 0 and acnt[old] == bcnt[old]:
                    large_cnt -= 1
                
                acnt[old] -= 1
            
            return -1, None
        
        l = blen
        r = alen + 1

        while l < r:
            m = l + ((r-l)>>1)
            mov, tmp = getC(m)
            if mov < 0:
                l = m + 1
            else:
                ans = tmp
                r = m

        if l > alen:
            return ""
        
        return ans
# @lc code=end

