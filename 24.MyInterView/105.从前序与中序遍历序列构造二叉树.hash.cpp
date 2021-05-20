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
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
    TreeNode *build_tree(const vector<int> &preorder,
                         int b,
                         int e,
                         const vector<int> &inorder,
                         int f,
                         int t,
                        unordered_map<int,int> &pos) {
        if (b >= e || f >= t) {
            return nullptr;
        }

        // 如果只有一个结点
        if (b + 1 == e) {
            assert(preorder[b] == inorder[f]);
            return new TreeNode(preorder[b]);
        }

        // 如果有多个结点，那么把树分为两半
        const int root_value = preorder[b];

        // 找到在中序中的位置
        const int root_pos = pos[root_value];

        // inorder左子树就是[f, root_pos) = left_len,
        // inorder右子树就是[root_pos + 1, t) = right_len
        // preorder左子树[b + 1, b + 1 + left_len)
        // preorder右子树[b + 1 + left_len, e)

        const int left_len = root_pos - f;
        const int right_pos = t - root_pos - 1;

        auto root = new TreeNode(root_value);

        // 构建左子树
        root->left = build_tree(preorder,
                                b + 1,
                                b + 1 + left_len,
                                inorder,
                                f,
                                root_pos,
                                pos);
        
        // 构建右子树 
        root->right = build_tree(preorder,
                                b + 1 + left_len,
                                e,
                                inorder,
                                root_pos + 1,
                                t,
                                pos);
        
        return root;
    }

public:
    TreeNode* buildTree(vector<int>& preorder, vector<int>& inorder) {
        const int N = preorder.size();

        if (N == 0) {
            return nullptr;
        }

        unordered_map<int,int> pos;
        for (int i = 0; i < inorder.size(); i++) {
            pos[inorder[i]] = i;
        }

        return build_tree(preorder, 0, N, inorder, 0, N, pos);
    }
};
// @lc code=end

