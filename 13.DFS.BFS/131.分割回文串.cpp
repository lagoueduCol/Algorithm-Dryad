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
  unordered_map<int, vector<vector<string>>> H;

  vector<vector<bool>> ok;
  // 将s字符串的[start, N)切分成为回文字符串
  // 返回所有可能的切分方法

  vector<vector<string>> dfs(string s, int start) {
    const int N = s.length();
    // 如果已经是空字符串了，那么肯定是没有解了
    if (start >= N) {
      return {};
    }
    // 如果只有一个字符，那么解集里面肯定只有1个
    if (start + 1 == N) {
      return {{s.substr(start)}};
    }

    auto iter = H.find(start);
    if (iter != H.end()) {
      return iter->second;
    }

    vector<vector<string>> ans;
    // 看一下后面的切分位置
    for (int cut = start; cut < N; cut++) {
      // 切分成为两部分[start, cut] [cut + 1, N)
      if (ok[start][cut]) {
        string word = s.substr(start, cut - start + 1);
        auto next = dfs(s, cut + 1);
        for (auto &n : next) {
          n.insert(n.begin(), word);
          ans.push_back(n);
        }

        if (cut == N - 1) {
          ans.emplace_back();
          ans.back().push_back(word);
        }
      }
    }

    return H[start] = ans;
  }

 public:
  vector<vector<string>> partition(string s) {
    const int N = s.length();
    ok.resize(N, vector<bool>(N, false));

    auto isok = [&](const int i, const int j) {
      int l = i, r = j;
      while (l < r) {
        if (s[l] != s[r]) return false;
        l++;
        r--;
      }
      return true;
    };

    for (int i = 0; i < N; i++) {
      ok[i][i] = true;
      for (int j = i + 1; j < N; j++) {
        ok[i][j] = isok(i, j);
      }
    }

    return dfs(s, 0);
  }
};
// @lc code=end
