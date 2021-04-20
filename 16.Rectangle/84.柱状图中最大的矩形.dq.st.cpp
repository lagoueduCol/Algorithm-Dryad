/*
 * @lc app=leetcode.cn id=84 lang=cpp
 *
 * [84] 柱状图中最大的矩形
 *
 * https://leetcode-cn.com/problems/largest-rectangle-in-histogram/description/
 *
 * algorithms
 * Hard (42.74%)
 * Likes:    1213
 * Dislikes: 0
 * Total Accepted:    126.1K
 * Total Submissions: 294.1K
 * Testcase Example:  '[2,1,5,6,2,3]'
 *
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * 
 * 
 * 
 * 
 * 
 * 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
 * 
 * 
 * 
 * 
 * 
 * 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
 * 
 * 
 * 
 * 示例:
 * 
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 * 
 */


#include <assert.h>
#include <limits.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include <algorithm>
#include <iostream>
#include <numeric>
#include <queue>
#include <string>
#include <unordered_map>
#include <unordered_set>
#include <vector>

using namespace std;


// @lc code=start
class Solution {
    int log2(int x) {
        return 31 - __builtin_clz((unsigned int)x);
    }

    void buildST(vector<int>& A, vector<vector<int>> &st) {
        const int N = A.size();
        // 第一步：
        //    - 处理长度为1的区间
        //      即[i, i + 1)
        //
        // 区间的表示：
        //      [start=i, len=2^0]
        //      也就是st[i][len=2^0]
        for (int i = 0; i < N; i++) {
            st[i][0] = i;
        }

        // 递推：
        //      依次处理2 ^ j长度。
        //      其中2 ^ j = 2 ^ (j-1) + 2 ^ (j-1)
        //      注意：这里的长度都是完整的2 ^ j的
        for (int j = 1; (1 << j) <= N; j++) {
            // 这里要处理的区间[i, i + (1<<j)]
            // last = i + (1<<j)
            // 根据左闭右开原则，last是可以取到n的。这点要注意。
            for (int i = 0; (i + (1 << j)) <= N; i++) {
                int left_range_min_index = st[i][j-1];
                int right_range_min_index = st[i+(1<<(j-1))][j-1];
                if (A[left_range_min_index] < A[right_range_min_index]) {
                    st[i][j] = left_range_min_index;
                } else {
                    st[i][j] = right_range_min_index;
                }
            }
        }
    }

    int queryST(vector<vector<int>> &st, vector<int> &A, int l, int r) {
        // 这里我们将区间[l, r]分为两个区间
        // [l, l+log2(len)] => [l, len=log2(len)]
        // [r-log2(len)+1, r] => [r-log2(len) + 1, len=log2(len)]
        int len = r - l + 1;
        int j = log2(len);
        int left_range_min_index = st[l][j];
        int right_range_min_index = st[r - (1 << j) + 1][j];
        if (A[left_range_min_index] < A[right_range_min_index]) {
            return left_range_min_index;
        }
        return right_range_min_index;
    }


    // 这里得到一个区域里面的最大矩形面积
    // 这个区间域为[b, e)
    // 注意e是取不到的
    int getRangeMaxArea(vector<int> &A, vector<vector<int>> &st, int b, int e) {
        // 如果为空区间
        if (b >= e) {
            return 0;
        }

        // 如果区间中只有一个元素
        if (b + 1 == e) {
            return A[b];
        }

        // 如果有多个元素。那么找到范围里面的最小值
        // 如果有多个最小值，那么我们就找离中心最近的那个，尽量把区域进行等分
        int minIndex = queryST(st, A, b, e-1);

        // 在使用 最小值 情况下的面积
        int useMinIndexArea = A[minIndex] * (e - b);

        // 不用 minIndex 那么就会把区间分为两部分
        int leftMaxArea = getRangeMaxArea(A, st, b, minIndex);
        int rightMaxArea = getRangeMaxArea(A, st, minIndex + 1, e);

        return max(useMinIndexArea, max(leftMaxArea, rightMaxArea));
    }

public:
    int largestRectangleArea(vector<int>& A) {
        const int N = A.size();
        vector<vector<int>> st(N, vector<int>(log2(N)+1));
        buildST(A, st);

        return getRangeMaxArea(A, st, 0, N);
    }
};
// @lc code=end



int main(void) {
    Solution s;
    vector<int> A{2,1,5,6,2,3};
    s.largestRectangleArea(A);
    return 0;
}