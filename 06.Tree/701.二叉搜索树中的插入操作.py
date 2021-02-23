#
#
# [701] 二叉搜索树中的插入操作
#
# https://leetcode-cn.com/problems/insert-into-a-binary-search-tree/description/
#
# algorithms
# Medium (72.06%)
# Likes:    154
# Dislikes: 0
# Total Accepted:    48.4K
# Total Submissions: 67.2K
# Testcase Example:  '[4,2,7,1,3]\n5'
#
# 给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 输入数据 保证
# ，新值和原始二叉搜索树中的任意节点值都不同。
# 
# 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回 任意有效的结果 。
# 
# 
# 
# 示例 1：
# 
# 
# 输入：root = [4,2,7,1,3], val = 5
# 输出：[4,2,7,1,3,5]
# 解释：另一个满足题目要求可以通过的树是：
# 
# 
# 
# 示例 2：
# 
# 
# 输入：root = [40,20,60,10,30,50,70], val = 25
# 输出：[40,20,60,10,30,50,70,null,null,25]
# 
# 
# 示例 3：
# 
# 
# 输入：root = [4,2,7,1,3,null,null,null,null,null,null], val = 5
# 输出：[4,2,7,1,3,5]
# 
# 
# 
# 
# 
# 
# 提示：
# 
# 
# 给定的树上的节点数介于 0 和 10^4 之间
# 每个节点都有一个唯一整数值，取值范围从 0 到 10^8
# -10^8 
# 新值和原始二叉搜索树中的任意节点值都不同
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
    def insertIntoBST(self, root, val):
        """
        :type root: TreeNode
        :type val: int
        :rtype: TreeNode
        """
        
        if not root:
            return TreeNode(val, None, None)
        
        if val < root.val:
            root.left = self.insertIntoBST(root.left, val)
        elif val > root.val:
            root.right = self.insertIntoBST(root.right, val)
        return root

