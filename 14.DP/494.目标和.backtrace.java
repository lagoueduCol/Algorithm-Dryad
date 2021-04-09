/*
 * @lc app=leetcode.cn id=494 lang=java
 *
 * [494] 目标和
 *
 * https://leetcode-cn.com/problems/target-sum/description/
 *
 * algorithms
 * Medium (45.05%)
 * Likes:    628
 * Dislikes: 0
 * Total Accepted:    75.7K
 * Total Submissions: 167.9K
 * Testcase Example:  '[1,1,1,1,1]\n3'
 *
 * 给定一个非负整数数组，a1, a2, ..., an,
 * 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或
 * -中选择一个符号添加在前面。
 *
 * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 *
 *
 *
 * 示例：
 *
 * 输入：nums: [1, 1, 1, 1, 1], S: 3
 * 输出：5
 * 解释：
 *
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 *
 * 一共有5种方法让最终目标和为3。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 数组非空，且长度不会超过 20 。
 * 初始的数组的和不会超过 1000 。
 * 保证返回的最终结果能被 32 位整数存下。
 *
 *
 */

// @lc code=start
class Solution {
    private int ans = 0;

    private void backtrace(int[] A, int idx, int cur, int target) {
        if (idx >= A.length) {
            ans += cur == target ? 1 : 0;
            return;
        }

        if (A[idx] != 0) {
            backtrace(A, idx + 1, cur + A[idx], target);
            backtrace(A, idx + 1, cur - A[idx], target);
        } else {
            backtrace(A, idx + 1, cur, target);
        }
    }

    public int findTargetSumWays(int[] A, int S) {
        ans = 0;

        // 首先求得整个数组的最大值
        int total = 0;
        for (int v : A) {
            total += v;
        }

        // 取正负号相加之后和为S
        // 如果有解，并且S为负数
        // 那么只需要把正负号互换就可以了
        S = S > 0 ? S : -S;

        if (S > total) {
            return 0;
        }

        // 计算0的个数
        // 因为每个0都可以表示可选，或者不可选
        int zero = 0;
        for (int x : A) {
            zero += x == 0 ? 1 : 0;
        }

        // 然后再进行暴力搜索
        // 回溯
        backtrace(A, 0/* idx */, 0/* cur */, S/* target */);

        return ans * (1 << zero);
    }
}
// @lc code=end
