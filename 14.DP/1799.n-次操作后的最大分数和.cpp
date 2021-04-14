/*
 * @lc app=leetcode.cn id=1799 lang=cpp
 *
 * [1799] N 次操作后的最大分数和
 *
 * https://leetcode-cn.com/problems/maximize-score-after-n-operations/description/
 *
 * algorithms
 * Hard (49.83%)
 * Likes:    15
 * Dislikes: 0
 * Total Accepted:    1.6K
 * Total Submissions: 3.3K
 * Testcase Example:  '[1,2]'
 *
 * 给你 nums ，它是一个大小为 2 * n 的正整数数组。你必须对这个数组执行
 * n 次操作。
 *
 * 在第 i 次操作时（操作编号从 1 开始），你需要：
 *
 *
 * 选择两个元素 x 和 y 。
 * 获得分数 i * gcd(x, y) 。
 * 将 x 和 y 从 nums 中删除。
 *
 *
 * 请你返回 n 次操作后你能获得的分数和最大为多少。
 *
 * 函数 gcd(x, y) 是 x 和 y 的最大公约数。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2]
 * 输出：1
 * 解释：最优操作是：
 * (1 * gcd(1, 2)) = 1
 *
 *
 * 示例 2：
 *
 * 输入：nums = [3,4,6,8]
 * 输出：11
 * 解释：最优操作是：
 * (1 * gcd(3, 6)) + (2 * gcd(4, 8)) = 3 + 8 = 11
 *
 *
 * 示例 3：
 *
 * 输入：nums = [1,2,3,4,5,6]
 * 输出：14
 * 解释：最优操作是：
 * (1 * gcd(1, 5)) + (2 * gcd(2, 4)) + (3 * gcd(3, 6)) = 1 + 4 + 9 = 14
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= n <= 7
 * nums.length == 2 * n
 * 1 <= nums[i] <= 10^6
 *
 *
 */

#include <assert.h>
#include <limits.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include <algorithm>
#include <iostream>
#include <numeric>
#include <queue>
#include <string>
#include <unordered_map>
#include <unordered_set>
#include <vector>

using namespace std;

// @lc code=start
class Solution {
 public:
  int maxScore(vector<int> &A) {
    const int N = A.size();
    const int total_steps = N >> 1;

    // 一共有N个数
    // 每个数可以表示存在，或者不存在
    // 那么只有两种状态0/1
    // 因此，我们可以用二进制位来进行表示
    // 由于题目中已经说明n <= 7
    // 所以，最多只需要14 bits
    // 那么用一个int位，我们就可以表示了
    // 所以这里我们申请dp[array_size]
    const int array_size = 1 << N;

    int dp[array_size];
    memset(dp, 0, sizeof(dp));

    // dp[0] = 0
    // 表示当没有任何数的时候，那么收益肯定为0
    // 已经设置过了，这里不用再管

    // 那么接下来就是从余下两个数的时候开始
    // 往前推导
    for (uint32_t i = 3; i < array_size; i++) {
      // 这里利用GCC内置的计算整的二进制中1的个数的函数
      auto cnt = __builtin_popcount(i);

      // 由于每次需要去掉两个数，当i里面的二进制1的数目为
      // 奇数的时候，没有必要计算!
      if (cnt & 0x01) {
        continue;
      }

      // 当前步数
      // 即: 当前我是第几步
      const int cur_step = ((N >> 1) - (cnt >> 1) + 1);

      // 那么我们需要从i里面选两个数
      for (int j = 0; j < N; j++) {
        // 如果i中没有A[j]这个数
        if ((i & (1 << j)) == 0) continue;
        for (int k = j + 1; k < N; k++) {
          // 如果i中没有A[k]这个数
          if ((i & (1 << k)) == 0) continue;

          // 这里我们先择A[j], A[k]
          const int g = std::gcd(A[j], A[k]);
          // 得分
          const int score = ((N >> 1) - cur_step + 1) * g;

          // 得到去掉i,j之后的状态
          const int mask = (1 << j) | (1 << k);
          const int pre_status = i & ~mask;

          const int total = dp[pre_status] + score;

          // 选择最大值dp[i]
          dp[i] = max(dp[i], total);
        }
      }
    }

    return dp[array_size - 1];
  }
};
// @lc code=end

int main(void) {
  Solution s;
  vector<int> A{3, 4, 6, 8};
  std::cout << s.maxScore(A) << std::endl;
  return 0;
}