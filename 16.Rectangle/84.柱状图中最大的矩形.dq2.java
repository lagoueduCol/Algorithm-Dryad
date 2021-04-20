import java.lang.reflect.Array;

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
    private int getMaxRangeArea(int[] heights, int b, int e) {
        if (b >= e) {
            return 0;
        }

        // 如果只有一个元素
        if (b + 1 == e) {
            return heights[b];
        }

        // 用数组中间的那个元素将数组分为两半
        final int mid = b + ((e-b) >> 1);

        // 不包含中间这个元素的时候，那么就只能在这个元素的左边和右边寻找了
        int leftMaxArea = getMaxRangeArea(heights, b, mid);
        int rightMaxArea = getMaxRangeArea(heights, mid + 1, e);

        // 如果一定要包含heights[mid]
        // 那么就有两种情况。
        int minHeight = heights[mid];
        int containsMidIndexArea = minHeight;
        int left = mid - 1, right = mid + 1;
        while (left >= b || right < e) {
            if (right >= e || left >= b && heights[left] >= heights[right]) {
                minHeight = Math.min(minHeight, heights[left]);
                left--;
            } else {
                minHeight = Math.min(minHeight, heights[right]);
                right++;
            }
            final int tmp = minHeight * (right - left - 1);
            containsMidIndexArea = Math.max(containsMidIndexArea, tmp);
        }

        return Math.max(containsMidIndexArea, Math.max(leftMaxArea, rightMaxArea));
    }

    public int largestRectangleArea(int[] heights) {
        final int N = heights == null ? 0 : heights.length;
        return getMaxRangeArea(heights, 0, N);
    }
}

// @lc code=end
