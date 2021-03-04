#
# @lc app=leetcode.cn id=559 lang=python
#
# [559] N 叉树的最大深度
#
# https://leetcode-cn.com/problems/maximum-depth-of-n-ary-tree/description/
#
# algorithms
# Easy (71.45%)
# Likes:    152
# Dislikes: 0
# Total Accepted:    43.7K
# Total Submissions: 61.2K
# Testcase Example:  '[1,null,3,2,4,null,5,6]'
#
# 给定一个 N 叉树，找到其最大深度。
# 
# 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
# 
# N 叉树输入按层序遍历序列化表示，每组子节点由空值分隔（请参见示例）。
# 
# 
# 
# 示例 1：
# 
# 
# 
# 
# 输入：root = [1,null,3,2,4,null,5,6]
# 输出：3
# 
# 
# 示例 2：
# 
# 
# 
# 
# 输入：root =
# [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
# 输出：5
# 
# 
# 
# 
# 提示：
# 
# 
# 树的深度不会超过 1000 。
# 树的节点数目位于 [0, 10^4] 之间。
# 
# 
#

# @lc code=start
"""
# Definition for a Node.
class Node(object):
    def __init__(self, val=None, children=None):
        self.val = val
        self.children = children
"""
import Queue as queue
class Solution(object):
    def maxDepth(self, root):
        """
        :type root: Node
        :rtype: int
        """

        # 生成FIFO队列
        Q = queue.Queue()

        # 如果结点不为空，那么放到队列中
        if root:
            Q.put(root)

        # 需要返回的结果
        ans = 0

        # 依次处理，直到遍历完所有的元素
        while not Q.empty():
            # 拿到当前层结点的个数
            qSize = Q.qsize()


            # 依次取出当前层的结点
            for i in range(qSize):
                # 把当前层的结点放到curLevel里面。
                cur = Q.get()

                # 按照顺序取出下一层
                for t in cur.children:
                    if t:
                        Q.put(t)
                
            ans += 1

        # 返回最终的结果
        return ans
# @lc code=end

