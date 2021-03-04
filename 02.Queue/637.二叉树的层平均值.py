#
# @lc app=leetcode.cn id=637 lang=python
#
# [637] 二叉树的层平均值
#
# https://leetcode-cn.com/problems/average-of-levels-in-binary-tree/description/
#
# algorithms
# Easy (68.78%)
# Likes:    239
# Dislikes: 0
# Total Accepted:    56.4K
# Total Submissions: 81.9K
# Testcase Example:  '[3,9,20,15,7]'
#
# 给定一个非空二叉树, 返回一个由每层节点平均值组成的数组。
# 
# 
# 
# 示例 1：
# 
# 输入：
# ⁠   3
# ⁠  / \
# ⁠ 9  20
# ⁠   /  \
# ⁠  15   7
# 输出：[3, 14.5, 11]
# 解释：
# 第 0 层的平均值是 3 ,  第1层是 14.5 , 第2层是 11 。因此返回 [3, 14.5, 11] 。
# 
# 
# 
# 
# 提示：
# 
# 
# 节点值的范围在32位有符号整数范围内。
# 
# 
#

# @lc code=start
# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None
import Queue as queue

class Solution(object):
    def averageOfLevels(self, root):
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
            tmp = 0.0

            # 依次取出当前层的结点
            for i in range(qSize):
                cur = Q.get()

                tmp += cur.val

                # 按照顺序取出下一层
                if cur.left:
                    Q.put(cur.left)
                if cur.right:
                    Q.put(cur.right)

            # 把当前层添加到结果里面
            ans.append(tmp / qSize)

        # 返回最终的结果
        return ans
# @lc code=end

