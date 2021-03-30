/*
 * @lc app=leetcode.cn id=76 lang=cpp
 *
 * [76] 最小覆盖子串
 *
 * https://leetcode-cn.com/problems/minimum-window-substring/description/
 *
 * algorithms
 * Hard (40.63%)
 * Likes:    949
 * Dislikes: 0
 * Total Accepted:    107.9K
 * Total Submissions: 265.4K
 * Testcase Example:  '"ADOBECODEBANC"\n"ABC"'
 *
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s
 * 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 *
 * 注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 *
 *
 * 示例 2：
 *
 *
 * 输入：s = "a", t = "a"
 * 输出："a"
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1
 * s 和 t 由英文字母组成
 *
 *
 *
 * 进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？
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
  string minWindow(string s, string t) {
    int H[256];
    memset(H, 0, sizeof(H));
    for (auto c : t) H[c]++;

    int hSize = 0;
    for (int i = 0; i < 256; i++) hSize += !!H[i];

    const int N = s.length();

    int R[256];
    memset(R, 0, sizeof(R));

    int left = -1;
    int equal = 0;

    int start = 1;
    int end = 0;
    string ans;

    for (int i = 0; i < N; i++) {
      char c = s[i];
      R[c]++;
      if (R[c] == H[c] && H[c]) {
        equal++;
      }

      while (left < i && equal >= hSize) {
        if (start > end || end - start > i - left) {
          start = left;
          end = i;
        }
        c = s[++left];
        if (R[c] == H[c] && H[c] > 0) {
          equal--;
        }
        R[c]--;
      }
    }

    if (start >= end) {
      return "";
    }
    return s.substr(start + 1, end - start);
  }
};
// @lc code=end

int main(void) { return 0; }