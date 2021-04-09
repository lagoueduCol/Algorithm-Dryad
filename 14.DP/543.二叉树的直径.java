import javax.swing.tree.TreeNode;

/*
 * @lc app=leetcode.cn id=543 lang=java
 *
 * [543] 二叉树的直径
 *
 * https://leetcode-cn.com/problems/diameter-of-binary-tree/description/
 *
 * algorithms
 * Easy (52.95%)
 * Likes:    671
 * Dislikes: 0
 * Total Accepted:    106.4K
 * Total Submissions: 201K
 * Testcase Example:  '[1,2,3,4,5]'
 *
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 * 
 * 
 * 
 * 示例 :
 * 给定二叉树
 * 
 * ⁠         1
 * ⁠        / \
 * ⁠       2   3
 * ⁠      / \     
 * ⁠     4   5    
 * 
 * 
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 * 
 * 
 * 
 * 注意：两结点之间的路径长度是以它们之间边的数目表示。
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
    private int ans = 0;

    // 返回值是一棵二叉树里面最长的结点的个数
    private int postOrder(TreeNode root) {
        if (root != null) {
            int l = postOrder(root.left);
            int r = postOrder(root.right);

            ans = Math.max(ans, l + r + 1);

            return Math.max(l, r) + 1;
        }
        return 0;
    }

    public int diameterOfBinaryTree(TreeNode root) {
        ans = 1;
        postOrder(root);
        return ans - 1;
    }
}
// @lc code=end
