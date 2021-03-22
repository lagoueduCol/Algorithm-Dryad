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
class Solution
{
  void backtrace(vector<int>& A,
                 int i,     /*第i个人*/
                 int start, /*第i个人的选择范围[start, end]*/
                 int end,
                 vector<int>& box,
                 vector<vector<int>>& ans)
  {
    const int N = A.size();
    ans.push_back(box);

    if (start >= N) {
      return;
    }

    for (int j = start; j < N; j++) {
      if (j > start && A[j] == A[j - 1]) {
        continue;
      }
      box.push_back(A[j]);
      backtrace(A, i + 1, j + 1, end, box, ans);
      box.pop_back();
    }
  }

public:
  vector<vector<int>> subsetsWithDup(vector<int>& A)
  {
    const int N = A.size();
    sort(A.begin(), A.end());
    vector<int> box;
    vector<vector<int>> ans;
    backtrace(A, 0, 0, N, box, ans);
    return ans;
  }
};

// @lc code=end
