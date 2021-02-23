/*
 * @lc app=leetcode.cn id=136 lang=cpp
 *
 * [136] 只出现一次的数字
 *
 * https://leetcode-cn.com/problems/single-number/description/
 *
 * algorithms
 * Easy (70.74%)
 * Likes:    1674
 * Dislikes: 0
 * Total Accepted:    326.1K
 * Total Submissions: 461K
 * Testcase Example:  '[2,2,1]'
 *
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 
 * 说明：
 * 
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * 
 * 示例 1:
 * 
 * 输入: [2,2,1]
 * 输出: 1
 * 
 * 
 * 示例 2:
 * 
 * 输入: [4,1,2,1,2]
 * 输出: 4
 * 
 */

// @lc code=start
class Solution {
    int threeSplit(vector<int> &A, int b, int e) {
        if (b >= e) {
            return 0;
        }
        if (b + 1 == e) {
            return A[b];
        }

        const int m = b + ((e-b)>>1);
        const int x = A[m];
        int l = b, i = b, r = e - 1;

        while (i <= r) {
            if (A[i] < x) swap(A[l++], A[i++]);
            else if (A[i] == x) i++;
            else swap(A[r--], A[i]);
        }

        if (1 == (i-l)) return A[l];

        if ((l-b) & 0x01) return threeSplit(A, b, l);
        return threeSplit(A, i, e);
     }
public:
    int singleNumber(vector<int>& A) {
        return threeSplit(A, 0, A.size());
    }
};
// @lc code=end