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
    vector<vector<int>> levelOrder(TreeNode *root) {
        queue<TreeNode *> Q;
        // 注意，入队的时候都不能为空
        if (root) {
            Q.push(root);
        }

        vector<vector<int>> ans;

        while (!Q.empty()) {
            // 生成新的一层
            ans.emplace_back();
            // 拿到当前层的结点个数
            int qSize = Q.size();

            // 依次取出当前层的结点
            while (qSize--) {
                // 照顺序取出当前层的结点
                auto cur = Q.front();
                Q.pop();
                // 把结点值放到当前层
                ans.back().push_back(cur->val);

                // 取出下一层，并且放到队列中。
                if (cur->left) {
                    Q.push(cur->left);
                }
                if (cur->right) {
                    Q.push(cur->right);
                }
            }
        }

        // 返回结果
        return ans;
    }
};
