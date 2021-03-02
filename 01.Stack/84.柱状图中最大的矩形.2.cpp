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
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为
 * 1 。
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

/*
 * 思路：
 *
 * 2个重要的性质：
 *
 * 一个递增栈里面存放的是数组的下标 stack = [i, j]
 *
 * 性质1: 对于j而言，下标[i+1 ... j-1] 这里面的元素的A[x]值都 >= A[j]
 *       对于i而言，(-1, i-1] 这里面元素的值都 >= A[i];
 *
 * 如果此时A[k]要入栈。并且A[k] < A[j]，要将A[j]出栈。
 *
 * 性质2: A[j+1 ... k-1]这个区间里面的元素都大于A[j]
 *
 */

// @lc code=start
class Solution {
   public:
    int largestRectangleArea(vector<int>& A) {
        const int N = A.size();

        int ans = 0;
        stack<int> t;
        for (int i = 0; i <= N; i++) {
            const int x = i == N ? -1 : A[i];
            while (!t.empty() && A[t.top()] > x) {
                auto idx = t.top();
                t.pop();
                auto height = A[idx];
                const int rightPos = i;
                const int leftPos = t.empty() ? -1 : t.top();
                const int width = rightPos - leftPos - 1;
                const int area = height * width;
                ans = max(ans, area);
            }
            t.push(i);
        }

        return ans;
    }
};
// @lc code=end
