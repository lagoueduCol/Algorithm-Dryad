import java.util.Stack;

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

// @lc code=start
class Solution {
  public int longestValidParentheses(String s) {
    // 拿到字符串的长度
    final int N = s == null ? 0 : s.length();
    if (N <= 1) {
      return 0;
    }

    int[] dp = new int[N];
    int ans = 0;

    // 前面两个字符的情况
    // 只有当()为字符串的时候，这两个是dp[0] = 0, dp[1] = 2;
    // 其他都是dp[0] = dp[1] = 0;
    if (s.charAt(0) == '(' && s.charAt(1) == ')') {
      dp[1] = 2;
      ans = 2;
    }

    for (int i = 2; i < N; i++) {
      final char c = s.charAt(i);

      // dp[i] = 0; // java的数组默认会初始化
      // 所以dp[i] = 0;可以跳过

      if (c == ')') {
        // 看一下preChar
        final char preChar = s.charAt(i - 1);

        if (preChar == '(') {
          // 前面是xxx()的结构
          dp[i] = 2 + dp[i - 2];
        } else {
          // 前面是一个 xxx))的结构
          // 那么只需要找到更之前的字符就可以了
          final int preLen = dp[i - 1];
          // [start, i-1] = preLen
          // i - start = preLen
          final int start = i - preLen;
          // 那么找到与 i 配对的位置
          final int pairPos = start - 1;
          if (pairPos >= 0 && s.charAt(pairPos) == '(') {
            // 如果能够与i匹配成功
            // 那么形成的结构就是xx((xxxx))
            // 我们还需要看一下之前的长度是不是可以加上去
            int curLen = i + 1 - pairPos;
            // 之前还有长度吗？
            if (pairPos - 1 >= 0) {
              curLen += dp[pairPos - 1];
            }

            dp[i] = curLen;
          }
        }
      }
      ans = Math.max(ans, dp[i]);
    }

    return ans;
  }
}
// @lc code=end
