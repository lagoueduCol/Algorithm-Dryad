/*
 * @lc app=leetcode.cn id=783 lang=cpp
 *
 * [783] 二叉搜索树节点最小距离
 *
 * https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes/description/
 *
 * algorithms
 * Easy (56.07%)
 * Likes:    106
 * Dislikes: 0
 * Total Accepted:    23.8K
 * Total Submissions: 42.5K
 * Testcase Example:  '[4,2,6,1,3,null,null]'
 *
 * 给定一个二叉搜索树的根节点 root，返回树中任意两节点的差的最小值。
 *
 *
 *
 * 示例：
 *
 * 输入: root = [4,2,6,1,3,null,null]
 * 输出: 1
 * 解释:
 * 注意，root是树节点对象(TreeNode object)，而不是数组。
 *
 * 给定的树 [4,2,6,1,3,null,null] 可表示为下图:
 *
 * ⁠         4
 * ⁠       /   \
 * ⁠     2      6
 * ⁠    / \
 * ⁠   1   3
 *
 * 最小的差值是 1, 它是节点1和节点2的差值, 也是节点3和节点2的差值。
 *
 *
 *
 * 注意：
 *
 *
 * 二叉树的大小范围在 2 到 100。
 * 二叉树总是有效的，每个节点的值都是整数，且不重复。
 * 本题与
 * 530：https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/
 * 相同
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
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */

class Solution {
  void midOrder(TreeNode *root, TreeNode *&pre, int &ans) {
    if (root) {
      midOrder(root->left, pre, ans);
      if (pre) {
        ans = min(ans, root->val - pre->val);
      }
      pre = root;
      midOrder(root->right, pre, ans);
    }
  }

 public:
  int minDiffInBST(TreeNode *root) {
    if (!root) {
      return 0;
    }
    // 思路，我们只需要中序遍历，然后拿到前后结点的差值，然后更新就可以了
    int ans = INT_MAX;
    TreeNode *pre = nullptr;

    midOrder(root, pre, ans);
    return ans;
  }
};
// @lc code=end
