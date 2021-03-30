/*
 * @lc app=leetcode.cn id=30 lang=cpp
 *
 * [30] 串联所有单词的子串
 *
 * https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words/description/
 *
 * algorithms
 * Hard (33.80%)
 * Likes:    417
 * Dislikes: 0
 * Total Accepted:    56.2K
 * Total Submissions: 166.2K
 * Testcase Example:  '"barfoothefoobarman"\n["foo","bar"]'
 *
 * 给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words
 * 中所有单词串联形成的子串的起始位置。
 *
 * 注意子串要与 words
 * 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。
 *
 *
 *
 * 示例 1：
 *
 * 输入：
 * ⁠ s = "barfoothefoobarman",
 * ⁠ words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：
 * 从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 *
 *
 * 示例 2：
 *
 * 输入：
 * ⁠ s = "wordgoodgoodgoodbestword",
 * ⁠ words = ["word","good","best","word"]
 * 输出：[]
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

class Counter {
  unordered_map<string, int> H;

public:
  int get(string &w) const {
    auto iter = H.find(w);
    if (iter == H.end()) {
      return 0;
    }
    return iter->second;
  }
  void add(string w, int v) {
    auto iter = H.find(w);
    if (iter == H.end()) {
      H.emplace(w, v);
    } else {
      iter->second += v;
      if (iter->second == 0) {
        H.erase(iter);
      }
    }
  }
  int size() const { return H.size(); }
};

class Solution {
public:
  vector<int> findSubstring(string s, vector<string> &D) {
    if (s.empty() || D.empty()) {
      return {};
    }

    const int N = s.length();
    const int L = D[0].length();

    vector<int> ans;
    Counter H;
    for (auto &w : D) {
      H.add(w, 1);
    }

    for (int start = 0; start < L; start++) {
      Counter R;
      int equal = 0;
      size_t item = 0;
      int left = start - L;
      for (int i = start; i < N; i += L) {
        auto w = s.substr(i, L);
        item++;

        R.add(w, 1);
        equal += R.get(w) == H.get(w);

        if (item < D.size()) {
          continue;
        }

        if (equal == H.size()) {
          ans.push_back(left + L);
        }

        left += L;
        w = s.substr(left, L);

        if (R.get(w) == H.get(w)) {
          equal--;
        }
        R.add(w, -1);
      }
    }

    return ans;
  }
};
// @lc code=end

int main(void) {
  string s = "barfoothefoobarman";
  vector<string> D{"foo", "bar"};
  Solution u;
  auto vs = u.findSubstring(s, D);
  for (auto x : vs) {
    std::cout << x << std::endl;
  }
  return 0;
}