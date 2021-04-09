/*
 * @lc app=leetcode.cn id=494 lang=cpp
 *
 * [494] 目标和
 *
 * https://leetcode-cn.com/problems/target-sum/description/
 *
 * algorithms
 * Medium (45.05%)
 * Likes:    628
 * Dislikes: 0
 * Total Accepted:    75.7K
 * Total Submissions: 167.9K
 * Testcase Example:  '[1,1,1,1,1]\n3'
 *
 * 给定一个非负整数数组，a1, a2, ..., an,
 * 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或
 * -中选择一个符号添加在前面。
 *
 * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 *
 *
 *
 * 示例：
 *
 * 输入：nums: [1, 1, 1, 1, 1], S: 3
 * 输出：5
 * 解释：
 *
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 *
 * 一共有5种方法让最终目标和为3。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 数组非空，且长度不会超过 20 。
 * 初始的数组的和不会超过 1000 。
 * 保证返回的最终结果能被 32 位整数存下。
 *
 *
 */

// @lc code=start
class Solution
{
public:
  int findTargetSumWays(vector<int>& A, int S)
  {
    const int N = A.size();
    const int total = accumulate(A.begin(), A.end(), 0);
    S = abs(S);

    // 如果给定的S值太大
    // 不用计算了
    if (S > total) {
      return 0;
    }

    // 如果我们要取出数最后加减之后为S
    // 我们让和为X的数前面都是+号
    // 和为Y的数，前面都是减号
    //
    // X - abs(Y) = S
    //
    // X + abs(Y) = total
    //
    // 那么最终可以得到
    //
    // X = (S + total) / 2
    //
    // 如果是一个奇数
    // 那么也是没有解的
    if ((S + total) & 0x01) {
      return 0;
    }

    // 那么现在问题就变成了选一些数出来，让它们的和等于X
    int X = (S + total) >> 1;
    // 由于是添加正负号，S是正数还是负数不重要

    // 零的个数
    // 在构成S的基础ans上，如果有zero个0
    // 那么最终解就是ans * (1<<zero)个
    int zero = 0;
    for (auto x : A) {
      zero += x == 0;
    }

    uint32_t dp[X + 1];
    memset(dp, 0, sizeof(dp));

    // 去除数组中的0之后，能够构成0的方法
    // 一开始肯定是只有一种的，也就是什么元素都不选
    // 注意，这里我们是去掉了所有0元素
    dp[0] = 1;

    for (auto v : A) {
      // 这里我们只处理非0的情况
      if (v) {
        // 由于每个数只能用一次，所以我们这里的更新方向要是从大到小
        for (int sum = X; sum >= v; sum--) {
          const int oldSum = sum - v;
          const int newSum = sum;

          dp[newSum] += dp[oldSum];
        }
      }
    }

    return dp[X] * (1 << zero);
  }
};
// @lc code=end
