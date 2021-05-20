#
# @lc app=leetcode.cn id=105 lang=python
#
# [105] 从前序与中序遍历序列构造二叉树
#
# https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/
#
# algorithms
# Medium (69.26%)
# Likes:    931
# Dislikes: 0
# Total Accepted:    165.7K
# Total Submissions: 238.9K
# Testcase Example:  '[3,9,20,15,7]\n[9,3,15,20,7]'
#
# 根据一棵树的前序遍历与中序遍历构造二叉树。
# 
# 注意:
# 你可以假设树中没有重复的元素。
# 
# 例如，给出
# 
# 前序遍历 preorder = [3,9,20,15,7]
# 中序遍历 inorder = [9,3,15,20,7]
# 
# 返回如下的二叉树：
# 
# ⁠   3
# ⁠  / \
# ⁠ 9  20
# ⁠   /  \
# ⁠  15   7
# 
#

# @lc code=start
# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution(object):
    def createTree(self, preorder, b, e, inorder, f, t, pos):
        if b >= e:
            return None
        
        if b + 1 == e:
            return TreeNode(preorder[b])
        
        root_value = preorder[b]
        root_pos = pos[root_value]
        
        root = TreeNode(root_value)
        left_len = root_pos - f
        right_len = t  - root_pos - 1

        root.left = self.createTree(preorder,
                                    b + 1,
                                    b + 1 + left_len,
                                    inorder,
                                    f,
                                    root_pos,
                                    pos)
        
        root.right = self.createTree(preorder, b + 1 + left_len,
                                     e,
                                     inorder,
                                     root_pos + 1,
                                     t,
                                     pos)
        return root

    def buildTree(self, preorder, inorder):
        """
        :type preorder: List[int]
        :type inorder: List[int]
        :rtype: TreeNode
        """

        N = 0 if not inorder else len(inorder)

        if N <= 0:
            return None
        
        pos = {}
        for i in range(0,N):
            pos[inorder[i]] = i

        return self.createTree(preorder, 0, N, inorder, 0, N, pos)
# @lc code=end

