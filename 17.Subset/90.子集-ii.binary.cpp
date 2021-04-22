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

// @lc code=start
class Solution {
public:
    vector<vector<int>> subsetsWithDup(vector<int>& nums) {
        const int N = nums.size();
        sort(nums.begin(), nums.end());

        vector<vector<int>> ans;

        for (int i = 0; i < (1<<N); i++) {
            vector<int> newSubset;
            bool newSubsetIsValid = true;

            // 在生成newSubset的过程
            for (int j = 0; j < N; j++) {
                // 如果nums[j]被选中
                int curBit = (i & (1<<j)) ? 1 : 0;
                if (j > 0 && nums[j] == nums[j-1]) {
                    int preBit = (i & (1<<(j-1))) ? 1 : 0;
                    if (curBit == 1 && preBit == 0) {
                        newSubsetIsValid = false;
                        break;
                    }
                }
                if (curBit) {
                    newSubset.push_back(nums[j]);
                }
            }

            if (newSubsetIsValid) {
                ans.push_back(newSubset);
            }
        }

        return ans;
    }
};
// @lc code=end

