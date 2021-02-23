/*
 * @lc app=leetcode.cn id=69 lang=cpp
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
public:
  int mySqrt(int x) {
    int64_t l = 0, r = x;

    auto getC = [](int64_t x, int64_t m) {
      if (m * m < x) {
        return -1;
      } else if (m * m == x) {
        return 0;
      }
      return 1;
    };

    while (l < r) {
      const int64_t m = l + ((r - l) >> 1);
      const int64_t mov = getC(x, m);
      if (mov < 0) {
        l = m + 1;
      } else {
        r = m;
      }
    }

    // 这里如能够找到开根号之后的整数，那么这时直接返回l
    if (l * l == x) {
      return l;
    }
    // 这里如果找不到，那么由于l * l > x
    // 此时需要后退一步。
    // 因为题意要求只能取去掉小数之后的整数部分。
    return l - 1;
  }
};
// @lc code=end
