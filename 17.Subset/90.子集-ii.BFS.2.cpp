/*
 * @lc app=leetcode.cn id=90 lang=cpp
 *
 * [90] 子集 II
 *
 * https://leetcode-cn.com/problems/subsets-ii/description/
 *
 * algorithms
 * Medium (61.92%)
 * Likes:    416
 * Dislikes: 0
 * Total Accepted:    71.4K
 * Total Submissions: 115.3K
 * Testcase Example:  '[1,2,2]'
 *
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 
 * 说明：解集不能包含重复的子集。
 * 
 * 示例:
 * 
 * 输入: [1,2,2]
 * 输出:
 * [
 * ⁠ [2],
 * ⁠ [1],
 * ⁠ [1,2,2],
 * ⁠ [2,2],
 * ⁠ [1,2],
 * ⁠ []
 * ]
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
    vector<vector<int>> subsetsWithDup(vector<int>& nums) {
        unordered_map<int,int> cnt;
        for (auto x: nums) {
            cnt[x]++;
        }

        vector<vector<int>> cur;
        cur.emplace_back();

        for (auto &p: cnt) {
            const int value = p.first;
            const int number = p.second;

            // 我们记录当前cur的Size
            // 然后把新生成的结果放到cur的后面
            const int curSize = cur.size();

            // 利用<value, number>去更新cur
            for (int i = 0; i < curSize; i++) {
                auto &subset = cur[i];
                auto newSubset = subset;
                for (int i = 0; i < number; i++) {
                    newSubset.push_back(value);
                    cur.push_back(newSubset);
                }
            }
        }

        return cur;
    }
};
// @lc code=end

