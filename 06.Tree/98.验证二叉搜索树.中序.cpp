/*
 *
 * [98] 验证二叉搜索树
 *
 * https://leetcode-cn.com/problems/validate-binary-search-tree/description/
 * https://www.lintcode.com/problem/validate-binary-search-tree/
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
    void midOrder(TreeNode *root, bool &ans, int64_t &pre) {
        // 注意这里利用ans来进行快速返回
        // 一旦发现已经不是BST了，那么后面的遍历也没有必要继续往下走了
        if (root && ans) {
            midOrder(root->left, ans, pre);

            // 中序遍历的时候节点的处理
            // 利用前面结点的值pre来判断序列的有效性。
            if (pre >= root->val) {
                ans = false;
                return;
            }
            // 更新pre的值。
            pre = root->val;

            midOrder(root->right, ans, pre);
        }
    }
public:
    bool isValidBST(TreeNode* root) {
        bool ans = true;
        int64_t pre = INT64_MIN;
        midOrder(root, ans, pre);
        return ans;
    }
};

