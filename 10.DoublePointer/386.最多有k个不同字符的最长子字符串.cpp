/*
给定字符串S，找到最多有k个不同字符的最长子串T。

样例
样例 1:

输入: S = "eceba" 并且 k = 3
输出: 4
解释: T = "eceb"
样例 2:

输入: S = "WORLD" 并且 k = 4
输出: 4
解释: T = "WORL" 或 "ORLD"
挑战
O(n) 时间复杂度

https://www.lintcode.com/problem/386/
*/

class Solution {
 public:
  /**
   * @param s: A string
   * @param k: An integer
   * @return: An integer
   */
  int lengthOfLongestSubstringKDistinct(string &s, int k) {
    // write your code here
    const int N = s.length();
    int left = -1;
    int ans = 0;

    int H[256] = {};
    int cnt = 0;

    for (int i = 0; i < N; i++) {
      const char c = s[i];

      cnt += H[c] == 0;
      H[c]++;

      while (left < i && cnt > k) {
        const char x = s[++left];
        if (H[x] == 1) {
          cnt--;
        }
        H[x]--;
      }

      ans = max(ans, i - left);
    }
    return ans;
  }
};