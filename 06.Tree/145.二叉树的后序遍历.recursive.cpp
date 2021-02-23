/*
 *
 * [145] 二叉树的后序遍历
 */

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
    void postOrder(TreeNode *root, vector<int> &ans) {
        if (root) {
            postOrder(root->left, ans);
            postOrder(root->right, ans);
            ans.push_back(root->val);
        }
    }
   public:
    vector<int> postorderTraversal(TreeNode *root) {
        vector<int> ans;
        postOrder(root, ans);
        return ans;
    }
};
