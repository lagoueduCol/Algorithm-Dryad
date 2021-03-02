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
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为
 * 1 。
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
#include <stack>
#include <string>
#include <unordered_map>
#include <unordered_set>
#include <vector>

using namespace std;

// @lc code=start

/**
 * 思路：
 *
 * 在选择的时候，由于要构造出最大的矩形。一种暴力的做法是：
 *
 * for (int i = 0; i < N; i++) {
 *     ans = max(ans, A[i]参与构造的最大矩形面积);
 * }
 *
 * 那么在求A[i]参与构造的最大矩形的时候。A[i]左边与右边的值
 * 肯定都要 >= A[i]才可以。
 *
 * 那么也就是说，我们需要找到左边第一个比A[i]小的数, leftPos
 * 也需要找到右边第一个比A[i]小的数。rightPos
 *
 * 那么前面暴力的代码就可以写成：
 *
 * for (int i = 0; i < N; i++) {
 *     leftPos = findLeftSmall(A[i]);
 *     rightPos = findRightSmall(A[i]);
 *     ans = max(ans, A[i] * (rightPos - leftPos - 1));
 * }
 *
 * 而leftPos和rightPos我们都可以先通过单调栈得到。那么到这里，问题就解决了。
 *
 *
 */

class LeftSmall {
    // 当我们要找左边比我小的元素的时候，需要用递增栈
   public:
    static vector<int> findLeftSmall(vector<int>& A) {
        if (A.empty()) {
            return {};
        }
        const int N = A.size();

        // 结果数组
        vector<int> ans(N);

        // 注意，栈中的元素记录的是下标
        stack<int> t;

        // 注意这里的遍历方向发生了变化，因为我们是要找到左边比我小的元素的位置
        for (int i = N - 1; i >= 0; i--) {
            const int x = A[i];
            // 每个元素都遍历栈中的元素完成消除动作
            // 这里是递减栈
            // 如果发现进来的元素x与栈中元素相比
            // 如果大于栈中的元素，那么要把栈中的元素弹出去
            while (!t.empty() && A[t.top()] > x) {
                // 消除的时候，记录一下被谁消除了
                ans[t.top()] = i;
                // 消除时候，值更大的需要从栈中消失
                t.pop();
            }
            // 剩下的入栈
            t.push(i);
        }
        // 栈中剩下的元素，由于没有人能消除他们，因此，只能将结果设置为-1。
        while (!t.empty()) {
            ans[t.top()] = -1;
            t.pop();
        }

        return ans;
    }
};

class RightSmall {
   public:
    static vector<int> findRightSmall(vector<int>& A) {
        if (A.empty()) {
            return {};
        }
        const int N = A.size();

        // 结果数组
        vector<int> ans(N);
        // 注意，栈中的元素记录的是下标
        stack<int> t;

        for (int i = 0; i < N; i++) {
            const int x = A[i];
            // 每个元素都向左遍历栈中的元素完成消除动作
            while (!t.empty() && A[t.top()] > x) {
                // 消除的时候，记录一下被谁消除了
                ans[t.top()] = i;
                // 消除时候，值更大的需要从栈中消失
                t.pop();
            }
            // 剩下的入栈
            t.push(i);
        }
        // 栈中剩下的元素，由于没有人能消除他们，因此，只能将结果设置为-1。
        while (!t.empty()) {
            ans[t.top()] = -1;
            t.pop();
        }

        return ans;
    }
};

class Solution {
   public:
    int largestRectangleArea(vector<int>& A) {
        if (A.empty()) {
            return 0;
        }

        const int N = A.size();

        auto leftSmall = LeftSmall::findLeftSmall(A);
        auto rightSmall = RightSmall::findRightSmall(A);

        int ans = 0;

        for (int i = 0; i < N; i++) {
            const int height = A[i];
            // 左边比我小的位置
            // 右边比我小的位置
            const int leftPos = leftSmall[i];
            const int rightPos = rightSmall[i] == -1 ? N : rightSmall[i];

            // 现在我们确定区间(leftPos, rightPos)
            // 注意两边都是开区间。在这个区间里面，所有的数肯定都是 >= A[i]的。
            // 那么底部的宽度就是
            const int width = rightPos - leftPos - 1;
            const int area = height * width;

            ans = max(ans, area);
        }

        return ans;
    }
};
// @lc code=end
