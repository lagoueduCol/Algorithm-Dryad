/*
 * @lc app=leetcode.cn id=84 lang=cpp
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
   public:
    int largestRectangleArea(vector<int>& A) {
        const int N = A.size();
        if (N == 0) return 0;

        vector<int> lm(N);
        vector<int> rm(N);

        lm[0] = -1;
        for (int i = 1; i < N; i++) {
            int idx = i - 1;
            while (idx != -1 && A[idx] >= A[i]) { idx = lm[idx]; }
            lm[i] = idx;
        }

        rm[N - 1] = N;
        for (int i = N - 2; i >= 0; i--) {
            int idx = i + 1;
            while (idx != N && A[idx] >= A[i]) { idx = rm[idx]; }
            rm[i] = idx;
        }

        int ans = 0;
        for (int i = 0; i < N; i++) {
            ans = max(ans, A[i] * (rm[i] - lm[i] - 1));
        }
        return ans;
    }
};

// @lc code=end

