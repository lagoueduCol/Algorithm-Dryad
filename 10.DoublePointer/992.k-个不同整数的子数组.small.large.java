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

    private void buildSmallRange(int[] A, int N, int K) {
        int left = -1;
        Counter cnt = new Counter();

        for (int i = 0; i < N; i++) {
            cnt.add(A[i], 1);

            // 如果加入之后，破坏了条件
            while (cnt.size() >= K) {
                // 如果相等
                if (cnt.size() == K) {
                    small[i] = i - left;
                }
                cnt.add(A[++left], -1);
            }

            if (small[i] > 0) {
                cnt.add(A[left--], 1);
                assert cnt.size() == K;
            }
        }
    }

    private void buildLargeRange(int[] A, int N, int K) {
        int left = -1;
        Counter cnt = new Counter();
        for (int i = 0; i < N; i++) {
            cnt.add(A[i], 1);

            while (cnt.size() > K) {
                cnt.add(A[++left], -1);
            }

            if (cnt.size() == K) {
                large[i] = i - left;
            }
        }
    }

    public int subarraysWithKDistinct(int[] A, int K) {
        final int N = A == null ? 0 : A.length;
        if (N <= 0 || K > N) {
            return 0;
        }

        small = new int[N];
        large = new int[N];

        buildSmallRange(A, N, K);
        buildLargeRange(A, N, K);

        int ans = 0;
        for (int i = 0; i < N; i++) {
            if (large[i] > 0 && small[i] > 0) {
                ans += large[i] - small[i] + 1;
            }
        }

        return ans;
    }
}
// @lc code=end
