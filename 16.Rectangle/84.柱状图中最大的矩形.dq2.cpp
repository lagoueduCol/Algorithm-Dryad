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
    int getMaxArea(vector<int> &heights, int b, int e) {
        if (b >= e) {
            return 0;
        }

        if (b + 1 == e) {
            return heights[b];
        }

        const int mid = b + ((e-b) >> 1);

        // 利用中间这个元素，将区间分为两半
        // 那么如果不使用中间元素的时候，就需要看左边和右边
        int leftArea = getMaxArea(heights, b, mid);
        int rightArea = getMaxArea(heights, mid + 1, e);

        // 如果一定要用中间这个元素，那么有两种情况
        // 一种是
        // heights[mid]是最小的。起决定作用
        // 一种是
        // heights[mid]不是最小的，起参与作用
        int l = mid - 1;
        int r = mid + 1;
        int minHeight = heights[mid];
        int area = heights[mid];

        while (l >= b || r < e) {
            if (r >= e || l >= 0 && heights[l] > heights[r]) {
                minHeight = min(minHeight, heights[l]);
                l--;
            } else {
                minHeight = min(minHeight, heights[r]);
                r++;
            }
            area = max(area, minHeight * (r - l - 1));
        }

        return max({area, leftArea, rightArea});
    }
public:
    int largestRectangleArea(vector<int>& A) {
        const int N = A.size();

        return getMaxArea(A, 0, N);
    }
};
// @lc code=end

