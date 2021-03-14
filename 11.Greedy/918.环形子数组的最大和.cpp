/*
 * @lc app=leetcode.cn id=918 lang=cpp
 *
 * [918] 环形子数组的最大和
 *
 * https://leetcode-cn.com/problems/maximum-sum-circular-subarray/description/
 *
 * algorithms
 * Medium (34.43%)
 * Likes:    145
 * Dislikes: 0
 * Total Accepted:    7.5K
 * Total Submissions: 21.8K
 * Testcase Example:  '[1,-2,3,-2]'
 *
 * 给定一个由整数数组 A 表示的环形数组 C，求 C 的非空子数组的最大可能和。
 * 
 * 在此处，环形数组意味着数组的末端将会与开头相连呈环状。（形式上，当0 <= i < A.length 时 C[i] = A[i]，且当 i >= 0 时
 * C[i+A.length] = C[i]）
 * 
 * 此外，子数组最多只能包含固定缓冲区 A 中的每个元素一次。（形式上，对于子数组 C[i], C[i+1], ..., C[j]，不存在 i <= k1,
 * k2 <= j 其中 k1 % A.length = k2 % A.length）
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：[1,-2,3,-2]
 * 输出：3
 * 解释：从子数组 [3] 得到最大和 3
 * 
 * 
 * 示例 2：
 * 
 * 输入：[5,-3,5]
 * 输出：10
 * 解释：从子数组 [5,5] 得到最大和 5 + 5 = 10
 * 
 * 
 * 示例 3：
 * 
 * 输入：[3,-1,2,-1]
 * 输出：4
 * 解释：从子数组 [2,-1,3] 得到最大和 2 + (-1) + 3 = 4
 * 
 * 
 * 示例 4：
 * 
 * 输入：[3,-2,2,-3]
 * 输出：3
 * 解释：从子数组 [3] 和 [3,-2,2] 都可以得到最大和 3
 * 
 * 
 * 示例 5：
 * 
 * 输入：[-2,-3,-1]
 * 输出：-1
 * 解释：从子数组 [-1] 得到最大和 -1
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * -30000 <= A[i] <= 30000
 * 1 <= A.length <= 30000
 * 
 * 
 */

// @lc code=start
class Solution {
    pair<int,int> getMin(vector<int> &A) {
        const int N = A.size();
        int64_t tmp = 0;
        int tmp_start = 0;
        int tmp_end = 0;

        int64_t ans = 0;
        int start = 0;
        int end = 0;

        for (int i = 0; i < N; i++) {
            if (tmp >= 0) {
                tmp_start = i;
                tmp = 0;
            }
            tmp += A[i];
            tmp_end = i;
            if (tmp < ans) {
                ans = tmp;
                start = tmp_start;
                end = tmp_end;
            }
        }

        return {start, end};
    }

    pair<int,int> getMax(vector<int> &A) {
        const int N = A.size();
        int64_t tmp = 0;
        int tmp_start = 0;
        int tmp_end = 0;

        int64_t ans = 0;
        int start = 0;
        int end = 0;

        for (int i = 0; i < N; i++) {
            if (tmp < 0) {
                tmp_start = i;
                tmp = 0;
            }
            tmp += A[i];
            tmp_end = i;
            if (tmp > ans) {
                ans = tmp;
                start = tmp_start;
                end = tmp_end;
            }
        }

        return {start, end};
    }
public:
    int maxSubarraySumCircular(vector<int>& A) {
        const int N = A.size();

        if (N <= 0) {
            return 0;
        }

        int maxValue = *max_element(A.begin(), A.end());
        if (maxValue < 0) {
            return maxValue;
        }

        int64_t s = 0;
        for (auto &x: A) s += x;

        auto small = getMin(A);
        int64_t smallSum = 0;
        for (int i = small.first; i <= small.second; i++) {
            smallSum += A[i];
        }

        int64_t largeSum = 0;
        auto large = getMax(A);
        for (int i = large.first; i <= large.second; i++) {
            largeSum += A[i];
        }

        if (s - smallSum > largeSum) {
            return s - smallSum;
        }
        return largeSum;
    }
};

// @lc code=end

