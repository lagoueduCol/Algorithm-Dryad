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

// @lc code=start
class Solution {
  public int longestValidParentheses(String s) {
    final int N = s == null ? 0 : s.length();
    if (N <= 1) {
      return 0;
    }

    // 我们要找到每个位置对应的pair在什么地方
    int[] pairPos = new int[N];
    Arrays.fill(pairPos, -1);

    Stack<Integer> st = new Stack<>();

    for (int i = 0; i < N; i++) {
      final char c = s.charAt(i);

      // 如果遇到右括号，我们看看能不能找到匹配的位置
      if (c == ')') {
        // 如果可以找到匹配的位置
        if (!st.isEmpty()) {
          pairPos[i] = st.peek();
          pairPos[st.peek()] = i;
          st.pop();
        }
      } else {
        st.push(i);
      }
    }

    // 接下来，我们只需要在pairPos数组中找到连续的，中间不带-1的
    // 最长的就可以了
    int tmp_sum = 0;
    int max_sum = 0;

    for (int i = 0; i < N; i++) {
      // 如果遇到了-1，那么我们就让tmp_sum变得足够小
      final int v = (pairPos[i] == -1) ? Integer.MIN_VALUE / 2 : 1;
      tmp_sum = Math.max(tmp_sum, 0) + v;
      max_sum = Math.max(max_sum, tmp_sum);
    }

    return max_sum;
  }
}
// @lc code=end

public class Main
{
  public static void main(String[] args)
  {
    Solution s = new Solution();
    String tmp = "()(()";
    System.out.println(s.longestValidParentheses(tmp));
  }
}