/*
 *
 * [112] 路径总和
 *
 * https://leetcode-cn.com/problems/path-sum/description/
 * https://www.lintcode.com/problem/path-sum/description
 *
 * algorithms
 * Easy (51.59%)
 * Likes:    491
 * Dislikes: 0
 * Total Accepted:    162.8K
 * Total Submissions: 315.6K
 * Testcase Example:  '[5,4,8,11,null,13,4,7,2,null,null,null,1]\n22'
 *
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 * 
 * 说明: 叶子节点是指没有子节点的节点。
 * 
 * 示例: 
 * 给定如下二叉树，以及目标和 sum = 22，
 * 
 * ⁠             5
 * ⁠            / \
 * ⁠           4   8
 * ⁠          /   / \
 * ⁠         11  13  4
 * ⁠        /  \      \
 * ⁠       7    2      1
 * 
 * 
 * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
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
    void preOrder(TreeNode *root, int sum, int target, bool &ans) {
        // 如果是空树，或者已经找到了想要的和
        if (!root || ans) {
            return;
        }

        // 把当前结点加上去
        sum += root->val;
        // 如果是叶子结点，那么看一下路径和是不是相等
        if (!root->left && !root->right) {
            if (sum == target) {
                ans = true;
                return;
            }
        }

        // 再看左子结点，再看右子结点
        preOrder(root->left, sum, target, ans);
        preOrder(root->right, sum, target, ans);
    }
public:
    bool hasPathSum(TreeNode* root, int sum) {
        bool ans = false;
        preOrder(root, 0, sum, ans);
        return ans;
    }
};



