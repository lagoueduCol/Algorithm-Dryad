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
    vector<int> buildNext(string &sub) {
        const int N = sub.length();
        vector<int> next(N+1, 0);

        int i = 0, j = -1;
        next[0] = -1;

        while (i < N) {
            if (-1 == j || sub[i] == sub[j]) {
                i++;
                j++;

                // sub[j]表示回退之后比较的那个字符
                if (i < N && j < N && sub[i] == sub[j]) {
                    next[i] = next[j];
                } else {
                    next[i] = j;
                }
            } else {
                assert(0 <= j && j <= N);
                j = next[j];
            }
        }

        return next;
    }
public:
    int strStr(string main, string sub) {
        if (sub.empty()) return 0;

        auto next = buildNext(sub);

        const int alen = main.length();
        const int blen = sub.length();
        int i = 0;
        int j = 0;

        while(i < alen && j < blen) {
            // 如果比较成功
            if (-1 == j || main[i] == sub[j]) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }

        return j == blen ? i - blen : -1;
    }
};
// @lc code=end

int main(void) {
    string main = "mississippi";
    string sub = "issipi";
    Solution s;
    std::cout << s.strStr(main, sub) << std::endl;
    return 0;
}