/*
 *
 * [450] 删除二叉搜索树中的节点
 *
 * https://leetcode-cn.com/problems/delete-node-in-a-bst/description/
 * https://www.lintcode.com/zh-cn/problem/remove-node-in-binary-search-tree/
 * 
 * algorithms
 * Medium (45.70%)
 * Likes:    377
 * Dislikes: 0
 * Total Accepted:    30.6K
 * Total Submissions: 67K
 * Testcase Example:  '[5,3,6,2,4,null,7]\n3'
 *
 * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key
 * 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
 * 
 * 一般来说，删除节点可分为两个步骤：
 * 
 * 
 * 首先找到需要删除的节点；
 * 如果找到了，删除它。
 * 
 * 
 * 说明： 要求算法时间复杂度为 O(h)，h 为树的高度。
 * 
 * 示例:
 * 
 * 
 * root = [5,3,6,2,4,null,7]
 * key = 3
 * 
 * ⁠   5
 * ⁠  / \
 * ⁠ 3   6
 * ⁠/ \   \
 * 2   4   7
 * 
 * 给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
 * 
 * 一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
 * 
 * ⁠   5
 * ⁠  / \
 * ⁠ 4   6
 * ⁠/     \
 * 2       7
 * 
 * 另一个正确答案是 [5,2,6,null,4,null,7]。
 * 
 * ⁠   5
 * ⁠  / \
 * ⁠ 2   6
 * ⁠  \   \
 * ⁠   4   7
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
    private void swapValue(TreeNode a, TreeNode b) {
        int t = a.val;
        a.val = b.val;
        b.val = t;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            // 当前树只有一个结点，那么直接返回null
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.left != null) {
                // 当前结点还有左子树
                // 那么需要从左子树中找个较大的值。
                TreeNode large = root.left;
                while (large.right != null) {
                    large = large.right;
                }
                // 交换再删除
                swapValue(root, large);
                root.left = deleteNode(root.left, key);
            } else if (root.right != null) {
                // 当前结点还有右子树
                TreeNode small = root.right;
                // 那么需要从右子树中找个较小的值
                while (small.left != null) {
                    small = small.left;
                }
                // 交换再删除
                swapValue(root, small);
                root.right = deleteNode(root.right, key);
            }
        }

        return root;
    }
}

