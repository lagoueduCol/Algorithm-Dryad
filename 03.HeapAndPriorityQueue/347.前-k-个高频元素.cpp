/*
 * @lc app=leetcode.cn id=347 lang=cpp
 *
 * [347] 前 K 个高频元素
 *
 * https://leetcode-cn.com/problems/top-k-frequent-elements/description/
 *
 * algorithms
 * Medium (61.87%)
 * Likes:    653
 * Dislikes: 0
 * Total Accepted:    136.4K
 * Total Submissions: 220.1K
 * Testcase Example:  '[1,1,1,2,2,3]\n2'
 *
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 *
 *
 * 示例 2:
 *
 * 输入: nums = [1], k = 1
 * 输出: [1]
 *
 *
 *
 * 提示：
 *
 *
 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。
 * 你可以按任意顺序返回答案。
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
    vector<int> topKFrequent(vector<int>& A, int k) {
        unordered_map<int, int> H;
        for (auto x : A) {
            H[x]++;
        }

        auto cmp = [&](const pair<int, int>& a, const pair<int, int>& b) {
            if (a.second != b.second) {
                return b.second < a.second;
            }
            return b.first < a.first;
        };

        // 要出现次数高频的k个，那么要用小堆
        priority_queue<pair<int, int>, vector<pair<int, int>>, decltype(cmp)> Q(
            cmp);

        for (auto& p : H) {
            Q.push({p.first, p.second});
            while (Q.size() > k) {
                Q.pop();
            }
        }

        vector<int> ans;
        while (!Q.empty()) {
            ans.push_back(Q.top().first);
            Q.pop();
        }

        sort(ans.begin(), ans.end());

        return ans;
    }
};
// @lc code=end
