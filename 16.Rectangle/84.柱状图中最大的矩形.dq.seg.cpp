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

    // 表示线段树的数组treeArray[]
    // 数组里面的值表示区间里面的最小值
    vector<int> treeArray;
    vector<int> heights;

    int leftNodePos(int rootPos) {
        return (rootPos << 1) + 1;
    }

    int rightNodePos(int rootPos) {
        return (rootPos << 1) + 2;
    }

    // treeArray[rootPos] 将会记录数组[start, end]
    // 这个区间上的信息。在本题中，信息为区间上的最小值
    void buildTree(int rootPos, int start, int end) {
        // 范围为空
        if (start > end)
            return;
        // 如果区间：只有一个数
        if (start == end) {
            treeArray[rootPos] = start;
        } else {
            // 否则需要将区间分为两半
            const int mid = start + ((end - start) >> 1);
            buildTree(leftNodePos(rootPos), start, mid);
            buildTree(rightNodePos(rootPos), mid + 1, end);
            // 构建成功之后，需要利用左子树的信息和右子树的信息来
            // 来更新 [start, end] rootNode 的信息
            const int leftRangeMinIndex = treeArray[leftNodePos(rootPos)];
            const int rightRangeMinIndex = treeArray[rightNodePos(rootPos)];

            if (heights[leftRangeMinIndex] < heights[rightRangeMinIndex]) {
                treeArray[rootPos] = leftRangeMinIndex;
            } else {
                treeArray[rootPos] = rightRangeMinIndex;
            }
        }
    }

    /**
     * 查询区间[queryStart, queryEnd]这个区间上的最小值信息
     *
     * treeArray[rootPos]表示区间 [start, end]上的最小值。
     * 可以把前面的三个参数看成
     * class TreeNode {
     *      int val;        <-- arg: treeArray[rootPos];
     *      int rangeStart; <-- arg: start
     *      int rangeEnd:   <-- arg: end
     *      TreeNode left;  <-- leftNodePos(rootPos);
     *      TreeNode right: <-- rightNodePos(rootPos);
     * }
     */
    int queryTree(int rootPos, int start, int end, int queryStart, int queryEnd) {
        // 无效区间，返回一个queryStart下标
        if (start > end || queryStart > queryEnd) {
            return queryStart;
        }
        // 原则1： 包含于查询区间内部
        if (queryStart <= start && end <= queryEnd) {
            return treeArray[rootPos];
        }
        // 原则2：不相交时，放弃区间信息，这里我们返回最大值
        if (end < queryStart || queryEnd < start) {
            return queryStart;
        }
        // 原则3：当相交的时候，需要将[start, end]进行拆分
        // 由于我们建树的时候，都是平分，所以这里将区间也进行平分
        const int mid = start + ((end - start) >> 1);
        const int leftRangeMinIndex = queryTree(leftNodePos(rootPos), start, mid, queryStart, queryEnd);
        const int rightRangeMinIndex = queryTree(rightNodePos(rootPos), mid + 1, end, queryStart, queryEnd);
        if (heights[leftRangeMinIndex] < heights[rightRangeMinIndex]) {
            return leftRangeMinIndex;
        }
        return rightRangeMinIndex;
    }

    // 当我们要更新数组中A[inx] = value的时候
    // 线段树中存储的区间的信息，也是需要更新的
    // 当然，这个函数在这里并没有使用到。
    void updateTree(int rootPos, int start, int end, int idx, int value) {
        // 如果树中的结点不在我们的更新路径上
        if (start > end || idx < start || idx > end) {
            return;
        }
        // 如果已经找到了叶子结点
        if (start == idx && idx == end) {
            treeArray[rootPos] = idx;
            heights[idx] = value;
            return;
        }
        // 这里后序遍历
        // 如果是非叶子结点，那么
        // 先更新左右子结点，再更新根结点
        const int mid = start + ((end - start) >> 1);
        // 更新左子树
        updateTree(leftNodePos(rootPos), start, mid, idx, value);
        // 更新右子树
        updateTree(rightNodePos(rootPos), mid + 1, end, idx, value);
        // 更新根结点
        // 来更新 [start, end] rootNode 的信息
        const int leftRangeMinIndex = treeArray[leftNodePos(rootPos)];
        const int rightRangeMinIndex = treeArray[rightNodePos(rootPos)];
        if (heights[leftRangeMinIndex] < heights[rightRangeMinIndex]) {
            treeArray[rootPos] = leftRangeMinIndex;
        } else {
            treeArray[rootPos] = rightRangeMinIndex;
        }
    }

    // 这里得到一个区域里面的最大矩形面积
    // 这个区间域为[b, e)
    // 注意e是取不到的
    int getRangeMaxArea(int b, int e) {
        const int N = heights.size();
        // 如果为空区间
        if (b >= e) {
            return 0;
        }

        // 如果区间中只有一个元素
        if (b + 1 == e) {
            return heights[b];
        }

        // 如果有多个元素。那么找到范围里面的最小值
        // 如果有多个最小值，那么我们就找离中心最近的那个，尽量把区域进行等分
        int minIndex = queryTree(0/*rootPos*/, 0/*start*/, N-1/*end*/, b, e-1);

        // 在使用 最小值 情况下的面积
        int useMinIndexArea = heights[minIndex] * (e - b);

        // 不用 minIndex 那么就会把区间分为两部分
        int leftMaxArea = getRangeMaxArea(b, minIndex);
        int rightMaxArea = getRangeMaxArea(minIndex + 1, e);

        return max(useMinIndexArea, max(leftMaxArea, rightMaxArea));
    }
public:
    int largestRectangleArea(vector<int>& heights) {
        const int N = heights.size();
        this->heights.swap(heights);

        treeArray.resize(N<<2);

        // 在使用segment tree的时候，区间都是[begin, end]两边都可以取到
        buildTree(0, 0, N - 1);

        return getRangeMaxArea(0, N);
    }
};
// @lc code=end


int main(void) {
    vector<int> A{2,1,5,6,2,3};
    Solution s;
    std::cout << s.largestRectangleArea(A) << std::endl;
    return 0;
}