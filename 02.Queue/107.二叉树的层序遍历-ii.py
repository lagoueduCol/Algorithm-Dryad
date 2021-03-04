#
# @lc app=leetcode.cn id=107 lang=python
#
# [107] 二叉树的层序遍历 II
#
# https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/description/
#
# algorithms
# Medium (68.19%)
# Likes:    412
# Dislikes: 0
# Total Accepted:    126.6K
# Total Submissions: 185.7K
# Testcase Example:  '[3,9,20,null,null,15,7]'
#
# 给定一个二叉树，返回其节点值自底向上的层序遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
# 
# 例如：
# 给定二叉树 [3,9,20,null,null,15,7],
# 
# 
# ⁠   3
# ⁠  / \
# ⁠ 9  20
# ⁠   /  \
# ⁠  15   7
# 
# 
# 返回其自底向上的层序遍历为：
# 
# 
# [
# ⁠ [15,7],
# ⁠ [9,20],
# ⁠ [3]
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
import Queue as queue
class Solution(object):
    def levelOrderBottom(self, root):
        """
        :type root: TreeNode
        :rtype: List[List[int]]
        """
        # 生成FIFO队列
        Q = queue.Queue()

        # 如果结点不为空，那么放到队列中
        if root:
            Q.put(root)

        # 需要返回的结果
        ans = []

        # 依次处理，直到遍历完所有的元素
        while not Q.empty():
            # 拿到当前层结点的个数
            qSize = Q.qsize()
            # 存放当前层遍历的结果
            curLevel = []

            # 依次取出当前层的结点
            for i in range(qSize):
                # 把当前层的结点放到curLevel里面。
                cur = Q.get()
                curLevel.append(cur.val)

                # 按照顺序取出下一层
                if cur.left:
                    Q.put(cur.left)
                if cur.right:
                    Q.put(cur.right)
            # 把当前层添加到结果里面
            ans.append([x for x in curLevel])

        ans.reverse()
        # 返回最终的结果
        return ans
# @lc code=end

