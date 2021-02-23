import javax.swing.tree.TreeNode;

/*
 *
 * [144] 二叉树的前序遍历
 *
 * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/description/
 * https://www.lintcode.com/problem/binary-tree-preorder-traversal/
 *
 * algorithms
 * Medium (66.89%)
 * Likes:    367
 * Dislikes: 0
 * Total Accepted:    170.7K
 * Total Submissions: 255.3K
 * Testcase Example:  '[1,null,2,3]'
 *
 * 给定一个二叉树，返回它的 前序 遍历。
 * 
 * 示例:
 * 
 * 输入: [1,null,2,3]  
 * ⁠  1
 * ⁠   \
 * ⁠    2
 * ⁠   /
 * ⁠  3 
 * 
 * 输出: [1,2,3]
 * 
 * 
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 * 
 */

import java.util.List;
import java.util.ArrayList;

/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    private void preOrder(TreeNode root, List<Integer> ans) {
        if (root != null) {
            ans.add(root.val);
            preOrder(root.left, ans);
            preOrder(root.right, ans);
        }
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        preOrder(root, ans);
        return ans;
    }
}
