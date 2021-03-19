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
 * 给你一个整数数组 nums ，数组中的元素 互不相同
 * 。返回该数组所有可能的子集（幂集）。
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
    void backtrack(vector<int> &A, int start, vector<int> &box,
                   vector<vector<int>> &ans) {
        const int N = A.size();
        ans.push_back(box);

        if (start >= N) {
            return;
        }

        for (int j = start; j < N; j++) {
            box.push_back(A[j]);
            backtrack(A, j + 1, box, ans);
            box.pop_back();
        }
    }

   public:
    vector<vector<int>> subsets(vector<int> &A) {
        vector<int> box;
        vector<vector<int>> ans;
        backtrack(A, 0, box, ans);
        return ans;
    }
};
// @lc code=end
