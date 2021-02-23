/*
 *
 * [700] 二叉搜索树中的搜索
 *
 * https://leetcode-cn.com/problems/search-in-a-binary-search-tree/description/
 *
 * algorithms
 * Easy (75.07%)
 * Likes:    107
 * Dislikes: 0
 * Total Accepted:    46.4K
 * Total Submissions: 61.8K
 * Testcase Example:  '[4,2,7,1,3]\n2'
 *
 * 给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 NULL。
 * 
 * 例如，
 * 
 * 
 * 给定二叉搜索树:
 * 
 * ⁠       4
 * ⁠      / \
 * ⁠     2   7
 * ⁠    / \
 * ⁠   1   3
 * 
 * 和值: 2
 * 
 * 
 * 你应该返回如下子树:
 * 
 * 
 * ⁠     2     
 * ⁠    / \   
 * ⁠   1   3
 * 
 * 
 * 在上述示例中，如果要找的值是 5，但因为没有节点值为 5，我们应该返回 NULL。
 * 
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        TreeNode p = root;
        while (p != null) {
            if (p.val == val) {
                return p;
            } else if (val < p.left) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return null;
    }
}

