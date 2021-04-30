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
    // 采用相每重叠的最长的区间的做法
    final int N = s == null ? 0 : s.length();

    // 两两配对的时候，我们将它们看成一个区间
    // 配对的时候，需要用到栈
    Stack<Integer> st = new Stack<>();

    // 记录每个点对应的位置
    int[] pairPos = new int[N];
    Arrays.fill(pairPos, -1);

    for (int i = 0; i < N; i++) {
      final char c = s.charAt(i);

      if (c == ')') {
        if (!st.isEmpty()) {
          final int topIdx = st.pop();
          pairPos[topIdx] = i;
        }
      } else {
        st.push(i);
      }
    }

    // 由于得到了很多区间，我们需要取相互覆盖的区间的最长值
    // 比如，我们认为[3,4], [5,6]是相互连续且覆盖的区间
    int start = 0;
    int end = -1;
    int ans = 0;

    for (int i = 0; i < N; i++) {
      if (pairPos[i] == -1) {
        continue;
      }

      final int from = i, to = pairPos[i];

      // 如果和[start, end]这个区间相交
      if (from <= end + 1) {
        end = Math.max(end, to);
      } else {
        // 如果不相交!
        start = from;
        end = to;
      }

      ans = Math.max(ans, end - start + 1);
    }

    return ans;
  }
}
// @lc code=end
