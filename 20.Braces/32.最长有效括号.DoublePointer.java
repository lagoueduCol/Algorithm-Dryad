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
    final int N = s == null ? 0 : s.length();

    if (N <= 1) {
      return 0;
    }

    // 首先我们想找到一个区间，这个区间必须要保证
    // 1. 最长
    // 2. 在任何时间，左括号数量 >= 右括号数量

    // 左括号的数量
    int leftBraceNumber = 0;
    // 右括号的数量
    int rightBraceNumber = 0;

    // 第一次扫描的区间的长度
    int maxEquLength1 = 0;

    for (int i = 0; i < N; i++) {
      // 取出这个字符
      final char c = s.charAt(i);

      leftBraceNumber += c == '(' ? 1 : 0;
      rightBraceNumber += c == ')' ? 1 : 0;

      // 我们总是想让括号的数目 >= 右括号的数目

      // 如果不成立了！
      // 我们去掉左边的任何一部分，都不会合得条件再次成立
      // 所以直接需要让两个计数器归0
      if (rightBraceNumber > leftBraceNumber) {
        leftBraceNumber = 0;
        rightBraceNumber = 0;
      }

      // 如果计数相等的时候
      if (leftBraceNumber == rightBraceNumber) {
        maxEquLength1 =
          Math.max(maxEquLength1, leftBraceNumber + rightBraceNumber);
      }
    }

    // 这个时候，maxEquLength1不能处理这种情况：
    // "()(()"
    // 此时maxEquLength1会误认为有2个右括号都可以匹配成功

    // 首先我们想找到一个区间，这个区间必须要保证
    // 1. 最长
    // 2. 在任何时间，左括号数量 <= 右括号数量

    int maxEquLength2 = 0;

    leftBraceNumber = 0;
    rightBraceNumber = 0;

    for (int i = N - 1; i >= 0; i--) {
      // 取出字符
      final char c = s.charAt(i);

      leftBraceNumber += c == '(' ? 1 : 0;
      rightBraceNumber += c == ')' ? 1 : 0;

      if (leftBraceNumber > rightBraceNumber) {
        leftBraceNumber = 0;
        rightBraceNumber = 0;
      }

      if (leftBraceNumber == rightBraceNumber) {
        maxEquLength2 =
          Math.max(maxEquLength2, leftBraceNumber + rightBraceNumber);
      }
    }

    // 最后取两者中最长的

    return Math.max(maxEquLength1, maxEquLength2);
  }
}
// @lc code=end

// "))))())()()(()"

public class Main
{
  public static void main(String[] args)
  {
    Solution s = new Solution();
    System.out.println(s.longestValidParentheses(new String("))))())()()(()")));
  }
}
