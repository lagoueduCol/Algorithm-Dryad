/*
 * @lc app=leetcode.cn id=47 lang=cpp
 *
 * [47] 全排列 II
 *
 * https://leetcode-cn.com/problems/permutations-ii/description/
 *
 * algorithms
 * Medium (62.96%)
 * Likes:    636
 * Dislikes: 0
 * Total Accepted:    148K
 * Total Submissions: 235.1K
 * Testcase Example:  '[1,1,2]'
 *
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 * ⁠[1,2,1],
 * ⁠[2,1,1]]
 *
 *
 * 示例 2：
 *
 *
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1
 * -10
 *
 *
 */

// @lc code=start
class Solution
{
  void backtrace(vector<int>& box, int start, vector<vector<int>>& ans)
  {
    const int N = box.size();
    if (start >= N) {
      ans.push_back(box);
      return;
    }

    for (int j = start; j < N; j++) {
      if (find(box.begin() + start, box.begin() + j, box[j]) ==
          (box.begin() + j)) {
        swap(box[j], box[start]);
        backtrace(box, start + 1, ans);
        swap(box[j], box[start]);
      }
    }
  }

public:
  vector<vector<int>> permuteUnique(vector<int>& A)
  {
    const int N = A.size();
    vector<int> box;
    vector<vector<int>> ans;
    backtrace(A, 0, ans);
    return ans;
  }
};
// @lc code=end
