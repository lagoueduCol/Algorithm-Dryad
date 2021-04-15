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
    // 在学习PMT的
    private int[] buildNext(String sub) {
        final int N = sub == null ? 0 : sub.length();

        int[] next = new int[N + 1];
        int i = 0;
        int j = -1;

        next[0] = -1;

        while (i < N) {
            if (j == -1 || sub.charAt(i) == sub.charAt(j)) {
                i++;
                j++;
                next[i] = j;
            } else {
                j = next[j];
            }
        }

        return next;
    }

    public int strStr(String main, String sub) {
        int i = 0;
        int j = 0;

        final int alen = main.length();
        final int blen = sub.length();

        int[] next = buildNext(sub);

        while (i < alen && j < blen) {
            if (-1 == j || main.charAt(i) == sub.charAt(j)) {
                // 如果匹配成功，那么向前走
                // 这里和暴力的方法没有区别
                i++;
                j++;
            } else {
                j = next[j];
            }
        }

        // 看一下是不是匹配完了
        return j == blen ? i - blen : -1;
    }
}
// @lc code=end