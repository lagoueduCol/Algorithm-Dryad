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

    // 拿到所有的区间
    List<Integer> pairs = new ArrayList<>();

    // 栈
    Stack<Integer> st = new Stack<>();

    for (int i = 0; i < N; i++) {

      // 我们想找到每个位置匹配的地方
      final char c = s.charAt(i);

      if (c == ')') {
        if (!st.empty()) {
          // 我们可以找到i匹配的位置中st.top();
          final int idx = st.pop();
          pairs.add(idx);
          pairs.add(i);
        }
      } else {
        st.push(i);
      }
    }

    // 我们将pairs放在一起，然后排个序
    Collections.sort(pairs);

    // 记录最长的匹配连续区域
    int ans = 0;

    // 所有的匹配成功的区域，都是连在一起的
    // 那么把序之后，这些数字应该是连续的，中间没有中断过
    int preValue = -1;
    int rangeStart = 0;

    for (int i = 0; i < pairs.size(); i++) {
      // 拿到当前值
      final int cur = pairs.get(i);

      // 如果之前有值
      if (i > 0) {
        // 如果值不再连续
        if (cur != preValue + 1) {
          // 那么从[rangeStart, i)就是一个合法区间
          ans = Math.max(ans, i - rangeStart);
          rangeStart = i;
        }
      }

      preValue = cur;
    }

    ans = Math.max(ans, pairs.size() - rangeStart);

    return ans;
  }
}
// @lc code=end
