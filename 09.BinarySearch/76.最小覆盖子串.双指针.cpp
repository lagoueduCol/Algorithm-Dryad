/*
 * @lc app=leetcode.cn id=76 lang=cpp
 *
 * [76] 最小覆盖子串
 */

#include <stdlib.h>
#include <string.h>

#include <string>

using namespace std;

// @lc code=start
class Solution {
   public:
    string minWindow(string s, string t) {
        int tcnt[256];

        memset(tcnt, 0, sizeof(tcnt));

        for (auto &c : t) { tcnt[c]++; }

        int rec[256];
        memset(rec, 0, sizeof(rec));

        auto cmp = [&]() {
            for (int i = 0; i < 256; i++) {
                if (rec[i] < tcnt[i]) { return false; }
            }
            return true;
        };

        int hit = 0;
        int start = 0, end = INT_MAX;
        int front = -1;
        for (int i = 0; i < s.length(); i++) {
            if (tcnt[s[i]]) {
                rec[s[i]]++;
                //是一次有效的命中
                if (rec[s[i]] <= tcnt[s[i]]) { hit++; }
            }

            while (hit == t.length()) {
                if (i - front < end - start) {
                    start = front;
                    end = i;
                }
                if (tcnt[s[1 + front]]) {
                    rec[s[1 + front]]--;
                    // 是一次有效的撤销
                    if (rec[s[1 + front]] < tcnt[s[1 + front]]) { --hit; }
                }
                ++front;
            }
        }

        if (end - start != INT_MAX) { return s.substr(start + 1, end - start); }
        return string();
    }
};
// @lc code=end
