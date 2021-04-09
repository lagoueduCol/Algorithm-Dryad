#
# @lc app=leetcode.cn id=543 lang=python
#
# [543] 二叉树的直径
#
# https://leetcode-cn.com/problems/diameter-of-binary-tree/description/
#
# algorithms
# Easy (52.95%)
# Likes:    671
# Dislikes: 0
# Total Accepted:    106.4K
# Total Submissions: 201K
# Testcase Example:  '[1,2,3,4,5]'
#
# 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
# 
# 
# 
# 示例 :
# 给定二叉树
# 
# ⁠         1
# ⁠        / \
# ⁠       2   3
# ⁠      / \     
# ⁠     4   5    
# 
# 
# 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
# 
# 
# 
# 注意：两结点之间的路径长度是以它们之间边的数目表示。
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
    def __init__(self):
        self.ans = 1

    # 返回值表示树中的一条路径，在这个路径上最长的点的个数。
    def postOrder(self, root):
        if root:
            l = self.postOrder(root.left)
            r = self.postOrder(root.right)
            self.ans = max(self.ans, l + r + 1)
            return max(l, r) + 1
        return 0

    def diameterOfBinaryTree(self, root):
        self.ans = 1
        self.postOrder(root)
        return self.ans - 1
# @lc code=end

