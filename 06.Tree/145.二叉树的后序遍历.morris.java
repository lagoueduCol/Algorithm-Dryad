/*
 *
 * [145] 二叉树的后序遍历
 *
 * https://leetcode-cn.com/problems/binary-tree-postorder-traversal/description/
 *
 * algorithms
 * Medium (73.92%)
 * Likes:    507
 * Dislikes: 0
 * Total Accepted:    180.4K
 * Total Submissions: 244K
 * Testcase Example:  '[1,null,2,3]'
 *
 * 给定一个二叉树，返回它的 后序 遍历。
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
 * 输出: [3,2,1]
 *
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 */

import java.util.*;

class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;
  TreeNode() {}
  TreeNode(int val) { this.val = val; }
  TreeNode(int val, TreeNode left, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }
}


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
  private TreeNode reverse(TreeNode head, TreeNode tail) {
    TreeNode dummy = new TreeNode();
    TreeNode p = head;
    while (p != tail) {
      TreeNode back = p.right;
      p.right = dummy.right;
      dummy.right = p;
      p = back;
    }
    return dummy.right;
  }

  private void scan(TreeNode head, List<Integer> ans) {
    TreeNode p = head;
    while (p != null) {
      TreeNode back = p.right;
      ans.add(p.val);
      p = back;
    }
  }

  void print(TreeNode par, List<Integer> ans) {
    // a.b.c.d.a
    // head = b, last = a
    TreeNode head = reverse(par.left, par);
    scan(head, ans);
    reverse(head, null);
    head.right = par;
  }

  public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> ans = new ArrayList<>();
    if (root == null) {
      return ans;
    }

    // 如果使用morris遍历，需要在原来的树上加一个dummy Node
    TreeNode dummy = new TreeNode();
    dummy.left = root;
    TreeNode cur = dummy;

    while (cur != null) {
      if (cur.left != null) {
        TreeNode pre = cur.left;
        while (pre.right != null &&  pre.right != cur) {
          pre = pre.right;
        }
        // 这里左子树没有处理过
        if (pre.right == null) {
          pre.right = cur;
          cur = cur.left;
        } else {
          // 这里左子树已经处理过了
          // 把最后的那一波一起带走
          // pre.right这个时候就是父结点
          print(cur, ans);
          pre.right = null;
          cur = cur.right;
        }
      } else {
        // 没有左边的子树，直接处理右子树
        cur = cur.right;
      }
    }
    return ans;
  }
}
