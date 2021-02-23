/*
 *
 * [1642] 可以到达的最远建筑
 *
 * https://leetcode-cn.com/problems/furthest-building-you-can-reach/description/
 *
 * algorithms
 * Medium (47.92%)
 * Likes:    33
 * Dislikes: 0
 * Total Accepted:    4K
 * Total Submissions: 8.3K
 * Testcase Example:  '[4,2,7,6,9,14,12]\n5\n1'
 *
 * 给你一个整数数组 heights ，表示建筑物的高度。另有一些砖块 bricks 和梯子
 * ladders 。
 *
 * 你从建筑物 0 开始旅程，不断向后面的建筑物移动，期间可能会用到砖块或梯子。
 *
 * 当从建筑物 i 移动到建筑物 i+1（下标 从 0 开始 ）时：
 *
 *
 * 如果当前建筑物的高度 大于或等于 下一建筑物的高度，则不需要梯子或砖块
 * 如果当前建筑的高度 小于 下一个建筑的高度，您可以使用 一架梯子 或 (h[i+1] -
 * h[i]) 个砖块
 *
 * 如果以最佳方式使用给定的梯子和砖块，返回你可以到达的最远建筑物的下标（下标 从
 * 0 开始 ）。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：heights = [4,2,7,6,9,14,12], bricks = 5, ladders = 1
 * 输出：4
 * 解释：从建筑物 0 出发，你可以按此方案完成旅程：
 * - 不使用砖块或梯子到达建筑物 1 ，因为 4 >= 2
 * - 使用 5 个砖块到达建筑物 2 。你必须使用砖块或梯子，因为 2 < 7
 * - 不使用砖块或梯子到达建筑物 3 ，因为 7 >= 6
 * - 使用唯一的梯子到达建筑物 4 。你必须使用砖块或梯子，因为 6 < 9
 * 无法越过建筑物 4 ，因为没有更多砖块或梯子。
 *
 *
 * 示例 2：
 *
 *
 * 输入：heights = [4,12,2,7,3,18,20,3,19], bricks = 10, ladders = 2
 * 输出：7
 *
 *
 * 示例 3：
 *
 *
 * 输入：heights = [14,3,19,3], bricks = 17, ladders = 0
 * 输出：3
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1
 * 1
 * 0
 * 0
 *
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

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode() : val(0), left(nullptr), right(nullptr) {}
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
    TreeNode(int x, TreeNode *left, TreeNode *right)
        : val(x), left(left), right(right) {}
};

struct ListNode {
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
};

class Solution {
   public:
    int furthestBuilding(vector<int> &heights, int bricks, int ladders) {
        // 注意处理非法输入
        if (heights.empty()) {
            return -1;
        }

        // 记录落并的小本子: 最大堆
        priority_queue<int> Q;
        int qSum = 0;

        int lastPos = 0;
        int preHeight = heights[0];

        for (int i = 1; i < heights.size(); i++) {
            const int curHeight = heights[i];

            // 如果是从高处往低处跳
            if (preHeight >= curHeight) {
                lastPos = i;
            } else {
                // 如果是低处往高处跳
                const int delta = curHeight - preHeight;
                // 拿到高度的落差
                // 写到小本子上
                Q.push(delta);
                qSum += delta;

                // 如果小本子上的总和比砖块多了
                // 并且还有梯子
                while (qSum > bricks && ladders > 0) {
                    // 需要用梯子把最大的落差给抵消掉
                    qSum -= Q.top();
                    std::cout << Q.top() << std::endl;
                    Q.pop();
                    ladders--;
                }
                // 看看经过一翻处理之后，还能不能跳到i这个位置
                if (qSum <= bricks) {
                    lastPos = i;
                } else {
                    // 如果经过一翻处理之后，还是不能跳
                    // 看来不能跳到i了
                    // 跳出循环!
                    break;
                }
            }

            // 更新位置的高度
            preHeight = curHeight;
        }

        return lastPos;
    }
};

int main(void) {
    Solution s;
    vector<int> A{4, 12, 2, 7, 3, 18, 20, 3, 19};
    int bricks = 10;
    int ladders = 2;

    std::cout << s.furthestBuilding(A, bricks, ladders) << std::endl;
    return 0;
}
