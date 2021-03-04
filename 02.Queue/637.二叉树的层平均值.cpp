/*
 * @lc app=leetcode.cn id=637 lang=cpp
 *
 * [637] 二叉树的层平均值
 *
 * https://leetcode-cn.com/problems/average-of-levels-in-binary-tree/description/
 *
 * algorithms
 * Easy (68.78%)
 * Likes:    239
 * Dislikes: 0
 * Total Accepted:    56.4K
 * Total Submissions: 81.9K
 * Testcase Example:  '[3,9,20,15,7]'
 *
 * 给定一个非空二叉树, 返回一个由每层节点平均值组成的数组。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：
 * ⁠   3
 * ⁠  / \
 * ⁠ 9  20
 * ⁠   /  \
 * ⁠  15   7
 * 输出：[3, 14.5, 11]
 * 解释：
 * 第 0 层的平均值是 3 ,  第1层是 14.5 , 第2层是 11 。因此返回 [3, 14.5, 11] 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 节点值的范围在32位有符号整数范围内。
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
    vector<double> averageOfLevels(TreeNode* root) {
        queue<TreeNode *> Q;
        // 注意，入队的时候都不能为空
        if (root) {
            Q.push(root);
        }

        vector<double> ans;

        while (!Q.empty()) {
            // 拿到当前层的结点个数
            int qSize = Q.size();
            double tmp = 0;
            auto cnt = qSize;

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

            ans.push_back(tmp / cnt);
        }

        // 返回结果
        return ans;
    }
};
// @lc code=end

