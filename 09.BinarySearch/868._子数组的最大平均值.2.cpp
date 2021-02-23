/*

给定一个由n个整数组成的数组，找到给定长度k的连续子数组，该子数组具有最大平均值。你需要输出最大平均值。

样例

Example1

Input:  nums = [1,12,-5,-6,50,3] and k = 4
Output: 12.75
Explanation:
Maximum average is (12-5-6+50)/4 = 51/4 = 12.75


Example2
Input:  nums = [4,2,1,3,3] and k = 2
Output: 3.00
Explanation:
Maximum average is (3+3)/2 = 6/2 = 3.00


注意事项
1 <= k <= n <= 30,000.
给定数组的元素范围是[-10,000, 10,000]。
*/
// 测试平台： https://www.lintcode.com/problem/maximum-average-subarray/

class Solution {
public:
    // 求长度为k的连续子数组的最大和
    // 这里采用滑动窗口来求解
    int64_t maxSumInKLength(vector<int> &A, int k) {
        const int N = A.size();
        // 窗口长度为k的累计和
        int64_t s = 0;
        int64_t ans = INT_MIN;
        for (int i = 0; i < N; i++) {
            s += A[i];
            if (i < k - 1) {
                continue;
            }
            // 此时数据的范围是
            // [i + 1 - k, i + 1)
            // 长度为k
            ans = max(ans, s);
            
            // 为了滑动前进，需要将A[i + 1 - k]
            // 移出窗口
            s -= A[i + 1 - k];
        }
        // 返回最大值
        return ans;
    }

    double findMaxAverage(vector<int> &A, int k) {
        const int N = A.size();
        auto maxSum = maxSumInKLength(A, k);
        return (double)maxSum / (double)k;
    }
};
