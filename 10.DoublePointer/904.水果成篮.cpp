/*
 * @lc app=leetcode.cn id=904 lang=cpp
 *
 * [904] 水果成篮
 *
 * https://leetcode-cn.com/problems/fruit-into-baskets/description/
 *
 * algorithms
 * Medium (43.31%)
 * Likes:    71
 * Dislikes: 0
 * Total Accepted:    10.5K
 * Total Submissions: 24.3K
 * Testcase Example:  '[1,2,1]'
 *
 * 在一排树中，第 i 棵树产生 tree[i] 型的水果。
 * 你可以从你选择的任何树开始，然后重复执行以下步骤：
 *
 *
 * 把这棵树上的水果放进你的篮子里。如果你做不到，就停下来。
 * 移动到当前树右侧的下一棵树。如果右边没有树，就停下来。
 *
 *
 * 请注意，在选择一颗树后，你没有任何选择：你必须执行步骤 1，然后执行步骤
 * 2，然后返回步骤 1，然后执行步骤 2，依此类推，直至停止。
 *
 * 你有两个篮子，每个篮子可以携带任何数量的水果，但你希望每个篮子只携带一种类型的水果。
 *
 * 用这个程序你能收集的水果树的最大总量是多少？
 *
 *
 *
 * 示例 1：
 *
 * 输入：[1,2,1]
 * 输出：3
 * 解释：我们可以收集 [1,2,1]。
 *
 *
 * 示例 2：
 *
 * 输入：[0,1,2,2]
 * 输出：3
 * 解释：我们可以收集 [1,2,2]
 * 如果我们从第一棵树开始，我们将只能收集到 [0, 1]。
 *
 *
 * 示例 3：
 *
 * 输入：[1,2,3,2,2]
 * 输出：4
 * 解释：我们可以收集 [2,3,2,2]
 * 如果我们从第一棵树开始，我们将只能收集到 [1, 2]。
 *
 *
 * 示例 4：
 *
 * 输入：[3,3,3,1,2,1,1,2,3,3,4]
 * 输出：5
 * 解释：我们可以收集 [1,2,1,1,2]
 * 如果我们从第一棵树或第八棵树开始，我们将只能收集到 4 棵水果树。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= tree.length <= 40000
 * 0 <= tree[i] < tree.length
 *
 *
 */

#include <bits/stdc++.h>

// @lc code=start
class Solution {
   public:
    int totalFruit(vector<int>& A) {
        // 记录每种数字的个数
        unordered_map<int, int> H;

        const int N = A.size();
        int left = -1;
        int ans = 0;

        for (int i = 0; i < N; i++) {
            auto x = A[i];
            H[x]++;

            // 如果当前x进来，如果进来之后，种类超过了2种
            // 那么就需要移动左指针
            while (H.size() > 2) {
                // 移动左指针，并且改变区间的状态
                const int old = A[++left];
                H[old]--;
                if (H[old] == 0) {
                    H.erase(old);
                }
            }

            // 到这里，区间肯定已经满足状态了。
            ans = max(ans, i - left);
        }

        return ans;
    }
};
// @lc code=end
