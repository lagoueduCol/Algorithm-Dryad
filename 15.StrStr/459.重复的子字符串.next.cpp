/*
 * @lc app=leetcode.cn id=459 lang=cpp
 *
 * [459] 重复的子字符串
 *
 * https://leetcode-cn.com/problems/repeated-substring-pattern/description/
 *
 * algorithms
 * Easy (51.07%)
 * Likes:    482
 * Dislikes: 0
 * Total Accepted:    66.8K
 * Total Submissions: 130.8K
 * Testcase Example:  '"abab"'
 *
 * 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。
 * 
 * 示例 1:
 * 
 * 
 * 输入: "abab"
 * 
 * 输出: True
 * 
 * 解释: 可由子字符串 "ab" 重复两次构成。
 * 
 * 
 * 示例 2:
 * 
 * 
 * 输入: "aba"
 * 
 * 输出: False
 * 
 * 
 * 示例 3:
 * 
 * 
 * 输入: "abcabcabcabc"
 * 
 * 输出: True
 * 
 * 解释: 可由子字符串 "abc" 重复四次构成。 (或者子字符串 "abcabc" 重复两次构成。)
 * 
 * 
 */

// @lc code=start
class Solution {
    int buildNext(string &s) {
        const int N = s.length();
        int next[N+1];

        int i = 0;
        int j = -1;
        next[0] = -1;

        while (i < N) {
            if (-1 == j || s[i] == s[j]) {
                i++;
                j++;
                next[i] = j;
            } else {
                j = next[j];
            }
        }

        // 最后一项就是整个字符串的前后缀的最长匹配
        return next[N];
    }

public:
    bool repeatedSubstringPattern(string s) {
        const int N = s.length();

        if (N <= 1) {
            return false;
        }

        const int pmt_len = buildNext(s);
        // 这里得到重复部分的长度
        const int rep_len = N - pmt_len;

        if (pmt_len == 0 || N  % rep_len) {
            return false;
        }

        for (int i = 0; i < N; i++) {
            const int map_pos = i % rep_len;
            if (s[i] != s[map_pos]) {
                return false;
            }
        }

        return true;
    }
};
// @lc code=end

