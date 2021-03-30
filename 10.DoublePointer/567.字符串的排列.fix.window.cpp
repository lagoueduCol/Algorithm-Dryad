/*
 * @lc app=leetcode.cn id=567 lang=cpp
 *
 * [567] 字符串的排列
 *
 * https://leetcode-cn.com/problems/permutation-in-string/description/
 *
 * algorithms
 * Medium (41.56%)
 * Likes:    305
 * Dislikes: 0
 * Total Accepted:    73.9K
 * Total Submissions: 177.3K
 * Testcase Example:  '"ab"\n"eidbaooo"'
 *
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 *
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 *
 *
 * 示例 2：
 *
 *
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 *
 *
 *
 *
 * 提示：
 *
 *
 * 输入的字符串只包含小写字母
 * 两个字符串的长度都在 [1, 10,000] 之间
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
  bool checkInclusion(string &A, string &B) {
    if (A.empty()) return true;
    if (B.empty()) return false;

    const int N = B.length();
    int left = -1;

    int H[256] = {};
    for (auto x : A) H[x]++;
    int hsize = 0;
    for (int i = 0; i < 256; i++) hsize += !!H[i];

    int R[256] = {};
    int cnt = 0;

    for (int i = 0; i < N; i++) {
      char c = B[i];
      R[c]++;
      if (R[c] == H[c] && H[c] > 0) {
        cnt++;
      }

      if (i + 1 < A.length()) {
        continue;
      }

      if (cnt == hsize) {
        return true;
      }

      left++;
      c = B[left];
      if (R[c] == H[c] && H[c] > 0) {
        cnt--;
      }
      R[c]--;
    }

    return false;
  }
};

// @lc code=end

int main(void) {
  Solution s;
  string A = "ab";
  string B = "eidbaooo";
  std::cout << s.checkInclusion(A, B) << std::endl;
  return 0;
}
