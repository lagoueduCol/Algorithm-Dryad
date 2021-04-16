/*
 * @lc app=leetcode.cn id=459 lang=java
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
    // 如果一个字符串可以由一个子串重复构成，比如
    // AAAA
    // 那么前后缀的最长匹配为"AAA"
    // 那么s.length() - 这个最长匹配
    // 就是得到重复部分的长度
    // 构建PMT
    // 不过，这里我们只需要整个字符串的PMT
    // 所以我们只需要返回最后一项就可以了。
    private int buildPMT(String sub) {
        final int N = sub == null ? 0 : sub.length();

        int[] PMT = new int[N];

        int i = 1;
        int j = 0;

        PMT[0] = 0;

        while (i < N) {
            if (sub.charAt(i) == sub.charAt(j)) {
                // 当相等的时候，
                i++;
                j++;
                PMT[i-1] = j;
            } else {
                if (0 == j) {
                    // 如果匹配失败，并且j已经为0
                    // 那么
                    i++;
                    PMT[i-1] = 0;
                } else {
                    j = PMT[j-1];
                }
            }
        }

        return PMT[N-1];
    }

    public boolean repeatedSubstringPattern(String s) {
        if (s == null || s.length() <= 1) {
            return false;
        }

        final int matchedLen = buildPMT(s);
        final int partLen = s.length() - matchedLen;

        if (matchedLen == 0 || s.length() % partLen != 0) {
            return false;
        }

        for (int i = 0; i < s.length(); i++) {
            final int mappedPos = i % partLen;
            if (s.charAt(i) != s.charAt(mappedPos)) {
                return false;
            }
        }

        return true;
    }
}
// @lc code=end