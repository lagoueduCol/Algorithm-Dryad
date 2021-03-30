#
# @lc app=leetcode.cn id=30 lang=python
#
# [30] 串联所有单词的子串
#
# https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words/description/
#
# algorithms
# Hard (33.80%)
# Likes:    417
# Dislikes: 0
# Total Accepted:    56.2K
# Total Submissions: 166.2K
# Testcase Example:  '"barfoothefoobarman"\n["foo","bar"]'
#
# 给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
# 
# 注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。
# 
# 
# 
# 示例 1：
# 
# 输入：
# ⁠ s = "barfoothefoobarman",
# ⁠ words = ["foo","bar"]
# 输出：[0,9]
# 解释：
# 从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
# 输出的顺序不重要, [9,0] 也是有效答案。
# 
# 
# 示例 2：
# 
# 输入：
# ⁠ s = "wordgoodgoodgoodbestword",
# ⁠ words = ["word","good","best","word"]
# 输出：[]
# 
# 
#

# @lc code=start
class Counter(object):
    def __init__(self):
        self.H = {}
    def get(self, w):
        return self.H.get(w, 0)
    def add(self, w, v):
        self.H[w] = self.H.get(w, 0) + v
        if self.H.get(w, 0) == 0:
            self.H.pop(w)
    def size(self):
        return len(self.H)

class Solution(object):
    def findSubstring(self, s, D):
        if not s or len(s) == 0 or len(D) == 0:
            return []
        
        N = len(s)
        L = len(D[0])

        H = Counter()
        for x in D:
            H.add(x, 1)

        ans = []

        for start in range(L):
            left = start - L
            equal = 0
            item = 0
            R = Counter()

            for i in range(start, N, L):
                w = s[i:i+L]
                item += 1

                R.add(w, 1)
                if (R.get(w) == H.get(w)):
                    equal += 1

                if item < len(D):
                    continue

                if equal == H.size():
                    ans.append(left + L)

                left += L
                w = s[left:left+L]
                if R.get(w) == H.get(w):
                    equal -= 1
                
                R.add(w, -1)
        return ans
# @lc code=end

