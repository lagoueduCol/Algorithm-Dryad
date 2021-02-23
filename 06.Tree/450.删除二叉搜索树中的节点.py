#
#
# [450] 删除二叉搜索树中的节点
#
# https://leetcode-cn.com/problems/delete-node-in-a-bst/description/
# https://www.lintcode.com/zh-cn/problem/remove-node-in-binary-search-tree/
#
# algorithms
# Medium (45.70%)
# Likes:    377
# Dislikes: 0
# Total Accepted:    30.6K
# Total Submissions: 67K
# Testcase Example:  '[5,3,6,2,4,null,7]\n3'
#
# 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key
# 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
# 
# 一般来说，删除节点可分为两个步骤：
# 
# 
# 首先找到需要删除的节点；
# 如果找到了，删除它。
# 
# 
# 说明： 要求算法时间复杂度为 O(h)，h 为树的高度。
# 
# 示例:
# 
# 
# root = [5,3,6,2,4,null,7]
# key = 3
# 
# ⁠   5
# ⁠  / \
# ⁠ 3   6
# ⁠/ \   \
# 2   4   7
# 
# 给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
# 
# 一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
# 
# ⁠   5
# ⁠  / \
# ⁠ 4   6
# ⁠/     \
# 2       7
# 
# 另一个正确答案是 [5,2,6,null,4,null,7]。
# 
# ⁠   5
# ⁠  / \
# ⁠ 2   6
# ⁠  \   \
# ⁠   4   7
# 
# 
#

# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution(object):
    def deleteNode(self, root, key):
        """
        :type root: TreeNode
        :type key: int
        :rtype: TreeNode
        """
        if not root:
            return None
    
        if key < root.val:
            root.left = self.deleteNode(root.left, key)
        elif key > root.val:
            root.right = self.deleteNode(root.right, key)
        else:
            if (not root.left) and (not root.right):
                return None
            elif root.left:
                large = root.left
                while large.right:
                    large = large.right
                root.val, large.val = large.val, root.val
                root.left = self.deleteNode(root.left, key)
            elif root.right:
                small = root.right
                while small.left:
                    small = small.left
                root.val, small.val = small.val, root.val
                root.right = self.deleteNode(root.right, key)
    
        return root

