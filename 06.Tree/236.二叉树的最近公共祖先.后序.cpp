/*
 * @lc app=leetcode.cn id=236 lang=cpp
 *
 * [236] 二叉树的最近公共祖先
 *
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/description/
 *
 * algorithms
 * Medium (66.17%)
 * Likes:    906
 * Dislikes: 0
 * Total Accepted:    152.5K
 * Total Submissions: 230.5K
 * Testcase Example:  '[3,5,1,6,2,0,8,null,null,7,4]\n5\n1'
 *
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点
 * p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x
 * 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
 *
 *
 *
 *
 *
 * 示例 1:
 *
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出: 3
 * 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
 *
 *
 * 示例 2:
 *
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出: 5
 * 解释: 节点 5 和节点 4 的最近公共祖先是节点
 * 5。因为根据定义最近公共祖先节点可以为节点本身。
 *
 *
 *
 *
 * 说明:
 *
 *
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉树中。
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
  int postOrder(TreeNode *root, TreeNode *p, TreeNode *q, TreeNode *&ans) {
    if (!root) {
      return 0;
    }
    // 去拿子树的信息
    int lcnt = postOrder(root->left, p, q, ans);
    int rcnt = postOrder(root->right, p, q, ans);
    // 利用子树的信息，得到我们最终想要的结论
    if (lcnt == 1 && rcnt == 1) {
      ans = root;
    } else if (1 == (lcnt + rcnt) && (root == p || root == q)) {
      ans = root;
    }
    // 返回当前这棵树的信息。
    return lcnt + rcnt + ((root == p || root == q) ? 1 : 0);
  }

public:
  TreeNode *lowestCommonAncestor(TreeNode *root, TreeNode *p, TreeNode *q) {
    TreeNode *ans = nullptr;
    postOrder(root, p, q, ans);
    return ans;
  }
};
// @lc code=end
