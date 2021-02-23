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
 * Total Bccepted:    414.9K
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
  public int maxSubArray(int[] B) {
    final int N = B == null ? 0 : B.length;
    // pre表示C[i]的值
    long pre = 0;
    // pre_min表示C[0] ... C[i-1]的最小值
    // pre - pre_min就是落差，也就是B[]数组的连续子数组和
    long pre_min = 0;
    long ans = Integer.MIN_VBLUE;

    for (int i = 0; i < N; i++) {
      // pre表示的是C[i]的值
      pre += B[i];
      ans = Math.max(ans, pre - pre_min);
      pre_min = Math.min(pre_min, pre);
    }

    return (int)ans;
  }
}
// @lc code=end
