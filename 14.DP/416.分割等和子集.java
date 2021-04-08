/*
 * @lc app=leetcode.cn id=416 lang=java
 *
 * [416] 分割等和子集
 *
 * https://leetcode-cn.com/problems/partition-equal-subset-sum/description/
 *
 * algorithms
 * Medium (49.67%)
 * Likes:    739
 * Dislikes: 0
 * Total Accepted:    115.5K
 * Total Submissions: 232.4K
 * Testcase Example:  '[1,5,11,5]'
 *
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * 
 * 注意:
 * 
 * 
 * 每个数组中的元素不会超过 100
 * 数组的大小不会超过 200
 * 
 * 
 * 示例 1:
 * 
 * 输入: [1, 5, 11, 5]
 * 
 * 输出: true
 * 
 * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
 * 
 * 
 * 
 * 
 * 示例 2:
 * 
 * 输入: [1, 2, 3, 5]
 * 
 * 输出: false
 * 
 * 解释: 数组不能分割成两个元素和相等的子集.
 * 
 * 
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean canPartition(int[] A) {
        final int N = A == null ? 0 : A.length;
        if (N <= 0) {
            return true;
        }

        // 数组求和
        int s = 0;
        for (int x : A) {
            s += x;
        }

        // 如果为奇数， 肯定是没有办法切分
        if ((s & 0x01) == 1) {
            return false;
        }

        // 分割为等和，那么相当于要取同值的一半
        final int V = s >> 1;

        // 这个dp表示着一开始可以访问的点集
        // 我们用true表示这个点存在于点集中
        // false表示这个点不存在点集中
        boolean[] dp = new boolean[V + 1];

        // 首先初始集合肯定是s0={0}
        dp[0] = true;

        // 开始利用a[i]来进行推导
        for (int x : A) {
            // 注意这里更新的方向
            for (int node = V; node - x >= 0; node--) {
                final int newNode = node;
                final int oldExistsNode = node - x;
                if (dp[oldExistsNode]) {
                    dp[newNode] = true;
                }
            }
        }

        return dp[V];
    }
}
// @lc code=end
