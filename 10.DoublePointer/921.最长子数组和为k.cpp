/*
911. 最大子数组之和为k
中文
English
给一个数组nums和目标值k，找到数组中最长的子数组，使其中的元素和为k。如果没有，则返回0。

样例
Example1

Input:  nums = [1, -1, 5, -2, 3], k = 3
Output: 4
Explanation:
because the subarray [1, -1, 5, -2] sums to 3 and is the longest.
Example2

Input: nums = [-2, -1, 2, 1], k = 1
Output: 2
Explanation:
because the subarray [-1, 2] sums to 1 and is the longest.
挑战
能否用O(n)的时间复杂度完成？

注意事项
数组之和保证在32位有符号整型数的范围内
*/

// 测试链接: https://www.lintcode.com/problem/maximum-size-subarray-sum-equals-k/leaderboard/

class Solution {
public:
    /**
     * @param nums: an array
     * @param k: a target value
     * @return: the maximum length of a subarray that sums to k
     */
    int maxSubArrayLen(vector<int> &A, int k) {
        // Write your code here
        const int N = A.size();
        unordered_map<int,int> H;
        H[0] = -1;
        int pre = 0;
        int ans = 0;
        for (int i = 0; i < N; i++) {
          const int c = A[i];
          pre += c;
          const int want = pre - k;
          auto iter = H.find(want);
          if (iter != H.end()) {
            ans = max(ans, i - iter->second);
          }

          iter = H.find(pre);
          if (iter == H.end()) {
            H.emplace(pre, i);
          }
        }

        return ans;
    }
};
