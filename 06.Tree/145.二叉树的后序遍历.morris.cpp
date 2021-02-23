/*
 *
 * [145] 二叉树的后序遍历
 *
 * https://leetcode-cn.com/problems/binary-tree-postorder-traversal/description/
 *
 * algorithms
 * Medium (73.92%)
 * Likes:    507
 * Dislikes: 0
 * Total Accepted:    180.4K
 * Total Submissions: 244K
 * Testcase Example:  '[1,null,2,3]'
 *
 * 给定一个二叉树，返回它的 后序 遍历。
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
 * 输出: [3,2,1]
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
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left),
 * right(right) {}
 * };
 */
class Solution {
    TreeNode *reverse(TreeNode *head, TreeNode *tail) {
        TreeNode dummy;
        auto p = head;
        while (p != tail) {
            auto back = p->right;
            p->right = dummy.right;
            dummy.right = p;
            p = back;
        }
        return dummy.right;
    }

    void scan(TreeNode *head, vector<int> &ans) {
        auto p = head;
        while (p) {
            auto back = p->right;
            ans.push_back(p->val);
            p = back;
        }
    }

    void print(TreeNode *par, vector<int> &ans) {
        // a -> b -> c -> d -> a
        // head = b, last = a
        auto head = reverse(par->left, par);
        scan(head, ans);
        reverse(head, nullptr);
        head->right = par;
    }

   public:
    vector<int> postorderTraversal(TreeNode *root) {
        if (!root) {
            return {};
        }

        TreeNode dummy;
        dummy.left = root;

        auto cur = &dummy;
        vector<int> ans;

        while (cur) {
            if (cur->left) {
                auto pre = cur->left;
                while (pre->right && pre->right != cur) {
                    pre = pre->right;
                }
                // 这里左子树没有处理过
                if (!pre->right) {
                    pre->right = cur;
                    cur = cur->left;
                } else {
                    // 这里左子树已经处理过了
                    // 把最后的那一波一起带走
                    // pre->right这个时候就是父结点
                    print(cur, ans);
                    pre->right = nullptr;
                    cur = cur->right;
                }
            } else {
                // 没有左边的子树，直接处理右子树
                cur = cur->right;
            }
        }
        return ans;
    }
};


