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
        const int N = nums.size();
        sort(nums.begin(), nums.end());

        vector<vector<int>> cur;
        cur.emplace_back();

        int preBeforeUpdateSize = 0;

        for (int i = 0; i < N; i++) {
            const int curSize = cur.size();
            const int value = nums[i];

            int startPos = 0;

            if (i > 0 && value == nums[i-1]) {
                // 如果与前面的值相等
                // 那么在更新的时候，需要从上次更新前的
                // 大小那里开始
                startPos = preBeforeUpdateSize;
            }

            for (int j = startPos; j < curSize; j++) {
                cur.emplace_back(cur[j]);
                cur.back().push_back(value);
            }

            preBeforeUpdateSize = curSize;
        }

        return cur;
    }
};
// @lc code=end

