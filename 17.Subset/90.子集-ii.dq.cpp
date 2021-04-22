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

        if (b >= e) {
            return ans;
        }

        if (b + 1 == e) {
            ans.emplace_back();
            ans.back().push_back(nums[b]);
            return ans;
        }

        const int mid = b + ((e-b) >> 1);
        int l = mid - 1;
        while (l >= b && nums[l] == nums[mid]) {
            l--;
        }

        int r = mid + 1;
        while (r < e && nums[r] == nums[mid]) {
            r++;
        }

        // (l, r)这个区间里面的值都是nums[mid]
        if (l == b - 1 && r == e) {
            // 整个区间都是一样的值
            vector<int> tmp;
            for (int j = 0; j < e - b; j++) {
                tmp.push_back(nums[b]);
                ans.push_back(tmp);
            }
            return ans;
        }

        // 否则要把数据按整块的切割到一边
        // 正常情况下，我们都取r;
        int cutPos = r;
        // 但是如果r == e
        if (r == e) {
            cutPos = l + 1;
        }

        auto lans = dq(nums, b, cutPos);
        auto rans = dq(nums, cutPos, e);

        // 需要对这两边的答案进行两两合并
        for (auto &x: lans) {
            for (auto &y: rans) {
                if (x.empty() && y.empty()) {
                    continue;
                }
                ans.push_back(merge(x, y));
            }
        }

        return ans;
    }
public:
    vector<vector<int>> subsetsWithDup(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        return dq(nums, 0, nums.size());
    }
};
// @lc code=end

