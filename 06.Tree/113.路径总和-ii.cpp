/*
 *
 * [113] 路径总和 II
 * 
 * https://www.lintcode.com/problem/path-sum-ii/description
 * https://leetcode-cn.com/problems/path-sum-ii/description/
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
    void backtrace(TreeNode *root, int64_t T, int64_t sum, vector<int> &path, vector<vector<int>> &ans) {
        if (!root) return;

        path.push_back(root->val);
        sum += root->val;

        if (!root->left && !root->right) {
            if (sum == T) {
                ans.push_back(path);
            } 
        } else {
            backtrace(root->left, T, sum, path, ans);
            backtrace(root->right, T, sum, path, ans);
        }

        path.pop_back();
    }
public:
    vector<vector<int>> pathSum(TreeNode* root, int sum) {
        if (!root) return {};
        vector<int> path;
        vector<vector<int>> ans;
        backtrace(root, sum, 0, path, ans);
        return ans;
    }
};

