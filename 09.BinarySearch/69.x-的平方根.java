/*
 * @lc app=leetcode.cn id=69 lang=java
 *
 * [69] x 的平方根
 *
 * https://leetcode-cn.com/problems/sqrtx/description/
 *
 * algorithms
 * Easy (39.16%)
 * Likes:    585
 * Dislikes: 0
 * Total Accepted:    253.6K
 * Total Submissions: 647.6K
 * Testcase Example:  '4'
 *
 * 实现 int sqrt(int x) 函数。
 *
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 *
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 *
 * 示例 1:
 *
 * 输入: 4
 * 输出: 2
 *
 *
 * 示例 2:
 *
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 * 由于返回类型是整数，小数部分将被舍去。
 *
 *
 */

// @lc code=start
class Solution {
  private int getC(long x, long m) {
    if (m * m < x) {
      return -1;
    } else if (m * m == x) {
      return 0;
    }
    return 1;
  }

  public int mySqrt(int x) {
    int l = 0, r = x;
    while (l < r) {
      final int m = l + ((r - l) >> 1);
      final int mov = getC(x, m);
      if (mov < 0) {
        l = m + 1;
      } else {
        r = m;
      }
    }
    if (l * l == x) {
      return l;
    }
    // 注意，当在映射之后的数组中不存在0的时候
    // l的下标是指向映射之后的C[x] = 1第一个位置
    // 此时 l * l > m
    // 所以这里需要返回l - 1
    // 因为按照题意，需要返回整数部分。
    return l - 1;
  }
}
// @lc code=end
