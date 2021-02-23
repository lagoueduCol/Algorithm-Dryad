/*
 *
 * [144] 二叉树的前序遍历
 *
 * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/description/
 * https://www.lintcode.com/problem/binary-tree-preorder-traversal/
 *
 * algorithms
 * Medium (66.89%)
 * Likes:    367
 * Dislikes: 0
 * Total Accepted:    170.7K
 * Total Submissions: 255.3K
 * Testcase Example:  '[1,null,2,3]'
 *
 * 给定一个二叉树，返回它的 前序 遍历。
 *
 * 示例:
 *
 * 输入: [1,null,2,3]
 * ⁠  1
 * ⁠   \
 * ⁠    2
 * ⁠   /
 * ⁠  3
 *
 * 输出: [1,2,3]
 *
 *
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 */

/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
    void preOrder(TreeNode *root, vector<int> &ans) {
        if (root) {
            ans.push_back(root->val);
            preOrder(root->left, ans);
            preOrder(root->right, ans);
        }
    }

   public:
    vector<int> preorderTraversal(TreeNode *root) {
        vector<int> ans;
        preOrder(root, ans);
        return ans;
    }
};
