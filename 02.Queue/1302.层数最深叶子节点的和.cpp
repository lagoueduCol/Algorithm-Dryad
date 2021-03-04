/*
 * @lc app=leetcode.cn id=1302 lang=cpp
 *
 * [1302] 层数最深叶子节点的和
 *
 * https://leetcode-cn.com/problems/deepest-leaves-sum/description/
 *
 * algorithms
 * Medium (81.31%)
 * Likes:    47
 * Dislikes: 0
 * Total Accepted:    14.2K
 * Total Submissions: 17.4K
 * Testcase Example:  '[1,2,3,4,5,null,6,7,null,null,null,null,8]'
 *
 * 给你一棵二叉树，请你返回层数最深的叶子节点的和。
 * 
 * 
 * 
 * 示例：
 * 
 * 
 * 
 * 输入：root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
 * 输出：15
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 树中节点数目在 1 到 10^4 之间。
 * 每个节点的值在 1 到 100 之间。
 * 
 * 
 */

// @lc code=start
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
    int deepestLeavesSum(TreeNode* root) {
        queue<TreeNode *> Q;
        // 注意，入队的时候都不能为空
        if (root) {
            Q.push(root);
        }

        int ans = 0;

        while (!Q.empty()) {
            // 拿到当前层的结点个数
            int qSize = Q.size();

            int tmp = 0;

            // 依次取出当前层的结点
            while (qSize--) {
                // 照顺序取出当前层的结点
                auto cur = Q.front();
                Q.pop();
                // 把结点值放到当前层
                tmp += cur->val;

                // 取出下一层，并且放到队列中。
                if (cur->left) {
                    Q.push(cur->left);
                }
                if (cur->right) {
                    Q.push(cur->right);
                }
            }
            ans = tmp;
        }

        // 返回结果
        return ans;
    }
};
// @lc code=end

