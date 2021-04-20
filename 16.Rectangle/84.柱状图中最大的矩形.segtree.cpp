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

// @lc code=start

/**
 *  注意：线段树中所有的区间都是两边闭合的。[b, e]表示[b]， [e]都可以访问。
 */

class Solution {
    // 表示线段树的数组treeArray[]
    // 数组里面的值表示区间里面的最小值
    vector<int> treeArray;

    int leftNodePos(int rootPos) {
        return (rootPos << 1) + 1;
    }

    int rightNodePos(int rootPos) {
        return (rootPos << 1) + 2;
    }

    // treeArray[rootPos] 将会记录数组[start, end]
    // 这个区间上的信息。在本题中，信息为区间上的最小值
    void buildTree(int rootPos, vector<int> &A, int start, int end) {
        // 范围为空
        if (start > end)
            return;
        // 如果区间：只有一个数
        if (start == end) {
            treeArray[rootPos] = A[start];
        } else {
            // 否则需要将区间分为两半
            const int mid = start + ((end - start) >> 1);
            buildTree(leftNodePos(rootPos), A, start, mid);
            buildTree(rightNodePos(rootPos), A, mid + 1, end);
            // 构建成功之后，需要利用左子树的信息和右子树的信息来
            // 来更新 [start, end] rootNode 的信息
            treeArray[rootPos] =
                min(treeArray[leftNodePos(rootPos)], treeArray[rightNodePos(rootPos)]);
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
        // 无效区间，反回最大值
        if (start > end || queryStart > queryEnd) {
            return INT_MAX;
        }
        // 原则1： 包含于查询区间内部
        if (queryStart <= start && end <= queryEnd) {
            return treeArray[rootPos];
        }
        // 原则2：不相交时，放弃区间信息，这里我们返回最大值
        if (end < queryStart || queryEnd < start) {
            return INT_MAX;
        }
        // 原则3：当相交的时候，需要将[start, end]进行拆分
        // 由于我们建树的时候，都是平分，所以这里将区间也进行平分
        const int mid = start + ((end - start) >> 1);
        return min(queryTree(leftNodePos(rootPos), start, mid, queryStart, queryEnd),
            queryTree(rightNodePos(rootPos), mid + 1, end, queryStart, queryEnd));
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
            treeArray[rootPos] = value;
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
        treeArray[rootPos] =
            min(treeArray[leftNodePos(rootPos)], treeArray[rightNodePos(rootPos)]);
    }

public:
    int largestRectangleArea(vector<int>& A) {
        const int N = A.size();
        int ans = 0;

        treeArray.resize(N << 2);
        buildTree(0, A, 0, N - 1);

        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                // rootPos = 0表示根结点
                // [0, N-1]表示根结点代表：[0, N-1]这个区间上的最小值信息
                // [i, j]是我们的查询区间
                ans = max(ans, queryTree(0/*rootPos*/, 0, N - 1, i, j) * (j - i + 1));
            }
        }
        return ans;
    }
};
// @lc code=end

