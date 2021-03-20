/*
 * @lc app=leetcode.cn id=46 lang=cpp
 *
 * [46] 全排列
 *
 * https://leetcode-cn.com/problems/permutations/description/
 *
 * algorithms
 * Medium (77.67%)
 * Likes:    1222
 * Dislikes: 0
 * Total Accepted:    277.9K
 * Total Submissions: 357.4K
 * Testcase Example:  '[1,2,3]'
 *
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 *
 * 示例:
 *
 * 输入: [1,2,3]
 * 输出:
 * [
 * ⁠ [1,2,3],
 * ⁠ [1,3,2],
 * ⁠ [2,1,3],
 * ⁠ [2,3,1],
 * ⁠ [3,1,2],
 * ⁠ [3,2,1]
 * ]
 *
 */

// @lc code=start
class Solution {
    void backtrack(vector<int> &A, int i, vector<bool> &used, vector<int> &box,
                   vector<vector<int>> &ans) {
        const int N = A.size();
        // 如果状态已经满足要求
        if (box.size() == N) {
            ans.push_back(box);
        }

        // 我们总是从第0个人开始，那么一共有N个元素
        // 那么当到第N个人的时候，已经没有东西可以选了。
        // [N ~ inf) 后面所有的人都没有东西可以选了。
        if (i >= N) {
            return;
        }

        // 第i个人的选择范围就是[0, N)
        // 但是不能选择box中已经用过的元素
        for (int j = 0; j < N; j++) {
            if (!used[j]) {
                box.push_back(A[j]);
                used[j] = true;
                backtrack(A, i + 1, used, box, ans);
                used[j] = false;
                box.pop_back();
            }
        }
    }

   public:
    vector<vector<int>> permute(vector<int> &A) {
        const int N = A.size();
        if (N == 0) {
            return {};
        }

        vector<int> box;
        vector<vector<int>> ans;
        vector<bool> used(N, false);

        backtrack(A, 0, used, box, ans);
        return ans;
    }
};
// @lc code=end
