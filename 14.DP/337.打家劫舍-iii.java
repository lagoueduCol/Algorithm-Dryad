/*
 * @lc app=leetcode.cn id=337 lang=java
 *
 * [337] 打家劫舍 III
 *
 * https://leetcode-cn.com/problems/house-robber-iii/description/
 *
 * algorithms
 * Medium (61.69%)
 * Likes:    798
 * Dislikes: 0
 * Total Accepted:    95.6K
 * Total Submissions: 154.9K
 * Testcase Example:  '[3,2,3,null,3,null,1]'
 *
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。
 * 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
 * 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 * 
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 * 
 * 示例 1:
 * 
 * 输入: [3,2,3,null,3,null,1]
 * 
 * ⁠    3
 * ⁠   / \
 * ⁠  2   3
 * ⁠   \   \ 
 * ⁠    3   1
 * 
 * 输出: 7 
 * 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
 * 
 * 示例 2:
 * 
 * 输入: [3,4,5,1,3,null,1]
 * 
 * 3
 * ⁠   / \
 * ⁠  4   5
 * ⁠ / \   \ 
 * ⁠1   3   1
 * 
 * 输出: 9
 * 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
 * 
 * 
 */

// @lc code=start
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode() {} TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left
 * = left; this.right = right; } }
 */
class Solution {
    // 返回值是一个pair
    // ans[0]表示抢当前根结点
    // ans[1]表示不能抢当前结点
    private long[] postOrder(TreeNode root) {
        if (root == null) {
            return new long[] { 0, 0 };
        }

        long[] l = postOrder(root.left);
        long[] r = postOrder(root.right);

        // 如果要抢当前结点
        long get = root.val;
        // 那么两个子结点必然不能抢
        get += l[1] + r[1];

        // 如果不抢当前结点
        long skip = 0;
        // 那么两个子结点可以抢，也可以不抢
        skip += Math.max(l[0], l[1]) + Math.max(r[0], r[1]);

        return new long[] { get, skip };
    }

    public int rob(TreeNode root) {
        long[] ans = postOrder(root);
        return (int) Math.max(ans[0], ans[1]);
    }
}
// @lc code=end
