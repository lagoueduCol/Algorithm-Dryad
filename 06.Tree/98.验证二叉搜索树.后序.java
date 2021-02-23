/*
 *
 * [98] 验证二叉搜索树
 *
 * https://leetcode-cn.com/problems/validate-binary-search-tree/description/
 *
 * algorithms
 * Medium (33.21%)
 * Likes:    889
 * Dislikes: 0
 * Total Accepted:    209.4K
 * Total Submissions: 630.5K
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
    class Node {
    public Long min = Long.MAX_VALUE;
    public Long max = Long.MIN_VALUE;
    public Node() {}
    public Node(Long l, Long r) {
      min = l;
      max = r;
    }
  }

  private boolean ans = true;
  private Node emptyRange = new Node();

  private Node postOrder(TreeNode root) {
    // 如果是空树，或者已经判断不是一棵二叉搜索树了
    // 那么就不需要再继续遍历了。
    if (root == null || !ans) {
      return emptyRange;
    }
    Node l = postOrder(root.left);
    Node r = postOrder(root.right);

    if (!(l.max < root.val && root.val < r.min)) {
      ans = false;
      // 当不符合的时候，返回任意区间都是可以的
      return emptyRange;
    }

    // 需要取左子树最小值与当前结点的最小值。
    // 需要取右子树最大值与当前结点的最大值
    return new Node(Math.min(l.min, root.val), Math.max(r.max, root.val));
  }

  public boolean isValidBST(TreeNode root) {
    ans = true;
    postOrder(root);
    return ans;
  }
}

