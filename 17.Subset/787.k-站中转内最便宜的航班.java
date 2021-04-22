import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=787 lang=java
 *
 * [787] K 站中转内最便宜的航班
 *
 * https://leetcode-cn.com/problems/cheapest-flights-within-k-stops/description/
 *
 * algorithms
 * Medium (36.99%)
 * Likes:    265
 * Dislikes: 0
 * Total Accepted:    16.2K
 * Total Submissions: 43.7K
 * Testcase Example:  '3\n[[0,1,100],[1,2,100],[0,2,500]]\n0\n2\n1'
 *
 * 有 n 个城市通过 m 个航班连接。每个航班都从城市 u 开始，以价格 w 抵达 v。
 *
 * 现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，你的任务是找到从 src 到 dst 最多经过
 * k 站中转的最便宜的价格。 如果没有这样的路线，则输出 -1。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入:
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 1
 * 输出: 200
 * 解释:
 * 城市航班图如下
 *
 *
 * 从城市 0 到城市 2 在 1 站中转以内的最便宜价格是 200，如图中红色所示。
 *
 * 示例 2：
 *
 *
 * 输入:
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 0
 * 输出: 500
 * 解释:
 * 城市航班图如下
 *
 *
 * 从城市 0 到城市 2 在 0 站中转以内的最便宜价格是 500，如图中蓝色所示。
 *
 *
 *
 * 提示：
 *
 *
 * n 范围是 [1, 100]，城市标签从 0 到 n - 1
 * 航班数量范围是 [0, n * (n - 1) / 2]
 * 每个航班的格式 (src, dst, price)
 * 每个航班的价格范围是 [1, 10000]
 * k 范围是 [0, n - 1]
 * 航班没有重复，且不存在自环
 *
 *
 */

// @lc code=start
class Solution {
    long[] Clone(long[] arr) {
        long[] ans = new long[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int target, int K) {
        long[] dst = new long[n + 1];
        for (int i = 0; i <= n; i++) {
            dst[i] = (long) Integer.MAX_VALUE / 2;
        }

        for (int i = 0; i <= K; i++) {

            long[] next = Clone(dst);
            next[src] = dst[src] = 0;

            for (int[] f : flights) {
                final int from = f[0], to = f[1], cost = f[2];
                next[to] = Math.min(next[to], dst[from] + cost);
            }

            dst = next;
        }

        return dst[target] >= (long)Integer.MAX_VALUE / 2 ? -1 : (int)dst[target];
    }
}
// @lc code=end
