# -*- coding: utf-8 -*-
#
# @lc app=leetcode.cn id=567 lang=python
#
# [567] 字符串的排列
#
# https://leetcode-cn.com/problems/permutation-in-string/description/
#
# algorithms
# Medium (41.56%)
# Likes:    305
# Dislikes: 0
# Total Accepted:    73.9K
# Total Submissions: 177.3K
# Testcase Example:  '"ab"\n"eidbaooo"'
#
# 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
# 
# 换句话说，第一个字符串的排列之一是第二个字符串的子串。
# 
# 
# 
# 示例 1：
# 
# 
# 输入: s1 = "ab" s2 = "eidbaooo"
# 输出: True
# 解释: s2 包含 s1 的排列之一 ("ba").
# 
# 
# 示例 2：
# 
# 
# 输入: s1= "ab" s2 = "eidboaoo"
# 输出: False
# 
# 
# 
# 
# 提示：
# 
# 
# 输入的字符串只包含小写字母
# 两个字符串的长度都在 [1, 10,000] 之间
# 
# 
#

# @lc code=start
class Solution(object):
    def checkInclusion(self, s1, s2):
        """
        :type s1: str
        :type s2: str
        :rtype: bool
        """
        if not s1:
            return True
        if not s2:
            return False
        
        N = len(s2)

        # 数组当hash表
        H = [0] * 256
        for x in s1:
            H[ord(x)] += 1

        # 记录hash表的大小
        hsize = 0
        for x in H:
            if x > 0:
                hsize += 1
        
        # 动态hash表
        R = [0] * 256
        cnt = 0

        ans = False

        left = -1
        for i in range(N):
            c = ord(s2[i])

            # 添加字符到窗口尾巴
            R[c] += 1
            if R[c] == H[c] and H[c] > 0:
                cnt += 1
            
            if i < len(s1) - 1:
                continue
            
            if cnt == hsize:
                return True

            # 移除头字符
            left += 1
            c = ord(s2[left])
            if R[c] == H[c] and H[c] > 0:
                cnt -= 1
            R[c] -= 1

        return False
# @lc code=end
