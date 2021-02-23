/*
 * @lc app=leetcode.cn id=33 lang=cpp
 *
 * [33] 搜索旋转排序数组
 */

// @lc code=start
class Solution {
   public:
    int search(vector<int>& A, int T) {
        const int N = A.size();
        int l = 0, r = N;

        while (l < r) {
            const int m = l + ((r - l) >> 1);

            if (A[l] == T) return l;
            if (A[m] == T) return m;
            if (A[r - 1] == T) return r - 1;

            if (A[l] < A[m]) {
                if (A[l] < T && T < A[m]) {
                    r = m;
                } else {
                    l = m + 1;
                }
            } else {
                if (A[m] < T && T < A[r - 1]) {
                    l = m + 1;
                } else {
                    r = m;
                }
            }
        }

        return -1;
    }
};
// @lc code=end
