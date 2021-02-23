/*
 * @lc app=leetcode.cn id=992 lang=java
 *
 * [992] K 个不同整数的子数组
 *
 * https://leetcode-cn.com/problems/subarrays-with-k-different-integers/description/
 *
 * algorithms
 * Hard (44.38%)
 * Likes:    269
 * Dislikes: 0
 * Total Accepted:    20.1K
 * Total Submissions: 45.2K
 * Testcase Example:  '[1,2,1,2,3]\n2'
 *
 * 给定一个正整数数组 A，如果 A 的某个子数组中不同整数的个数恰好为 K，则称 A 的这个连续、不一定不同的子数组为好子数组。
 * 
 * （例如，[1,2,3,1,2] 中有 3 个不同的整数：1，2，以及 3。）
 * 
 * 返回 A 中好子数组的数目。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：A = [1,2,1,2,3], K = 2
 * 输出：7
 * 解释：恰好由 2 个不同整数组成的子数组：[1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2],
 * [1,2,1,2].
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：A = [1,2,1,3,4], K = 3
 * 输出：3
 * 解释：恰好由 3 个不同整数组成的子数组：[1,2,1,3], [2,1,3], [1,3,4].
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * 1 
 * 1 
 * 
 * 
 */

import java.util.*;

// @lc code=start
class Counter extends HashMap<Integer, Integer> {
    public int get(Integer k) {
        return containsKey(k) ? super.get(k) : 0;
    }

    public void add(Integer k, int v) {
        put(k, get(k) + v);
        if (get(k) <= 0) {
            remove(k);
        }
    }
}

class Solution {
    private int[] small = null;
    private int[] large = null;

    // 这个函数是求包含个数不超过K的最长区间
    private int lessOrEqualNumber(int[] A, int N, int K) {
        // 不同数目小于等于k的区间个数
        // 我们需要找到每个位置的最长区间
        int left = -1;
        int ans = 0;

        Counter cnt = new Counter();

        for (int i = 0; i < N; i++) {
            // 将A[i]添加到区间里面
            cnt.add(A[i], 1);

            // 破坏了条件
            while (cnt.size() > K) {
                cnt.add(A[++left], -1);
            }

            ans += i - left;
        }

        return ans;
    }

    public int subarraysWithKDistinct(int[] A, int K) {
        final int N = A == null ? 0 : A.length;
        return lessOrEqualNumber(A, N, K) - lessOrEqualNumber(A, N, K - 1);
    }
}
// @lc code=end
