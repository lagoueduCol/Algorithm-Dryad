/*
 * @lc app=leetcode.cn id=78 lang=cpp
 *
 * [78] 子集
 *
 * https://leetcode-cn.com/problems/subsets/description/
 *
 * algorithms
 * Medium (79.61%)
 * Likes:    1061
 * Dislikes: 0
 * Total Accepted:    212.1K
 * Total Submissions: 266.5K
 * Testcase Example:  '[1,2,3]'
 *
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [0]
 * 输出：[[],[0]]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * -10 
 * nums 中的所有元素 互不相同
 * 
 * 
 */

// @lc code=start
class Solution {
    vector<int> merge(vector<int> &x, vector<int> &y) {
        vector<int> ans(x);
        for (auto &v: y) {
            ans.push_back(v);
        }
        return ans;
    }

    vector<vector<int>> dq(vector<int> &nums, int b, int e) {
        vector<vector<int>> ans;
        ans.emplace_back();

        // 如果范围为空
        if (b >= e) {
            return ans;
        }

        // 如果只有一个元素
        if (b + 1 == e) {
            ans.emplace_back();
            ans.back().push_back(nums[b]);
            return ans;
        }

        // 从中间分为两半
        const int mid = b + ((e-b)>>1);

        auto l = dq(nums, b, mid);
        auto r = dq(nums, mid, e);

        for (auto &x: l) {
            for (auto &y: r) {
                if (x.empty() && y.empty()) {
                    continue;
                }
                ans.emplace_back(merge(x, y));
            }
        }

        return ans;
    }

public:
    vector<vector<int>> subsets(vector<int>& nums) {
        const int N = nums.size();
        return dq(nums, 0, N);
    }
};
// @lc code=end

