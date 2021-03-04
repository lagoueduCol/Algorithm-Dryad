/*
 * @lc app=leetcode.cn id=103 lang=cpp
 *
 * [103] 二叉树的锯齿形层序遍历
 *
 * https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/description/
 *
 * algorithms
 * Medium (57.10%)
 * Likes:    402
 * Dislikes: 0
 * Total Accepted:    117.5K
 * Total Submissions: 205.7K
 * Testcase Example:  '[3,9,20,null,null,15,7]'
 *
 * 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * 
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 * 
 * 
 * ⁠   3
 * ⁠  / \
 * ⁠ 9  20
 * ⁠   /  \
 * ⁠  15   7
 * 
 * 
 * 返回锯齿形层序遍历如下：
 * 
 * 
 * [
 * ⁠ [3],
 * ⁠ [20,9],
 * ⁠ [15,7]
 * ]
 * 
 * 
 */

#include <assert.h>
#include <limits.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include <algorithm>
#include <iostream>
#include <numeric>
#include <queue>
#include <string>
#include <unordered_map>
#include <unordered_set>
#include <vector>

using namespace std;



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
public:
    vector<vector<int>> zigzagLevelOrder(TreeNode* root) {
       vector<vector<int>> ans;

        //  生成当前层
        vector<TreeNode*> cur;
        // 注意加入的条件，不能把空指针放进去
        if (root) {
            cur.push_back(root);
        }

        vector<TreeNode*> nextLevel;
        bool bRev = false;

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
            if (bRev) {
                reverse(ans.back().begin(), ans.back().end());
            }
            bRev = !bRev;

            // c++里面更迭的技巧
            // 把下一层更迭为当前层。
            cur.swap(nextLevel);
        }

        return ans;
    }
};
// @lc code=end

