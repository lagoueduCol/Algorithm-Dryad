/*
 * @lc app=leetcode.cn id=435 lang=java
 *
 * [435] 无重叠区间
 *
 * https://leetcode-cn.com/problems/non-overlapping-intervals/description/
 *
 * algorithms
 * Medium (49.26%)
 * Likes:    363
 * Dislikes: 0
 * Total Accepted:    58.6K
 * Total Submissions: 118.9K
 * Testcase Example:  '[[1,2]]'
 *
 * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
 *
 * 注意:
 *
 *
 * 可以认为区间的终点总是大于它的起点。
 * 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
 *
 *
 * 示例 1:
 *
 *
 * 输入: [ [1,2], [2,3], [3,4], [1,3] ]
 *
 * 输出: 1
 *
 * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
 *
 *
 * 示例 2:
 *
 *
 * 输入: [ [1,2], [1,2], [1,2] ]
 *
 * 输出: 2
 *
 * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
 *
 *
 * 示例 3:
 *
 *
 * 输入: [ [1,2], [2,3] ]
 *
 * 输出: 0
 *
 * 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
 *
 *
 */

// @lc code=start
class Solution {
  public int eraseOverlapIntervals(int[][] A) {
    final int N = A == null ? 0 : A.length;

    Arrays.sort(A, new Comparator<int[]>() {
      public int compare(int[] a, int[] b) {
        return a[1] == b[1] ? 0 : (a[1] < b[1] ? -1 : 1);
      }
    });

    int preEnd = Integer.MIN_VALUE;
    int ans = 0;

    for (int i = 0; i < N; i++) {
      final int start = A[i][0];
      if (preEnd <= start) {
        preEnd = A[i][1];
        ans++;
      }
    }

    return N - ans;
  }
}
// @lc code=end
