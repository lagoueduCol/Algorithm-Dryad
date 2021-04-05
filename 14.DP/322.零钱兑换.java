/*
 * @lc app=leetcode.cn id=322 lang=java
 *
 * [322] 零钱兑换
 *
 * https://leetcode-cn.com/problems/coin-change/description/
 *
 * algorithms
 * Medium (42.96%)
 * Likes:    1160
 * Dislikes: 0
 * Total Accepted:    201.9K
 * Total Submissions: 469.8K
 * Testcase Example:  '[1,2,5]\n11'
 *
 * 给定不同面额的硬币 coins 和一个总金额
 * amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 * 你可以认为每种硬币的数量是无限的。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 *
 * 示例 2：
 *
 *
 * 输入：coins = [2], amount = 3
 * 输出：-1
 *
 * 示例 3：
 *
 *
 * 输入：coins = [1], amount = 0
 * 输出：0
 *
 *
 * 示例 4：
 *
 *
 * 输入：coins = [1], amount = 1
 * 输出：1
 *
 *
 * 示例 5：
 *
 *
 * 输入：coins = [1], amount = 2
 * 输出：2
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1
 * 1
 * 0
 *
 *
 */

// @lc code=start
class Solution
{
  public int coinChange(int[] coins, int amount)
  {
    // 没有解的时候，设置一个较大的值
    final int INF = Integer.MAX_VALUE / 4;

    int[] dp = new int[amount + 1];
    // 一开始给所有的数设置为不可解。
    for (int i = 1; i <= amount; i++) {
      dp[i] = INF;
    }

    // DP的初始条件
    dp[0] = 0;

    for (int i = 0; i < amount; i++) {
      for (int y : coins) {
        // 注意边界的处理，不要越界
        if (y <= amount && i + y < amount + 1 && i + y >= 0) {
          // 正向推导时的递推公式!
          dp[i + y] = Math.min(dp[i + y], dp[i] + 1);
        }
      }
    }

    return dp[amount] >= INF ? -1 : dp[amount];
  }
}
// @lc code=end
