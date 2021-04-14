/*
 * @lc app=leetcode.cn id=28 lang=cpp
 *
 * [28] 实现 strStr()
 *
 * https://leetcode-cn.com/problems/implement-strstr/description/
 *
 * algorithms
 * Easy (39.71%)
 * Likes:    773
 * Dislikes: 0
 * Total Accepted:    326.5K
 * Total Submissions: 822.2K
 * Testcase Example:  '"hello"\n"ll"'
 *
 * 实现 strStr() 函数。
 * 
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置
 * (从0开始)。如果不存在，则返回  -1。
 * 
 * 示例 1:
 * 
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 * 
 * 
 * 示例 2:
 * 
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 * 
 * 
 * 说明:
 * 
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * 
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
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
    vector<int> buildPMT(string &sub) {
        const int N = sub.length();
        vector<int> pmt;
        vector<int> next(N+1, 0);

        int i = 0, j = -1;
        next[0] = -1;

        while (i < N) {
            if (-1 == j || sub[i] == sub[j]) {
                i++;
                j++;
                assert(0 <= i && i <= N);
                next[i] = j;
            } else {
                assert(0 <= j && j <= N);
                j = next[j];
            }
        }

        for (int i = 1; i <= N; i++) {
            pmt.push_back(next[i]);
        }
        return pmt;
    }
public:
    int strStr(string main, string sub) {
        if (sub.empty()) return 0;

        auto PMT = buildPMT(sub);

        const int alen = main.length();
        const int blen = sub.length();
        int i = 0;
        int j = 0;

        while(i < alen && j < blen) {
            // 如果比较成功
            if (main[i] == sub[j]) {
                i++;
                j++;
            } else {
                // 如果比较失败

                if (j == 0) {
                    // Case 1.
                    // 已经退到底了，PMT[]
                    // 表中没有记录空串的的值
                    // 这个时候需要移动i
                    i++;
                } else {
                    // Case 2.
                    j = PMT[j-1];
                }
            }
        }

        return j == blen ? i - blen : -1;
    }
};
// @lc code=end

int main(void) {
    string main = "hello";
    string sub = "ll";
    Solution s;
    std::cout << s.strStr(main, sub) << std::endl;
    return 0;
}