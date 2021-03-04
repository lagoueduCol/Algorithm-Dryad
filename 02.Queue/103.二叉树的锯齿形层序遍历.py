#
# @lc app=leetcode.cn id=103 lang=python
#
# [103] 二叉树的锯齿形层序遍历
#
# https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/description/
#
# algorithms
# Medium (57.10%)
# Likes:    402
# Dislikes: 0
# Total Accepted:    117.5K
# Total Submissions: 205.7K
# Testcase Example:  '[3,9,20,null,null,15,7]'
#
# 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
# 
# 例如：
# 给定二叉树 [3,9,20,null,null,15,7],
# 
# 
# ⁠   3
# ⁠  / \
# ⁠ 9  20
# ⁠   /  \
# ⁠  15   7
# 
# 
# 返回锯齿形层序遍历如下：
# 
# 
# [
# ⁠ [3],
# ⁠ [20,9],
# ⁠ [15,7]
# ]
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
    def zigzagLevelOrder(self, root):
        """
        :type root: TreeNode
        :rtype: List[List[int]]
        """
        # 存放每一层遍历的结果
        ans = []

        # 记录当前层，注意不要把空的root放进去
        cur = []
        if root:
            cur.append(root)
        
        # 当前层需要倒转不？
        bRev = False
        
        while len(cur):
            # nextLevel用来存放下一层的结点
            nextLevel = []

            # curResult用来存放当前层的值
            curResult = []

            for c in cur:
                # 把当前层的值放到curResult里面
                curResult.append(c.val)

                # 生成下一层
                if c.left:
                    nextLevel.append(c.left)
                if c.right:
                    nextLevel.append(c.right)
            
            # 指向下一层
            cur = nextLevel

            # 把当前层放到结果中
            if bRev:
                curResult.reverse()
            ans.append([x for x in curResult])
            bRev = not bRev
        
        # 返回结果
        return ans

# @lc code=end

