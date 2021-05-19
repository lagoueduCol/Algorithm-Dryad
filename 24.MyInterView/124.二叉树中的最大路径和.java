/*
 * @lc app=leetcode.cn id=124 lang=java
 *
 * [124] 二叉树中的最大路径和
 *
 * https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/description/
 *
 * algorithms
 * Hard (43.59%)
 * Likes:    1044
 * Dislikes: 0
 * Total Accepted:    121.5K
 * Total Submissions: 278K
 * Testcase Example:  '[1,2,3]'
 *
 * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个
 * 节点，且不一定经过根节点。
 * 
 * 路径和 是路径中各节点值的总和。
 * 
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：root = [1,2,3]
 * 输出：6
 * 解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
 * 
 * 示例 2：
 * 
 * 
 * 输入：root = [-10,9,20,null,null,15,7]
 * 输出：42
 * 解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 树中节点数目范围是 [1, 3 * 10^4]
 * -1000 
 * 
 * 
 */

// @lc code=start
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
    private long ans = 0;
    // 项庄舞剑，意在沛公
    // 返回值表示从根结点出发的最大的路径和
    // 但是我们要求的是最大路径和，这个路径只要相连就可以了
    private long postOrder(TreeNode root) {
        if (root == null) {
            return 0;
        }

        long l = postOrder(root.left);
        long r = postOrder(root.right);

        // 这里在取值的时候，有4种可能
        // - 0 + root.val
        // - l + root.val
        // - r + root.val
        // - l + r + root.val
        long tmp = Math.max(
            Math.max(0, l),
            Math.max(r, r + l)
        ) + root.val;
        ans = Math.max(ans, tmp);

        // 返回的时候，有3种选择
        // - root.val
        // - l + root.val
        // - r + root.val
        return Math.max(Math.max(0, l), r) + root.val;
    }

    public int maxPathSum(TreeNode root) {
        // 空树
        if (root == null) {
            return 0;
        }

        ans = Integer.MIN_VALUE;

        postOrder(root);

        return (int)ans;
    }
}
// @lc code=end

