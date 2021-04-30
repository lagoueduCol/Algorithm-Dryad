/*
 * @lc app=leetcode.cn id=32 lang=java
 *
 * [32] 最长有效括号
 *
 * https://leetcode-cn.com/problems/longest-valid-parentheses/description/
 *
 * algorithms
 * Hard (34.80%)
 * Likes:    1283
 * Dislikes: 0
 * Total Accepted:    145.9K
 * Total Submissions: 419.1K
 * Testcase Example:  '"(()"'
 *
 * 给你一个只包含 '(' 和
 * ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 *
 *
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：s = "(()"
 * 输出：2
 * 解释：最长有效括号子串是 "()"
 *
 *
 * 示例 2：
 *
 *
 * 输入：s = ")()())"
 * 输出：4
 * 解释：最长有效括号子串是 "()()"
 *
 *
 * 示例 3：
 *
 *
 * 输入：s = ""
 * 输出：0
 *
 *
 *
 *
 * 提示：
 *
 *
 * 0
 * s[i] 为 '(' 或 ')'
 *
 *
 *
 *
 */
import java.util.*;
import java.util.Stack;

// @lc code=start
class Solution {
  public int longestValidParentheses(String s) {
    // 拿到字符串的长度
    final int N = s == null ? 0 : s.length();
    if (N <= 1) {
      return 0;
    }

    Stack<Integer> st = new Stack<>();

    // 最长的有效长度
    int ans = 0;
    int start = 0;

    for (int i = 0; i < N; i++) {
      final char c = s.charAt(i);
      if (c == ')') {
        // 如果从[start, i]这个区间里面
        // 右括号已经可以匹配掉所有的左括号了
        if (st.isEmpty()) {
          start = i + 1;
        } else {
          st.pop();
          final int base = st.isEmpty() ? start : st.peek() + 1;
          ans = Math.max(ans, i - base + 1);
        }
      } else {
        st.push(i);
      }
    }

    return ans;
  }
}
// @lc code=end
