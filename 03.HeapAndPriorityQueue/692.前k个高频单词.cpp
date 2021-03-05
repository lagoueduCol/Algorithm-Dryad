/*
 * @lc app=leetcode.cn id=692 lang=cpp
 *
 * [692] 前K个高频单词
 *
 * https://leetcode-cn.com/problems/top-k-frequent-words/description/
 *
 * algorithms
 * Medium (52.47%)
 * Likes:    214
 * Dislikes: 0
 * Total Accepted:    24.3K
 * Total Submissions: 46.2K
 * Testcase Example:  '["i", "love", "leetcode", "i", "love", "coding"]\n2'
 *
 * 给一非空的单词列表，返回前 k 个出现次数最多的单词。
 *
 * 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。
 *
 * 示例 1：
 *
 *
 * 输入: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 * 输出: ["i", "love"]
 * 解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
 * ⁠   注意，按字母顺序 "i" 在 "love" 之前。
 *
 *
 *
 *
 * 示例 2：
 *
 *
 * 输入: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is",
 * "is"], k = 4 输出: ["the", "is", "sunny", "day"] 解析: "the", "is", "sunny"
 * 和 "day" 是出现次数最多的四个单词， ⁠   出现次数依次为
 * 4, 3, 2 和 1 次。
 *
 *
 *
 *
 * 注意：
 *
 *
 * 假定 k 总为有效值， 1 ≤ k ≤ 集合元素数。
 * 输入的单词均由小写字母组成。
 *
 *
 *
 *
 * 扩展练习：
 *
 *
 * 尝试以 O(n log k) 时间复杂度和 O(n) 空间复杂度解决。
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
    vector<string> topKFrequent(vector<string> &A, int k) {
        unordered_map<string, int> H;
        for (auto x : A) {
            H[x]++;
        }

        auto cmp = [&](const pair<string, int> &a, const pair<string, int> &b) {
            if (a.second != b.second) {
                return b.second < a.second;
            }
            // 如果出现次数相同
            // 那么字典序小的要排在前面
            // 按理说应该就是 b.first < a.first;
            // 但是，由于后面还有reverse(ans.begin(), ans.end());
            // 所以这里需要用a.first < b.first;
            return a.first < b.first;
        };

        // 要出现次数高频的k个，那么要用小堆
        priority_queue<pair<string, int>, vector<pair<string, int>>,
                       decltype(cmp)>
            Q(cmp);

        for (auto &p : H) {
            Q.push({p.first, p.second});
            while (Q.size() > k) {
                Q.pop();
            }
        }

        vector<string> ans;
        while (!Q.empty()) {
            ans.push_back(Q.top().first);
            Q.pop();
        }

        reverse(ans.begin(), ans.end());

        return ans;
    }
};
// @lc code=end
