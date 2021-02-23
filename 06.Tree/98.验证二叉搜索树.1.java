/*
 *
 * [98] 验证二叉搜索树
 *
 * https://leetcode-cn.com/problems/validate-binary-search-tree/description/
 * https://www.lintcode.com/problem/validate-binary-search-tree/
 *
 * algorithms
 * Medium (33.13%)
 * Likes:    876
 * Dislikes: 0
 * Total Accepted:    205.7K
 * Total Submissions: 621.1K
 * Testcase Example:  '[2,1,3]'
 *
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * 
 * 假设一个二叉搜索树具有如下特征：
 * 
 * 
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 
 * 
 * 示例 1:
 * 
 * 输入:
 * ⁠   2
 * ⁠  / \
 * ⁠ 1   3
 * 输出: true
 * 
 * 
 * 示例 2:
 * 
 * 输入:
 * ⁠   5
 * ⁠  / \
 * ⁠ 1   4
 * / \
 * 3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 * 根节点的值为 5 ，但是其右子节点值为 4 。
 * 
 * 
 */

/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode() {} TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left
 * = left; this.right = right; } }
 */
class Solution {
    private boolean ans = true;

    private void preOrder(TreeNode root, Long l, Long r) {
        // 1. 如果为空树
        // 2. 如果已经有结点不满足BST的要求了
        if (root == null || !ans) {
            return;
        }

        // 检查当前结点是不是在影子二叉树的区间里面
        // 这里相当于在检查两棵二叉树相同位置的结点
        if (!(l < root.val && root.val < r)) {
            ans = false;
            return;
        }

        // 这里同时遍历左子树，(l, root.val)就是影子二叉树的左子结点
        preOrder(root.left, l, Long.valueOf(root.val));
        // 这里同时遍历右子树，(root.val, r)就是影子二叉树的右子结点
        preOrder(root.right, Long.valueOf(root.val), r);
    }

    public boolean isValidBST(TreeNode root) {
        ans = true;
        preOrder(root, Long.MIN_VALUE, Long.MAX_VALUE);
        return ans;
    }
}
