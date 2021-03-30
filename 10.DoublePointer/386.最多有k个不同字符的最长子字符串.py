# /*
# 给定字符串S，找到最多有k个不同字符的最长子串T。
# 
# 样例
# 样例 1:
# 
# 输入: S = "eceba" 并且 k = 3
# 输出: 4
# 解释: T = "eceb"
# 样例 2:
# 
# 输入: S = "WORLD" 并且 k = 4
# 输出: 4
# 解释: T = "WORL" 或 "ORLD"
# 挑战
# O(n) 时间复杂度
# 
# https://www.lintcode.com/problem/386/
# */

class Solution:
    """
    @param s: A string
    @param k: An integer
    @return: An integer
    """
    def lengthOfLongestSubstringKDistinct(self, s, k):
        # write your code here
        N = 0 if not s else len(s)
        left = -1
        ans = 0
        cnt = 0
        H = [0] * 256

        for i in range(N):
            c = ord(s[i])
            if H[c] == 0:
                cnt += 1
            H[c]+= 1

            while left < i and cnt > k:
                left += 1
                c = ord(s[left])

                if H[c] == 1:
                    cnt -= 1
                H[c] -= 1
            
            ans = max(ans, i - left)
        
        return ans