import java.util.Stack;

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
    // Node 记录了:
    // 1. 原树的结点t
    // 2. 影子二叉树的结点(l, r)
    class Node {
        TreeNode orig;
        Long l;
        Long r;

        public Node(TreeNode orig, Long l, Long r) {
            this.orig = orig;
            this.l = l;
            this.r = r;
        }
    };

    public boolean isValidBST(TreeNode root) {
        Stack<Node> stack = new Stack<Node>();
        // 从根结点开始
        Node n = new Node(root, Long.MIN_VALUE, Long.MAX_VALUE);
        // 注意这里是利用n来完成两棵树的遍历，所以需要利用n.t结点来判断
        while (n.orig != null || !stack.empty()) {
            while (n.orig != null) {
                // 判断结点是不是在给定的范围里面
                if (!(n.l < n.orig.val && n.orig.val < n.r)) {
                    return false;
                }
                stack.push(new Node(n.orig, n.l, n.r));
                // 在使用栈的时候，这两个的顺序不要乱!
                // 遍历影子二叉树的左子树
                n.r = Long.valueOf(n.orig.val);
                // 遍历原二叉树的左子树
                n.orig = n.orig.left;
            }
            n = stack.peek();
            stack.pop();
            // 下面的两个顺序不要乱!
            // 因为要走向右子树，更新左边间
            n.l = Long.valueOf(n.orig.val);
            // 开始遍历原二叉树的右子树
            n.orig = n.orig.right;
        }
        return true;
    }
}
