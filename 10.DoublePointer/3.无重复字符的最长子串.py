#
# @lc app=leetcode.cn id=3 lang=python
#
# [3] 无重复字符的最长子串
#
# https:#leetcode-cn.com/problems/longest-substring-without-repeating-characters/description/
#
# algorithms
# Medium (36.36%)
# Likes:    4961
# Dislikes: 0
# Total Accepted:    827.4K
# Total Submissions: 2.3M
# Testcase Example:  '"abcabcbb"'
#
# 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
# 
# 
# 
# 示例 1:
# 
# 
# 输入: s = "abcabcbb"
# 输出: 3 
# 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
# 
# 
# 示例 2:
# 
# 
# 输入: s = "bbbbb"
# 输出: 1
# 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
# 
# 
# 示例 3:
# 
# 
# 输入: s = "pwwkew"
# 输出: 3
# 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
# 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
# 
# 
# 示例 4:
# 
# 
# 输入: s = ""
# 输出: 0
# 
# 
# 
# 
# 提示：
# 
# 
# 0 
# s 由英文字母、数字、符号和空格组成
# 
# 
#

# @lc code=start
class Solution(object):
    def lengthOfLongestSubstring(self, s):
        """
        :type s: str
        :rtype: int
        """
        N = len(s) if s else 0

        left = -1
        ans = 0

        pos = [-1] * 256

        for i in range(0, N):
            c = ord(s[i])
            # (left, i)已经是一个有效的解
            # 现在要把s[i]加进来
            # 1. 坏了才移动
            # 那么看一下是否会坏？
            if pos[c] > left:
                # 如果(left, i)里面已经有了s[i]
                # 那么需要移动左边，
                # 移动的时候，怎么移动？
                # 可以直接将left 移动到pos[s[i]]
                # 因为我们采用的区间是左开右闭
                left = pos[c]
            pos[c] = i
            ans = max(ans, i - left)
        
        return ans
# @lc code=end

