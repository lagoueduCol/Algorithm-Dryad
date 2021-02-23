/*
 * @lc app=leetcode.cn id=4 lang=cpp
 *
 * [4] 寻找两个正序数组的中位数
 *
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/description/
 * https://www.lintcode.com/problem/median-of-two-sorted-arrays/description
 *
 * algorithms
 * Hard (39.50%)
 * Likes:    3625
 * Dislikes: 0
 * Total Accepted:    323.6K
 * Total Submissions: 819.4K
 * Testcase Example:  '[1,3]\n[2]'
 *
 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1
 * 和 nums2。请你找出并返回这两个正序数组的中位数。
 *
 * 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 *
 *
 * 示例 2：
 *
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 *
 *
 * 示例 3：
 *
 * 输入：nums1 = [0,0], nums2 = [0,0]
 * 输出：0.00000
 *
 *
 * 示例 4：
 *
 * 输入：nums1 = [], nums2 = [1]
 * 输出：1.00000
 *
 *
 * 示例 5：
 *
 * 输入：nums1 = [2], nums2 = []
 * 输出：2.00000
 *
 *
 *
 *
 * 提示：
 *
 *
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -10^6 <= nums1[i], nums2[i] <= 10^6
 *
 *
 */

// @lc code=start
class Solution {
public:
  double findMedianSortedArrays(vector<int> &A, vector<int> &B) {
    const int len = A.size() + B.size(), alen = A.size(), blen = B.size();
    int i = 0, j = 0;
    if (!len)
      return 0;
    int k = (len - 1) >> 1;
    while (k > 0) {
      const int p = (k - 1) >> 1;
      if (j + p >= blen || (i + p < alen && A[i + p] < B[j + p])) {
        i += p + 1;
      } else {
        j += p + 1;
      }
      k -= p + 1;
    }
    const double front =
        (j >= blen || (i < alen && A[i] < B[j])) ? A[i++] : B[j++];
    if (len & 0x01)
      return front;
    const double back = (j >= blen || (i < alen && A[i] < B[j])) ? A[i] : B[j];
    return (front + back) / 2.0;
  }
};
// @lc code=end
