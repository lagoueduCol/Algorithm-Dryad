/*
 * @lc app=leetcode.cn id=153 lang=cpp
 *
 * [153] 寻找旋转排序数组中的最小值
 *
 * https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/description/
 *
 * algorithms
 * Medium (52.31%)
 * Likes:    350
 * Dislikes: 0
 * Total Accepted:    102.8K
 * Total Submissions: 196.6K
 * Testcase Example:  '[3,4,5,1,2]'
 *
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。例如，数组 [0,1,2,4,5,6,7]
 * 可能变为 [4,5,6,7,0,1,2] 。
 *
 * 请找出其中最小的元素。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：nums = [3,4,5,1,2]
 * 输出：1
 *
 *
 * 示例 2：
 *
 *
 * 输入：nums = [4,5,6,7,0,1,2]
 * 输出：0
 *
 *
 * 示例 3：
 *
 *
 * 输入：nums = [1]
 * 输出：1
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1
 * -5000
 * nums 中的所有整数都是 唯一 的
 * nums 原来是一个升序排序的数组，但在预先未知的某个点上进行了旋转
 *
 *
 */

// @lc code=start
class Solution {
   public:
    int findMin(vector<int>& A) {
        const int N = A.size();
        int l = 0, r = N;
        while (l < r) {
            const int m = l + ((r - l) >> 1);

            // 如果A[m]在数组的左半部分
            if (A[0] <= A[m]) {
                // 如果整个数组是有序的
                if (A[m] <= A[r - 1]) {
                    return A[0];
                } else {
                    // 那么把[l, m]扔掉
                    // 保留[m + 1, r)
                    l = m + 1;
                }
            }
            // 如果A[m]在数组的右半部分
            else {
                // 那么分两种情况
                // 1. 如果还有数比A[m]小
                if (m > 0 && A[m - 1] < A[m]) {
                    // 那么把[m, r)扔掉
                    // 保留[l, m)
                    r = m;
                } else {
                    return A[m];
                }
            }
        }

        // 由于一直约束最小值在[l, r)范围里面
        // 那么最后最小值一定就是A[l]
        return A[l];
    }
};
// @lc code=end
