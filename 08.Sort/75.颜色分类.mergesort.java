/*
 * @lc app=leetcode.cn id=75 lang=java
 *
 * [75] 颜色分类
 *
 * https://leetcode-cn.com/problems/sort-colors/description/
 *
 * algorithms
 * Medium (57.26%)
 * Likes:    761
 * Dislikes: 0
 * Total Accepted:    166.8K
 * Total Submissions: 291.4K
 * Testcase Example:  '[2,0,2,1,1,0]'
 *
 * 给定一个包含红色、白色和蓝色，一共 n
 * 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 *
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 *
 *
 *
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：nums = [2,0,2,1,1,0]
 * 输出：[0,0,1,1,2,2]
 *
 *
 * 示例 2：
 *
 *
 * 输入：nums = [2,0,1]
 * 输出：[0,1,2]
 *
 *
 * 示例 3：
 *
 *
 * 输入：nums = [0]
 * 输出：[0]
 *
 *
 * 示例 4：
 *
 *
 * 输入：nums = [1]
 * 输出：[1]
 *
 *
 *
 *
 * 提示：
 *
 *
 * n == nums.length
 * 1
 * nums[i] 为 0、1 或 2
 *
 *
 *
 *
 * 进阶：
 *
 *
 * 你可以不使用代码库中的排序函数来解决这道题吗？
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 *
 *
 */

// @lc code=start
class Solution {
  private void msort(int[] a, int b, int e, int t[]) {
    // empty range
    if (b >= e) {
      return;
    }

    // single
    if (b + 1 == e) {
      return;
    }

    // 分成两半
    final int m = b + ((e - b) >> 1);

    msort(a, b, m, t);
    msort(a, m, e, t);

    for (int i = b; i < e; i++) {
      System.out.println(a[i]);
    }

    int i = b;
    int j = m;
    int to = b;

    // 将两个子数组进行合并
    while (i < m || j < e) {
      if (j >= e || i < m && a[i] <= a[j]) {
        t[to++] = a[i++];
      } else {
        t[to++] = a[j++];
      }
    }

    for (i = b; i < e; i++) {
      a[i] = t[i];
    }
  }
  public void sortColors(int[] nums) {
    int[] t = new int[nums.length];
    msort(nums, 0, nums.length, t);
  }
}
// @lc code=end
