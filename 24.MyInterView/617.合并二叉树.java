/*
 * @lc app=leetcode.cn id=617 lang=java
 *
 * [617] 合并二叉树
 *
 * https://leetcode-cn.com/problems/merge-two-binary-trees/description/
 *
 * algorithms
 * Easy (78.73%)
 * Likes:    688
 * Dislikes: 0
 * Total Accepted:    145.1K
 * Total Submissions: 184.3K
 * Testcase Example:  '[1,3,2,5]\n[2,1,3,null,4,null,7]'
 *
 * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
 * 
 * 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL
 * 的节点将直接作为新二叉树的节点。
 * 
 * 示例 1:
 * 
 * 
 * 输入: 
 * Tree 1                     Tree 2                  
 * ⁠         1                         2                             
 * ⁠        / \                       / \                            
 * ⁠       3   2                     1   3                        
 * ⁠      /                           \   \                      
 * ⁠     5                             4   7                  
 * 输出: 
 * 合并后的树:
 * 3
 * / \
 * 4   5
 * / \   \ 
 * 5   4   7
 * 
 * 
 * 注意: 合并必须从两个树的根节点开始。
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
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        // 采用前序遍历
        // 如果两棵树都空了
        if (root1 == null && root2 == null) {
            return null;
        }

        // 否则就求和
        int rootValue = 0;

        // 求出根结点的值, 实际上就是求和
        rootValue += root1 == null ? 0 : root1.val;
        rootValue += root2 == null ? 0 : root2.val;

        // 生成新结点
        TreeNode root = new TreeNode(rootValue);

        root.left = mergeTrees(root1 == null ? null : root1.left,
                               root2 == null ? null : root2.left);

        root.right = mergeTrees(root1 == null ? null : root1.right,
                                root2 == null ? null : root2.right);

        return root;
    }
}
// @lc code=end

