import java.util.Map;
import java.util.Set;

/*
 * @lc app=leetcode.cn id=90 lang=java
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
  private void append(List<Integer> box, List<List<Integer>> ans)
  {
    ans.add(new ArrayList<>());
    for (Integer x : box) {
      ans.get(ans.size() - 1).add(x);
    }
  }

  private void backtrace(int[] A,
                         int start, /*第i个人的选择范围(start, N)*/
                         List<Integer> box,
                         List<List<Integer>> ans)
  {
      final int N = A == null ? 0 : A.length;
      append(box, ans);

      // 已经没得选了
      if (start >= N) {
          return;
      }

      for (int j = start; j < N; j++) {
        if (j > start && A[j] == A[j-1]) continue;
        box.add(A[j]);
        backtrace(A, j + 1, box, ans);
        box.remove(box.size()-1);
      }
  }

  public List<List<Integer>> subsetsWithDup(int[] A) {
      final int N = A == null ? 0 : A.length;
      List<Integer> box = new ArrayList<>();
      List<List<Integer>> ans = new ArrayList<>();
      if (N <= 0) {
          return ans;
      }
      Arrays.sort(A);
      backtrace(A, 0, box, ans);
      return ans;
  }
}
// @lc code=end
