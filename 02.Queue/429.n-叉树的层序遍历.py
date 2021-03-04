#
# @lc app=leetcode.cn id=429 lang=python
#
# [429] N 叉树的层序遍历
#
# https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal/description/
#
# algorithms
# Medium (68.07%)
# Likes:    135
# Dislikes: 0
# Total Accepted:    40.1K
# Total Submissions: 58.8K
# Testcase Example:  '[1,null,3,2,4,null,5,6]'
#
# 给定一个 N 叉树，返回其节点值的层序遍历。（即从左到右，逐层遍历）。
# 
# 树的序列化输入是用层序遍历，每组子节点都由 null 值分隔（参见示例）。
# 
# 
# 
# 示例 1：
# 
# 
# 
# 
# 输入：root = [1,null,3,2,4,null,5,6]
# 输出：[[1],[3,2,4],[5,6]]
# 
# 
# 示例 2：
# 
# 
# 
# 
# 输入：root =
# [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
# 输出：[[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]
# 
# 
# 
# 
# 提示：
# 
# 
# 树的高度不会超过 1000
# 树的节点总数在 [0, 10^4] 之间
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
    def levelOrder(self, root):
        """
        :type root: Node
        :rtype: List[List[int]]
        """
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
                for t in cur.children:
                    if t:
                        Q.put(t)
            # 把当前层添加到结果里面
            ans.append([x for x in curLevel])

        # 返回最终的结果
        return ans

