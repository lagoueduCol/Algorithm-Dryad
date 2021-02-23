/*
 * @lc app=leetcode.cn id=81 lang=java
 *
 * [81] 搜索旋转排序数组 II
 *
 * https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii/description/
 *
 * algorithms
 * Medium (36.74%)
 * Likes:    283
 * Dislikes: 0
 * Total Accepted:    52.9K
 * Total Submissions: 143.9K
 * Testcase Example:  '[2,5,6,0,0,1,2]\n0'
 *
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * 
 * ( 例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2] )。
 * 
 * 编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false。
 * 
 * 示例 1:
 * 
 * 输入: nums = [2,5,6,0,0,1,2], target = 0
 * 输出: true
 * 
 * 
 * 示例 2:
 * 
 * 输入: nums = [2,5,6,0,0,1,2], target = 3
 * 输出: false
 * 
 * 进阶:
 * 
 * 
 * 这是 搜索旋转排序数组 的延伸题目，本题中的 nums  可能包含重复元素。
 * 这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？
 * 
 * 
 */

// @lc code=start

class Solution {
    // 注意这里按照题目要求，这里需要返回true/false
    public boolean search(int[] A, int x) {
        final int N = A == null ? 0 : A.length;
        int l = 0, r = N;

        while (l < r) {
            final int m = l + ((r - l) >> 1);
            // 先处理掉能够取到的3个值。
            // 这里不能用A[r]，因为我们使用的是
            // 开闭原则，右边始终是不能取到的。
            if (A[m] == x || A[l] == x || A[r-1] == x) {
                return true;
            }
            if (A[l] == A[m]) {
              l++;
              continue;
            }

            // 这里开始把不要的区间切掉
            if (A[m] > A[l]) {
                if (A[l] < x && x < A[m]) {
                    // case (a)
                    // 到这里，A[m]已经不可能等于x
                    // 所以需要将[m, ...., r)
                    // 这段区间一起扔掉
                    // 留下[l, m)这段区间，续断查找
                    r = m;
                } else {
                    // case (b)
                    // A[m]已经不可能等于x
                    // 所以这里将[l, ..., m]这个区间切掉
                    // 留下[m + 1, r)
                    l = m + 1;
                }
            } else {
                if (A[m] < x && x < A[r - 1]) {
                    // case (c)
                    // 到这里，左边的区间[l, ... , m]已经不需要了
                    // 只需要留下[m + 1, r)
                    l = m + 1;
                } else {
                    // case (d)
                    // 到这里，右边的区间[m, ... , r)
                    // 已经不需要了，只需要留下区间[l, ... , m)
                    r = m;
                }
            }
        }
        return false;
    }
}
// @lc code=end

