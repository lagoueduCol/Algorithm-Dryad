/*
 *
 * [98] 验证二叉搜索树
 *
 * https://leetcode-cn.com/problems/validate-binary-search-tree/description/
 *
 * algorithms
 * Medium (33.21%)
 * Likes:    889
 * Dislikes: 0
 * Total Accepted:    209.4K
 * Total Submissions: 630.5K
 * Testcase Example:  '[2,1,3]'
 *
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * 
 * 假设一个二叉搜索树具有如下特征：
 * 
 * 
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 
 * 
 * 示例 1:
 * 
 * 输入:
 * ⁠   2
 * ⁠  / \
 * ⁠ 1   3
 * 输出: true
 * 
 * 
 * 示例 2:
 * 
 * 输入:
 * ⁠   5
 * ⁠  / \
 * ⁠ 1   4
 * / \
 * 3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 * 根节点的值为 5 ，但是其右子节点值为 4 。
 * 
 * 
 */

/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
    pair<int64_t, int64_t> postOrder(TreeNode *root, bool &ans) {
        if (!root || !ans) {
            return {INT64_MAX, INT64_MIN};
        }

        auto l = postOrder(root->left, ans);
        auto r = postOrder(root->right, ans);

        if (!(l.second < root->val && root->val < r.first)) {
            ans = false;
            return {INT64_MAX, INT64_MIN};
        }

        return {min<int64_t>(l.first, root->val), max<int64_t>(root->val, r.second)};
    }
public:
    bool isValidBST(TreeNode* root) {
        bool ans = true;
        postOrder(root, ans);
        return ans;
    }
};

