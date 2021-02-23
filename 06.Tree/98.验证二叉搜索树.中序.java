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
    boolean ans = true;
    Long preValue = Long.MIN_VALUE;
    private void midOrder(TreeNode root) {
        if (!ans) {
            return;
        }

        if (root != null) {
            midOrder(root.left);

            // 只需要在中序遍历的时候，
            // 与前面的值进行一下比较就可以了。
            if (preValue >= root.val) {
                ans = false;
                return;
            }
            preValue = Long.valueOf(root.val);

            midOrder(root.right);
        }
    }
    public boolean isValidBST(TreeNode root) {
        ans = true;
        // 注意，为了防止root.val取到最小值INT_MIN
        // 这里需要初始化为64位的最小值。
        preValue = Long.MIN_VALUE;
        midOrder(root);
        return ans;
    }
}

