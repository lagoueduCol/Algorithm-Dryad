# 本题的测试平台链接：
# https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
#
# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None
import Queue as queue
class Solution(object):
    def levelOrder(self, root):
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

        # 返回最终的结果
        return ans
