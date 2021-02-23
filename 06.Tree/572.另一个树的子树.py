#
#
# [572] 另一个树的子树
#
# https://leetcode-cn.com/problems/subtree-of-another-tree/description/
# https://www.lintcode.com/problem/subtree-of-another-tree/
#
# algorithms
# Easy (47.07%)
# Likes:    390
# Dislikes: 0
# Total Accepted:    53K
# Total Submissions: 112.6K
# Testcase Example:  '[3,4,5,1,2]\n[4,1,2]'
#
# 给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s
# 也可以看做它自身的一棵子树。
# 
# 示例 1:
# 给定的树 s:
# 
# 
# ⁠    3
# ⁠   / \
# ⁠  4   5
# ⁠ / \
# ⁠1   2
# 
# 
# 给定的树 t：
# 
# 
# ⁠  4 
# ⁠ / \
# ⁠1   2
# 
# 
# 返回 true，因为 t 与 s 的一个子树拥有相同的结构和节点值。
# 
# 示例 2:
# 给定的树 s：
# 
# 
# ⁠    3
# ⁠   / \
# ⁠  4   5
# ⁠ / \
# ⁠1   2
# ⁠   /
# ⁠  0
# 
# 
# 给定的树 t：
# 
# 
# ⁠  4
# ⁠ / \
# ⁠1   2
# 
# 
# 返回 false。
# 
#

# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution(object):
    def isSame(self, a, b):
        if (not a) and (not b):
            return True
        if (not a) or (not b):
            return False
        return a.val == b.val and \
            self.isSame(a.left, b.left) and \
                self.isSame(a.right, b.right)

    def isSubtree(self, s, t):
        """
        :type s: TreeNode
        :type t: TreeNode
        :rtype: bool
        """
        if s == t or (not t):
            return True

        if not s:
            return False
        
        return s.val == t.val and self.isSame(s, t) or \
            self.isSubtree(s.left, t) or \
                self.isSubtree(s.right, t)


