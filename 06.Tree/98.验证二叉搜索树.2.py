#
#
# [98] 验证二叉搜索树
#
# https://leetcode-cn.com/problems/validate-binary-search-tree/description/
# https://www.lintcode.com/problem/validate-binary-search-tree/
#
# algorithms
# Medium (33.13%)
# Likes:    876
# Dislikes: 0
# Total Accepted:    205.7K
# Total Submissions: 621.1K
# Testcase Example:  '[2,1,3]'
#
# 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
# 
# 假设一个二叉搜索树具有如下特征：
# 
# 
# 节点的左子树只包含小于当前节点的数。
# 节点的右子树只包含大于当前节点的数。
# 所有左子树和右子树自身必须也是二叉搜索树。
# 
# 
# 示例 1:
# 
# 输入:
# ⁠   2
# ⁠  / \
# ⁠ 1   3
# 输出: true
# 
# 
# 示例 2:
# 
# 输入:
# ⁠   5
# ⁠  / \
# ⁠ 1   4
# / \
# 3   6
# 输出: false
# 解释: 输入为: [5,1,4,null,null,3,6]。
# 根节点的值为 5 ，但是其右子节点值为 4 。
# 
# 
#

# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

# 这里同时记录了：
# 1. 原二叉树的值
# 2. 新的二叉树的区间的值
class Node(object):
    def __init__(self, orig, l, r):
        self.orig = orig
        self.l = l
        self.r = r

class Solution(object):
    def isValidBST(self, root):
        """
        :type root: TreeNode
        :rtype: bool
        """
        # 栈用来存放原二叉树和影子二叉树的值
        stack = []
        # 从根结点开始
        n = Node(root, float('-inf'), float('inf'))
        # 注意这里是利用n来完成两棵树的遍历，所以需要利用n.t结点来判断
        while n.orig or len(stack) > 0:
            while n.orig:
                # 判断结点是不是在给定的范围里面
                if not (n.l < n.orig.val and n.orig.val < n.r):
                    return False
                # 注意这里不要写成append(n)是因为压入的时候会压入一个引用
                stack.append(Node(n.orig, n.l, n.r))
                # 遍历影子二叉树的左子树
                n.r = n.orig.val
                # 遍历原二叉树的右子树
                n.orig = n.orig.left

            n = stack.pop()
            # 在使用栈的时候，这两个的顺序不要乱!
            # 因为要走向右子树，更新左边间
            n.l = n.orig.val
            # 开始遍历原二叉树的右子树
            n.orig = n.orig.right

        return True


