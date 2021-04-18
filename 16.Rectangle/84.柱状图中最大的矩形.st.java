/*
 * @lc app=leetcode.cn id=84 lang=java
 *
 * [84] 柱状图中最大的矩形
 *
 * https://leetcode-cn.com/problems/largest-rectangle-in-histogram/description/
 *
 * algorithms
 * Hard (42.74%)
 * Likes:    1213
 * Dislikes: 0
 * Total Accepted:    126.1K
 * Total Submissions: 294.1K
 * Testcase Example:  '[2,1,5,6,2,3]'
 *
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * 
 * 
 * 
 * 
 * 
 * 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
 * 
 * 
 * 
 * 
 * 
 * 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
 * 
 * 
 * 
 * 示例:
 * 
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 * 
 */

// @lc code=start
class Solution {
    private int log2(int N) {
        return (int)(Math.log(N) / Math.log(2));
    }

    private int[][] createST(int N) {
        final int powerOf2 = log2(N);
        int[][] st = new int[N][powerOf2 + 1];
        for (int i = 0; i < N; i++) {
            st[i] = new int[powerOf2+1];
        }
        return st;
    }

    private void buildST(int[] A, int[][] st) {
        final int N = A == null ? 0 : A.length;

        // 第一步：
        //    - 处理长度为1的区间
        //      即[i, i + 1)
        //
        // 区间的表示：
        //      [start=i, len=2^0]
        //      也就是st[i][len=2^0]
        for (int i = 0; i < N; i++) {
            st[i][0] = A[i];
        }

        // 递推：
        //      依次处理2 ^ j长度。
        //      其中2 ^ j = 2 ^ (j-1) + 2 ^ (j-1)
        //      注意：这里的长度都是完整的2 ^ j的
        for (int j = 1; (1 << j) <= N; j++) {
            // 这里要处理的区间[i, i + (1<<j)]
            // last = i + (1<<j)
            // 根据左闭右开原则，last是可以取到n的。这点要注意。
            for (int i = 0; (i + (1 << j)) <= N; i++) {
                st[i][j] = Math.min(st[i][j - 1],
                                    st[i + (1 << (j - 1))][j - 1]);
            }
        }
    }

    private int minHeight(int[][] st, int l, int r) {
        // 这里我们将区间[l, r]分为两个区间
        // [l, l+log2(len)] => [l, len=log2(len)]
        // [r-log2(len)+1, r] => [r-log2(len) + 1, len=log2(len)]
        int len = r - l + 1;
        int j = log2(len);
        return Math.min(st[l][j], st[r - (1 << j) + 1][j]);
    }

    public int largestRectangleArea(int[] A) {
        final int N = A == null ? 0 : A.length;

        int[][] st = createST(N);

        buildST(A, st);

        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                ans = Math.max(ans, minHeight(st, i, j) * (j - i + 1));
            }
        }
        return ans;
    }
}

// @lc code=end

