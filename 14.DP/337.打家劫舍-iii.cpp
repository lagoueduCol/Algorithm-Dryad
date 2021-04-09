/*
 * @lc app=leetcode.cn id=337 lang=cpp
 *
 * [337] 打家劫舍 III
 *
 * https://leetcode-cn.com/problems/house-robber-iii/description/
 *
 * algorithms
 * Medium (61.69%)
 * Likes:    798
 * Dislikes: 0
 * Total Accepted:    95.6K
 * Total Submissions: 154.9K
 * Testcase Example:  '[3,2,3,null,3,null,1]'
 *
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。
 * 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
 * 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 *
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 *
 * 示例 1:
 *
 * 输入: [3,2,3,null,3,null,1]
 *
 * ⁠    3
 * ⁠   / \
 * ⁠  2   3
 * ⁠   \   \
 * ⁠    3   1
 *
 * 输出: 7
 * 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
 *
 * 示例 2:
 *
 * 输入: [3,4,5,1,3,null,1]
 *
 * 3
 * ⁠   / \
 * ⁠  4   5
 * ⁠ / \   \
 * ⁠1   3   1
 *
 * 输出: 9
 * 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
 *
 *
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left),
 * right(right) {}
 * };
 */
class Solution {
  /**
   * 返回值分别表示:
   * first. 抢根结点的最大收益
   * second. 不抢根结点的最大收益
   */
  pair<int64_t, int64_t> postOrder(TreeNode* root) {
    // 如果是一棵空树，那么当然没有什么收益的
    if (!root) {
      return {0, 0};
    }

    auto l = postOrder(root->left);
    auto r = postOrder(root->right);

    // 抢当前根结点
    int64_t get = root->val;
    // 那么就需要两个子树的根点不能抢
    get += l.second + r.second;

    // 不抢当前结点
    int64_t skip = 0;
    // 那么就需要两个子树的根结点可以抢，或者可以不抢!
    skip = max(l.first, l.second) + max(r.first, r.second);

    return {get, skip};
  }

 public:
  int rob(TreeNode* root) {
    auto p = postOrder(root);
    return max(p.first, p.second);
  }
};
// @lc code=end
