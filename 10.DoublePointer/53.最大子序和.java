/*
 * @lc app=leetcode.cn id=53 lang=java
 *
 * [53] 最大子序和
 *
 * https://leetcode-cn.com/problems/maximum-subarray/description/
 *
 * algorithms
 * Easy (53.24%)
 * Likes:    2881
 * Dislikes: 0
 * Total Accepted:    414.9K
 * Total Submissions: 779.3K
 * Testcase Example:  '[-2,1,-3,4,-1,2,1,-5,4]'
 *
 * 给定一个整数数组
 * nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 *
 *
 * 示例 2：
 *
 *
 * 输入：nums = [1]
 * 输出：1
 *
 *
 * 示例 3：
 *
 *
 * 输入：nums = [0]
 * 输出：0
 *
 *
 * 示例 4：
 *
 *
 * 输入：nums = [-1]
 * 输出：-1
 *
 *
 * 示例 5：
 *
 *
 * 输入：nums = [-100000]
 * 输出：-100000
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1
 * -10^5
 *
 *
 *
 *
 * 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。
 *
 */

// @lc code=start
class Solution {
  public int maxSubArray(int[] A) {
    final int N = A == null ? 0 : A.length;
    if (N <= 0) {
      return 0;
    }
    int maxItem = Integer.MIN_VALUE;

    // 区间左边的位置
    int left = -1;
    // 区间的和
    int s = 0;
    // 最优解
    int ans = 0;

    for (int i = 0; i < N; i++) {
      // 在让A[i]加入之前，s表示的是以A[i-1]为右端点的最大和
      // 见到A[i]的时候，让A[i]加入到这个区间中
      s += A[i];

      // 但是有可能将区间的状态破坏掉了
      // 我们要求的是最大和，那么当s < 0
      // 的时候，还不如取空区间呢。
      if (s < 0) {
        s = 0;
        // (left=i, i]左开右闭，构成事实上的空区间
        left = i;
      }

      // 此时是以A[i]为右端点的最大值
      // 用来更新答案
      ans = Math.max(ans, s);

      // 看一下数组的最大值是不是大于0
      maxItem = Math.max(maxItem, A[i]);
    }

    // 如果数组中所有元素都小于，那么只需要返回最大的那个就可以了。
    return maxItem < 0 ? maxItem : ans;

  }
}
// @lc code=end
