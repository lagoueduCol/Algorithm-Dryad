/*
 * @lc app=leetcode.cn id=33 lang=java
 *
 * [33] 搜索旋转排序数组
 *
 * https://leetcode-cn.com/problems/search-in-rotated-sorted-array/description/
 *
 * algorithms
 * Medium (40.44%)
 * Likes:    1176
 * Dislikes: 0
 * Total Accepted:    219K
 * Total Submissions: 541.4K
 * Testcase Example:  '[4,5,6,7,0,1,2]\n0'
 *
 * 升序排列的整数数组 nums 在预先未知的某个点上进行了旋转（例如， [0,1,2,4,5,6,7] 经旋转后可能变为 [4,5,6,7,0,1,2]
 * ）。
 * 
 * 请你在数组中搜索 target ，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [4,5,6,7,0,1,2], target = 0
 * 输出：4
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [4,5,6,7,0,1,2], target = 3
 * 输出：-1
 * 
 * 示例 3：
 * 
 * 
 * 输入：nums = [1], target = 0
 * 输出：-1
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * -10^4 
 * nums 中的每个值都 独一无二
 * nums 肯定会在某个点上旋转
 * -10^4 
 * 
 * 
 */

// @lc code=start
class Solution {
    public int search(int[] A, int x) {
        final int N = A == null ? 0 : A.length;
        int l = 0, r = N;

        while (l < r) {
            final int m = l + ((r - l) >> 1);
            // 先处理掉能够取到的3个值。
            if (A[l] == x)
                return l;
            if (A[m] == x)
                return m;
            // 这里不能用A[r]，因为我们使用的是
            // 开闭原则，右边始终是不能取到的。
            if (A[r - 1] == x)
                return r - 1;

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
        return -1;
    }
}
// @lc code=end
