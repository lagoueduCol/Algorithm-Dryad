/*
 * @lc app=leetcode.cn id=322 lang=cpp
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

constexpr int INF = INT_MAX >> 4;

class Solution {
  unordered_set<int> H;
  unordered_map<int, int> ret;
  int dfs(vector<int>& A, int T) {
    if (H.empty()) {
      sort(A.begin(), A.end());
      reverse(A.begin(), A.end());
      for (auto x : A) H.insert(x);
    }
    // 返回不可能
    if (T < 0) {
      return INT_MAX >> 4;
    }

    if (T == 0) {
      return 0;
    }

    if (H.count(T)) {
      return 1;
    }

    auto iter = ret.find(T);
    if (iter != ret.end()) {
      return iter->second;
    }

    // 看看是否可以由两个数组构成
    for (auto x : A)
      if (H.count(T - x)) return 2;

    int ans = INT_MAX;
    for (auto x : A) {
      ans = min(ans, dfs(A, T - x) + 1);
    }
    return ret[T] = ans;
  }

 public:
  int coinChange(vector<int>& A, int T) {
    auto ans = dfs(A, T);
    return ans >= INF ? -1 : ans;
  }
};

// @lc code=end
