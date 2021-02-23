/*
 * @lc app=leetcode.cn id=154 lang=cpp
 *
 * [154] 寻找旋转排序数组中的最小值 II
 *
 * https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/description/
 *
 * algorithms
 * Hard (50.48%)
 * Likes:    236
 * Dislikes: 0
 * Total Accepted:    48.7K
 * Total Submissions: 96.5K
 * Testcase Example:  '[1,3,5]'
 *
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,1,2,4,5,6,7]  可能变为 [4,5,6,7,0,1,2] )。
 *
 * 请找出其中最小的元素。
 *
 * 注意数组中可能存在重复的元素。
 *
 * 示例 1：
 *
 * 输入: [1,3,5]
 * 输出: 1
 *
 * 示例 2：
 *
 * 输入: [2,2,2,0,1]
 * 输出: 0
 *
 * 说明：
 *
 *
 * 这道题是 寻找旋转排序数组中的最小值 的延伸题目。
 * 允许重复会影响算法的时间复杂度吗？会如何影响，为什么？
 *
 *
 */

// @lc code=start
class Solution {
   public:
    int findMin(vector<int>& A) {
        const int N = A.size();
        // 由于要使用A[r-1]
        // 这里直接用了闭区间[l, r]
        int l = 0, r = N - 1;
        // 保证区间中至少有一个元素
        while (l < r) {
            const int m = l + ((r - l) >> 1);
            // 如果A[m] < A[r]
            // 表示A[m]掉在了右边的区间
            // 那么此时[m + 1, r]可以不要了
            if (A[m] < A[r]) {
                // 保留区间[l, m]
                r = m;
            } else if (A[m] > A[r]) {
                // 当A[m] > A[r]时
                // A[m]掉在左边的区间
                // 此时[l, m]可以不要了
                // 保留区间[m + 1, r]
                l = m + 1;
            } else {
                // 如果与右边元素相等，此时r与m不相等的
                // 那么扔掉A[r]
                r--;
            }
        }
        return A[l];
    }
};
// @lc code=end
