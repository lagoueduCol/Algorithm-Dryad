/*
 * @lc app=leetcode.cn id=501 lang=cpp
 *
 * [501] 二叉搜索树中的众数
 *
 * https://leetcode-cn.com/problems/find-mode-in-binary-search-tree/description/
 *
 * algorithms
 * Easy (50.05%)
 * Likes:    279
 * Dislikes: 0
 * Total Accepted:    49.3K
 * Total Submissions: 98.4K
 * Testcase Example:  '[1,null,2,2]'
 *
 * 给定一个有相同值的二叉搜索树（BST），找出 BST
 * 中的所有众数（出现频率最高的元素）。
 *
 * 假定 BST 有如下定义：
 *
 *
 * 结点左子树中所含结点的值小于等于当前结点的值
 * 结点右子树中所含结点的值大于等于当前结点的值
 * 左子树和右子树都是二叉搜索树
 *
 *
 * 例如：
 * 给定 BST [1,null,2,2],
 *
 * ⁠  1
 * ⁠   \
 * ⁠    2
 * ⁠   /
 * ⁠  2
 *
 *
 * 返回[2].
 *
 * 提示：如果众数超过1个，不需考虑输出顺序
 *
 * 进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）
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
#include <stack>
#include <string>
#include <unordered_map>
#include <unordered_set>
#include <vector>

using namespace std;

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode() : val(0), left(nullptr), right(nullptr) {}
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
    TreeNode(int x, TreeNode *left, TreeNode *right)
        : val(x), left(left), right(right) {}
};

struct ListNode {
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
};

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
   public:
    vector<int> findMode(TreeNode *root) {
        vector<pair<int, int>> rec;

        auto append = [&](int v, int cnt) {
            if (cnt <= 0) {
                return;
            }
            if (rec.empty() || cnt > rec.back().second) {
                rec.clear();
                rec.push_back({v, cnt});
            } else if (cnt == rec.back().second) {
                rec.push_back({v, cnt});
            }
        };

        int preValue = 0;
        int preCnt = 0;
        stack<TreeNode *> t;
        while (root || !t.empty()) {
            while (root) {
                t.push(root);
                root = root->left;
            }

            root = t.top();
            t.pop();

            if (preValue == root->val) {
                preCnt++;
            } else {
                // 如果不相等，那么需要{preValue, preCnt}
                // 放到rec中
                append(preValue, preCnt);
                preValue = root->val;
                preCnt = 1;
            }

            root = root->right;
        }

        append(preValue, preCnt);

        vector<int> ans;
        for (auto &p : rec) ans.push_back(p.first);

        return ans;
    }
};
// @lc code=end
