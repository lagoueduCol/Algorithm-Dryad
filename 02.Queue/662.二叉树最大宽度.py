# -*- coding: utf-8 -*-
#
# @lc app=leetcode.cn id=662 lang=python
#
# [662] 二叉树最大宽度
#
# https://leetcode-cn.com/problems/maximum-width-of-binary-tree/description/
#
# algorithms
# Medium (39.16%)
# Likes:    203
# Dislikes: 0
# Total Accepted:    16.2K
# Total Submissions: 41.5K
# Testcase Example:  '[1,3,2,5,3,null,9]'
#
# 给定一个二叉树，编写一个函数来获取这个树的最大宽度。树的宽度是所有层中的最大宽度。这个二叉树与满二叉树（full binary
# tree）结构相同，但一些节点为空。
# 
# 每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度。
# 
# 示例 1:
# 
# 
# 输入: 
# 
# ⁠          1
# ⁠        /   \
# ⁠       3     2
# ⁠      / \     \  
# ⁠     5   3     9 
# 
# 输出: 4
# 解释: 最大值出现在树的第 3 层，宽度为 4 (5,3,null,9)。
# 
# 
# 示例 2:
# 
# 
# 输入: 
# 
# ⁠         1
# ⁠        /  
# ⁠       3    
# ⁠      / \       
# ⁠     5   3     
# 
# 输出: 2
# 解释: 最大值出现在树的第 3 层，宽度为 2 (5,3)。
# 
# 
# 示例 3:
# 
# 
# 输入: 
# 
# ⁠         1
# ⁠        / \
# ⁠       3   2 
# ⁠      /        
# ⁠     5      
# 
# 输出: 2
# 解释: 最大值出现在树的第 2 层，宽度为 2 (3,2)。
# 
# 
# 示例 4:
# 
# 
# 输入: 
# 
# ⁠         1
# ⁠        / \
# ⁠       3   2
# ⁠      /     \  
# ⁠     5       9 
# ⁠    /         \
# ⁠   6           7
# 输出: 8
# 解释: 最大值出现在树的第 4 层，宽度为 8 (6,null,null,null,null,null,null,7)。
# 
# 
# 注意: 答案在32位有符号整数的表示范围内。
# 
#

# @lc code=start
# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

import Queue as queue
class Solution(object):
    def widthOfBinaryTree(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
        # 生成FIFO队列
        Q = queue.Queue()

        # 如果结点不为空，那么放到队列中
        if root:
            Q.put((root,0))

        # 需要返回的结果
        ans = 0

        # 依次处理，直到遍历完所有的元素
        while not Q.empty():
            qSize = Q.qsize()

            start = -1
            end = 0

            for i in range(qSize):
                cur = Q.get()

                if -1 == start:
                    start = cur[1]
                end = cur[1]

                if cur[0].left:
                    Q.put((cur[0].left, cur[1] * 2))

                if cur[0].right:
                    Q.put((cur[0].right, cur[1] * 2 + 1))

            ans = max(ans, end - start + 1)

        return ans

# @lc code=end

