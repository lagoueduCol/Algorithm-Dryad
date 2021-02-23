/*
 * @lc app=leetcode.cn id=76 lang=java
 *
 * [76] 最小覆盖子串
 *
 * https://leetcode-cn.com/problems/minimum-window-substring/description/
 *
 * algorithms
 * Hard (40.63%)
 * Likes:    949
 * Dislikes: 0
 * Total Accepted:    107.9K
 * Total Submissions: 265.4K
 * Testcase Example:  '"ADOBECODEBANC"\n"ABC"'
 *
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s
 * 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 *
 * 注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 *
 *
 * 示例 2：
 *
 *
 * 输入：s = "a", t = "a"
 * 输出："a"
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1
 * s 和 t 由英文字母组成
 *
 *
 *
 * 进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？
 */

// @lc code=start
class Solution {
    public String minWindow(String A, String B) {
        // A 中包含B中所有字符
        final int alen = A == null ? 0 : A.length();
        final int blen = B == null ? 0 : B.length();

        int[] bstat = new int[256];
        int items = 0;
        for (int i = 0; i < blen; i++) {
            bstat[B.charAt(i)]++;
            if (bstat[B.charAt(i)] == 1) {
                items++;
            }
        }

        int left = -1;
        int ans = alen + 1;
        int start = 0;
        int[] astat = new int[256];
        int equal = 0;

        for (int i = 0; i < alen; i++) {
            astat[A.charAt(i)]++;
            if (astat[A.charAt(i)] == bstat[A.charAt(i)]) {
                equal++;
            }

            while (equal >= items) {
                if (ans > i - left) {
                    start = left + 1;
                    ans = i - left;
                }

                ++left;
                if (astat[A.charAt(left)] == bstat[A.charAt(left)]) {
                    equal--;
                }
                astat[A.charAt(left)]--;
            }
        }
        return ans <= alen ? A.substring(start, start + ans) : new String("");
    }
}
// @lc code=end
