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
    // 构建PMT
    private int[] buildPMT(String sub) {
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

        return PMT;
    }

    public int strStr(String main, String sub) {
        if (sub == null || sub.length() == 0) {
            return 0;
        }

        if (main == null || main.length() == 0) {
            return -1;
        }

        int i = 0;
        int j = 0;

        final int alen = main.length();
        final int blen = sub.length();

        int[] PMT = buildPMT(sub);

        while (i < alen && j < blen) {
            if (main.charAt(i) == sub.charAt(j)) {
                // 如果匹配成功，那么向前走
                // 这里和暴力的方法没有区别
                i++;
                j++;
            } else {
                // 如果匹配失败，我们这里要跳过一些无效的比较
                if (j == 0) {
                    // 这里需要移动i
                    i++;
                } else {
                    // 跳过无效的比较!
                    j = PMT[j-1];
                }
            }
        }

        // 看一下是不是匹配完了
        return j == blen ? i - blen : -1;
    }
}
// @lc code=end

