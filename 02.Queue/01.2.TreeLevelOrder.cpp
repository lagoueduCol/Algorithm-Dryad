// 本题测试平台链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal/

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
public:
    vector<vector<int>> levelOrder(TreeNode* root) {
        vector<vector<int>> ans;

        //  生成当前层
        vector<TreeNode*> cur;
        // 注意加入的条件，不能把空指针放进去
        if (root) {
            cur.push_back(root);
        }

        vector<TreeNode*> nextLevel;

        while (!cur.empty()) {
            // 一定要记得把下一层清空
            nextLevel.clear();

            // 在结果层中新生成一层，ans.back()专门用来存放结果
            ans.emplace_back();

            for (auto c: cur) {
                // 存放当前层的结果
                ans.back().push_back(c->val);

                // 专门用来存放下一层结果
                if (c->left) {
                    nextLevel.push_back(c->left);
                }
                if (c->right) {
                    nextLevel.push_back(c->right);
                }
            }

            // c++里面更迭的技巧
            // 把下一层更迭为当前层。
            cur.swap(nextLevel);
        }

        return ans;
    }
};
