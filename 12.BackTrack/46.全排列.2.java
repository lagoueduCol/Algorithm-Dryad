import java.awt.List;
import java.util.ArrayList;

/*
 * @lc app=leetcode.cn id=46 lang=java
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
class Solution
{
  private void append(int[] box, List<List<Integer>> ans)
  {
    ans.add(new ArrayList<>());
    for (int x: box) {
      ans.get(ans.size()-1).add(x);
    }
  }

  private void swap(int[] box, int a, int b) {
    int t = box[a];
    box[a] = box[b];
    box[b] = t;
  }

  private void backtrack(int[] A,
                         int i, /*第i个人, 选择范围[i, N)*/
                         int [] box,
                         List<List<Integer>> ans)
  {
    final int N = A == null ? 0 : A.length;
    // 如果状态已经满足要求
    // box已经填满了
    if (i == N) {
      append(box, ans);
    }

    // 我们总是从第0个人开始，那么一共有N个元素
    // 那么当到第N个人的时候，已经没有东西可以选了。
    // [N ~ inf) 后面所有的人都没有东西可以选了。
    if (i >= N) {
      return;
    }

    // 第i个人可以选择box后面可选的元素。
    for (int j = i; j < N; j++) {
      // 这里只是选中box[j]元素
      swap(box, i, j);
      backtrack(A, i + 1, box, ans);
      // 箱子一定要还原
      swap(box, i, j);
    }
  }

  public List<List<Integer>> permute(int[] A)
  {
    final int N = A == null ? 0 : A.length;
    List<List<Integer>> ans = new ArrayList<>();
    if (N == 0) {
      return ans;
    }

    // 首先构建好箱子，为了让第i个人有元素可以选择
    // 我们需要提前将元素放在里面。
    int[] box = new int[N];
    for (int i = 0; i < N; i++) {
      box[i] = A[i];
    }

    backtrack(A, 0, box, ans);
    return ans;
  }
}
// @lc code=end
