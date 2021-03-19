/*
 * @lc app=leetcode.cn id=77 lang=cpp
 *
 * [77] 组合
 *
 * https://leetcode-cn.com/problems/combinations/description/
 *
 * algorithms
 * Medium (76.55%)
 * Likes:    526
 * Dislikes: 0
 * Total Accepted:    143.1K
 * Total Submissions: 186.8K
 * Testcase Example:  '4\n2'
 *
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 *
 * 示例:
 *
 * 输入: n = 4, k = 2
 * 输出:
 * [
 * ⁠ [2,4],
 * ⁠ [3,4],
 * ⁠ [2,3],
 * ⁠ [1,2],
 * ⁠ [1,3],
 * ⁠ [1,4],
 * ]
 *
 */

// @lc code=start
class Solution {
    void backtrack(int begin, int end, int k, vector<int> &box,
                   vector<vector<int>> &ans) {
        if (box.size() == k) {
            ans.push_back(box);
        }

        if (box.size() >= k || begin >= end) {
            return;
        }

        for (int i = begin; i < end; i++) {
            box.push_back(i);
            backtrack(i + 1, end, k, box, ans);
            box.pop_back();
        }
    }

   public:
    vector<vector<int>> combine(int n, int k) {
        vector<int> box;
        vector<vector<int>> ans;
        backtrack(1, n + 1, k, box, ans);
        return ans;
    }
};
// @lc code=end
