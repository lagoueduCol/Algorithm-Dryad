/*
 * @lc app=leetcode.cn id=131 lang=cpp
 *
 * [131] 分割回文串
 *
 * https://leetcode-cn.com/problems/palindrome-partitioning/description/
 *
 * algorithms
 * Medium (72.74%)
 * Likes:    663
 * Dislikes: 0
 * Total Accepted:    96.4K
 * Total Submissions: 132.5K
 * Testcase Example:  '"aab"'
 *
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s
 * 所有可能的分割方案。
 *
 * 回文串 是正着读和反着读都一样的字符串。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 *
 *
 * 示例 2：
 *
 *
 * 输入：s = "a"
 * 输出：[["a"]]
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1
 * s 仅由小写英文字母组成
 *
 *
 */

#include <string>
#include <unordered_map>
#include <vector>

using namespace std;

// @lc code=start

class Solution {
  void backtrace(string &s, vector<vector<bool>> &dp, int start,
                 vector<string> &box, vector<vector<string>> &ans) {
    const int N = s.length();
    // 当前人的选择空间为[start, N)
    // 当start == N的时候，已经没有任何选择范围了。
    if (start == N) {
      ans.push_back(box);
      return;
    }

    // 第i个人切子串的范围
    for (int cut = start; cut < s.length(); cut++) {
      if (dp[start][cut]) {
        box.push_back(s.substr(start, cut - start + 1));
        // 下一个人可以切分的字符串[cut + 1, N)
        backtrace(s, dp, cut + 1, box, ans);
        box.pop_back();
      }
    }
  }

  void build(string &s, vector<vector<bool>> &dp) {
    const int N = s.length();
    dp.resize(s.length(), vector<bool>(N));
    // 初始化dp
    // 当子串长度为1
    for (int i = 0; i < N; i++) dp[i][i] = true;
    // 当子串长度为2
    for (int i = 0; i + 1 < N; i++) dp[i][i + 1] = s[i] == s[i + 1];
    // 其他长度的子串
    for (int len = 2; len < N; len++)
      for (int i = 0; i < N - len; i++)
        dp[i][i + len] = s[i] == s[i + len] && dp[i + 1][i + len - 1];
  }

 public:
  vector<vector<string>> partition(string &s) {
    const int N = s.length();
    vector<vector<bool>> dp;
    build(s, dp);

    vector<string> box;
    vector<vector<string>> ans;

    backtrace(s, dp, 0, box, ans);
    return ans;
  }
};

// @lc code=end
