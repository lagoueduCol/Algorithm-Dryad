/*
 * @lc app=leetcode.cn id=435 lang=cpp
 *
 * [435] 无重叠区间
 *
 * https://leetcode-cn.com/problems/non-overlapping-intervals/description/
 *
 * algorithms
 * Medium (49.26%)
 * Likes:    363
 * Dislikes: 0
 * Total Accepted:    58.6K
 * Total Submissions: 118.9K
 * Testcase Example:  '[[1,2]]'
 *
 * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
 *
 * 注意:
 *
 *
 * 可以认为区间的终点总是大于它的起点。
 * 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
 *
 *
 * 示例 1:
 *
 *
 * 输入: [ [1,2], [2,3], [3,4], [1,3] ]
 *
 * 输出: 1
 *
 * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
 *
 *
 * 示例 2:
 *
 *
 * 输入: [ [1,2], [1,2], [1,2] ]
 *
 * 输出: 2
 *
 * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
 *
 *
 * 示例 3:
 *
 *
 * 输入: [ [1,2], [2,3] ]
 *
 * 输出: 0
 *
 * 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
 *
 *
 */

// @lc code=start

class Solution {
   public:
    int eraseOverlapIntervals(vector<vector<int>>& A) {
        const int N = A.size();

        sort(A.begin(), A.end());

        int preStart = INT_MAX;
        int ans = 0;
        for (int i = N - 1; i >= 0; i--) {
            const int end = A[i][1];
            const int start = A[i][0];
            if (end <= preStart) {
                ans++;
                preStart = A[i][0];
            }
        }

        return N - ans;
    }
};

// @lc code=end
