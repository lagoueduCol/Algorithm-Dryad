/*
 * @lc app=leetcode.cn id=105 lang=cpp
 *
 * [105] 从前序与中序遍历序列构造二叉树
 *
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/
 *
 * algorithms
 * Medium (69.26%)
 * Likes:    931
 * Dislikes: 0
 * Total Accepted:    165.7K
 * Total Submissions: 238.9K
 * Testcase Example:  '[3,9,20,15,7]\n[9,3,15,20,7]'
 *
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 *
 * 返回如下的二叉树：
 *
 * ⁠   3
 * ⁠  / \
 * ⁠ 9  20
 * ⁠   /  \
 * ⁠  15   7
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
    // 注意这里的区间A数组是[b, e), B数组是[f, t)
    // 分别表示A数组[begin, end)
    // B数组的[from, to)
    TreeNode* buildTree(vector<int>& A, int b, int e, vector<int>& B, int f,
                        int t) {
        if (b >= e) return nullptr;
        if (b + 1 == e) return new TreeNode(A[b]);
        auto pos = find(B.begin() + f, B.begin() + t, A[b]);
        const int left_len = pos - (B.begin() + f);
        auto root = new TreeNode(A[b]);
        root->left = buildTree(A, b + 1, b + 1 + left_len, B, f, f + left_len);
        root->right = buildTree(A, b + 1 + left_len, e, B, f + left_len + 1, t);
        return root;
    }

   public:
    TreeNode* buildTree(vector<int>& A, vector<int>& B) {
        return buildTree(A, 0, A.size(), B, 0, B.size());
    }
};
// @lc code=end
