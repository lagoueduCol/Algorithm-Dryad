/*
 * @lc app=leetcode.cn id=28 lang=java
 *
 * [28] 实现 strStr()
 *
 * https://leetcode-cn.com/problems/implement-strstr/description/
 *
 * algorithms
 * Easy (39.71%)
 * Likes:    773
 * Dislikes: 0
 * Total Accepted:    326.5K
 * Total Submissions: 822.2K
 * Testcase Example:  '"hello"\n"ll"'
 *
 * 实现 strStr() 函数。
 * 
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置
 * (从0开始)。如果不存在，则返回  -1。
 * 
 * 示例 1:
 * 
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 * 
 * 
 * 示例 2:
 * 
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 * 
 * 
 * 说明:
 * 
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * 
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 * 
 */

// @lc code=start

class Solution {
    static private int[] pos = new int[256];

    public int strStr(String main, String sub) {
        if (sub == null || sub.length() == 0) {
            return 0;
        }
        if (main == null || main.length() == 0) {
            return -1;
        }

        final int mainLen = main.length();
        final int subLen = sub.length();

        int i = 0;
        int j = 0;

        for (j = 0; j < pos.length; j++) {
            pos[j] = -1;
        }

        for (j = 0; j < subLen; j++) {
            pos[(int)sub.charAt(j)] = j;
        }

        while (i <= mainLen - subLen) {
            j = 0;

            while (main.charAt(i + j) == sub.charAt(j)) {
                j++;
                if (j >= subLen) {
                    return i;
                }
            }

            // 如果Target Char不在main的范围里面了
            if (i + subLen >= mainLen) {
                return -1;
            }

            final int lastChar = (int)main.charAt(i + subLen);
            i += subLen - pos[lastChar];
        }

        return -1;
    }
}

// @lc code=end

