import javax.swing.tree.TreeNode;

/*
 *
 * [112] 路径总和
 *
 * https://leetcode-cn.com/problems/path-sum/description/
 * https://www.lintcode.com/problem/path-sum/description
 *
 * algorithms
 * Easy (51.58%)
 * Likes:    491
 * Dislikes: 0
 * Total Accepted:    162.6K
 * Total Submissions: 315.2K
 * Testcase Example:  '[5,4,8,11,null,13,4,7,2,null,null,null,1]\n22'
 *
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 * 
 * 说明: 叶子节点是指没有子节点的节点。
 * 
 * 示例: 
 * 给定如下二叉树，以及目标和 sum = 22，
 * 
 * ⁠             5
 * ⁠            / \
 * ⁠           4   8
 * ⁠          /   / \
 * ⁠         11  13  4
 * ⁠        /  \      \
 * ⁠       7    2      1
 * 
 * 
 * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
 * 
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    // 是否存在路径和等于给定值
    boolean ans = false;

    // 采用前序遍历的方式
    private void preOrder(TreeNode root, int sum, int target) {
        if (root == null || ans) {
            return;
        }
        // 首先加上当前结点的值
        sum += root.val;

        // 检查叶子结点
        if (root.left == null && root.right == null) {
            // 如果相等
            if (sum == target) {
                ans = true;
            }
            return;
        }

        // 查看左子树
        preOrder(root.left, sum, target);
        // 查看右子树
        preOrder(root.right, sum, target);
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        // 检查二叉树
        preOrder(root, 0, sum);
        return ans;
    }
}

