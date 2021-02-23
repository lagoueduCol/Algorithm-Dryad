/*
 * @lc app=leetcode.cn id=53 lang=cpp
 *
 * [53] 最大子序和
 */

#include <vector>
using namespace std;

// @lc code=start
class Solution {
   public:
    int maxSubArray(vector<int>& A) {
        const int N = A.size();
        if (N <= 0) {
            return 0;
        }

        int left = -1;
        int s = 0;
        int ans = INT_MIN;

        int max_item = *max_element(A.begin(), A.end());
        if (max_item < 0) {
            return max_item;
        }

        for (int i = 0; i < N; i++) {
            // 将A[i]添加到区间中
            s += A[i];

            // 如果当前的区间的状态被破坏了
            if (s <= 0) {
                // 直接把当前区间扔掉
                left = i;
                s = 0;
            }

            // 现在的区间是以A[i]为右端点能够取得的最大
            // 值
            ans = max(ans, s);
        }

        return ans;
    }
};
// @lc code=end
