import java.util.Collections;
import java.util.List;
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
class Solution
{
  public int longestValidParentheses(String s)
  {
    final int N = s == null ? 0 : s.length();

    if (N <= 1) {
      return 0;
    }

    // 记录每个配对成功的位置
    int[] pairs = new int[N];
    Arrays.fill(pairs, -1);

    // 栈
    Stack<Integer> st = new Stack<>();

    for (int i = 0; i < N; i++) {

      // 我们想找到每个位置匹配的地方
      final char c = s.charAt(i);

      if (c == ')') {
        if (!st.empty()) {
          // 我们可以找到i匹配的位置中st.top();
          final int idx = st.pop();
          pairs[idx] = i;
          pairs[i] = idx;
        }
      } else {
        st.push(i);
      }
    }

    // 记录最长的匹配连续区域
    int ans = 0;

    // 匹配成功的区域, 初始区间为[0,0)
    int rangeStart = 0;
    int rangeEnd = 0;

    for (int i = 0; i < pairs.length; i++) {
      // 拿到当前值
      final int cur = pairs[i] != -1 ? 1 : 0;

      // 如果当前值为cur == 1
      // 那么表示可以在之前的区间的基础上延续
      if (cur == 1) {
        rangeEnd = i + 1;
      } else {
        rangeStart = i + 1;
      }

      ans = Math.max(ans, rangeEnd - rangeStart);
    }

    return ans;
  }
}
// @lc code=end
