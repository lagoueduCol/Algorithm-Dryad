#
#
# [144] 二叉树的前序遍历
#
# https://leetcode-cn.com/problems/binary-tree-preorder-traversal/description/
# https://www.lintcode.com/problem/binary-tree-preorder-traversal/
#
# algorithms
# Medium (66.89%)
# Likes:    367
# Dislikes: 0
# Total Accepted:    170.7K
# Total Submissions: 255.3K
# Testcase Example:  '[1,null,2,3]'
#
# 给定一个二叉树，返回它的 前序 遍历。
# 
# 示例:
# 
# 输入: [1,null,2,3]  
# ⁠  1
# ⁠   \
# ⁠    2
# ⁠   /
# ⁠  3 
# 
# 输出: [1,2,3]
# 
# 
# 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
# 
#

# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution(object):
    def preorderTraversal(self, root):
        """
        :type root: TreeNode
        :rtype: List[int]
        """
        ans = []

        cur = root
        while cur:
            if cur.left:
                pre = cur.left
                while pre.right and pre.right != cur:
                    pre = pre.right
                
                if not pre.right:
                    ans.append(cur.val)
                    pre.right = cur
                    cur = cur.left
                else:
                    pre.right = None
                    cur = cur.right
            else:
                ans.append(cur.val)
                cur = cur.right

        return ans

