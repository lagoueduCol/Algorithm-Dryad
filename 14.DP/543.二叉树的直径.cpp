/*
 * @lc app=leetcode.cn id=543 lang=cpp
 *
 * [543] 二叉树的直径
 *
 * https://leetcode-cn.com/problems/diameter-of-binary-tree/description/
 *
 * algorithms
 * Easy (52.95%)
 * Likes:    671
 * Dislikes: 0
 * Total Accepted:    106.4K
 * Total Submissions: 201K
 * Testcase Example:  '[1,2,3,4,5]'
 *
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 *
 *
 *
 * 示例 :
 * 给定二叉树
 *
 * ⁠         1
 * ⁠        / \
 * ⁠       2   3
 * ⁠      / \
 * ⁠     4   5
 *
 *
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 *
 *
 *
 * 注意：两结点之间的路径长度是以它们之间边的数目表示。
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
  // 项庄舞剑，这里返回值表示的是子树中最长路径中结点的数目
  // ans表示是最长的直径
  int postOrder(TreeNode *root, int &ans) {
    if (!root) {
      return 0;
    }

    auto l = postOrder(root->left, ans);
    auto r = postOrder(root->right, ans);

    ans = max(ans, l + r + 1);

    return max(l, r) + 1;
  }

 public:
  int diameterOfBinaryTree(TreeNode *root) {
    int ans = 1;
    postOrder(root, ans);
    return ans - 1;
  }
};
// @lc code=end
