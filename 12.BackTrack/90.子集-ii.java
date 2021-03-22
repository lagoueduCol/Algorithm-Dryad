import java.util.Arrays;
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
                         int i, /*第i个人*/
                         int start, /*第i个人的选择范围(start, end)*/
                         int end,
                         List<Integer> box,
                         List<List<Integer>> ans)
  {
      final int N = A == null ? 0 : A.length;
      append(box, ans);

      if (i >= N) {
          return;
      }

      Set<Integer> used = new HashSet<>();
      for (int j = start; j < end; j++) {
          if (!used.contains(A[j])) {
              box.add(A[j]);
              backtrace(A, i + 1, j + 1, end, box, ans);
              box.remove(box.size()-1);
              used.add(A[j]);
          }
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
      backtrace(A, 0, 0, N, box, ans);
      return ans;
  }
}
// @lc code=end
