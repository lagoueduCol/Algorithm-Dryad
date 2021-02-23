/*
 * @lc app=leetcode.cn id=136 lang=cpp
 *
 * [136] 只出现一次的数字
 */

// @lc code=start
class Solution {
   public:
    int singleNumber(vector<int>& A) {
        int ans = 0;
        for (auto& x : A) ans ^= x;
        return ans;
    }
};
// @lc code=end
