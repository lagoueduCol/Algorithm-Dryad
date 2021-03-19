#
# @lc app=leetcode.cn id=99 lang=python
#
# [99] 恢复二叉搜索树
#
# https://leetcode-cn.com/problems/recover-binary-search-tree/description/
#
# algorithms
# Hard (62.07%)
# Likes:    430
# Dislikes: 0
# Total Accepted:    50.5K
# Total Submissions: 81.4K
# Testcase Example:  '[1,3,null,null,2]'
#
# 给你二叉搜索树的根节点 root ，该树中的两个节点被错误地交换。请在不改变其结构的情况下，恢复这棵树。
# 
# 进阶：使用 O(n) 空间复杂度的解法很容易实现。你能想出一个只使用常数空间的解决方案吗？
# 
# 
# 
# 示例 1：
# 
# 
# 输入：root = [1,3,null,null,2]
# 输出：[3,1,null,null,2]
# 解释：3 不能是 1 左孩子，因为 3 > 1 。交换 1 和 3 使二叉搜索树有效。
# 
# 
# 示例 2：
# 
# 
# 输入：root = [3,1,4,null,null,2]
# 输出：[2,1,4,null,null,3]
# 解释：2 不能在 3 的右子树中，因为 2 < 3 。交换 2 和 3 使二叉搜索树有效。
# 
# 
# 
# 提示：
# 
# 
# 树上节点的数目在范围 [2, 1000] 内
# -2^31 
# 
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
        # 一个交换位置
        self.first = None
        # 第二个交换位置
        self.second = None
        # 记录中序遍历的前面的那个位置
        self.pre = None

    # 检查是不是交换位
    def check(self, cur):
        # 如果原来的顺序是[1, 2, 3, 4, 5, 6, 7]
        # 两个节点交换之后[1, 6, 3, 4, 5, 2, 7]
        # 第一次是 6 > 3, 我们需要记住6
        if self.pre and self.pre.val > cur.val:
            if not self.first:
                self.first = self.pre
            # 第二次是 5 > 2
            # 此时需要记住2
            self.second = cur
        # 记得更新pre
        self.pre = cur
    
    def midOrder(self, root):
        if root:
            self.midOrder(root.left)
            self.check(root)
            self.midOrder(root.right)

    def recoverTree(self, root):
        self.midOrder(root)
        if self.first and self.second:
            t = self.first.val
            self.first.val = self.second.val
            self.second.val = t

# @lc code=end

