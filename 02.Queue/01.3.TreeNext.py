"""
测试链接 https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii/submissions/
# Definition for a Node.
class Node(object):
    def __init__(self, val=0, left=None, right=None, next=None):
        self.val = val
        self.left = left
        self.right = right
        self.next = next
"""
class Solution(object):
    def connect(self, root):
        """
        :type root: Node
        :rtype: Node
        """
        Q = None
        if root:
            Q = root

        while Q:
            # 下一层结点的前一个结点
            nextLevelPreNode = None
            # 下一层结点的头结点
            nextLevelHead = None

            # Q是当前层的head
            p = Q
            # 按照顺序依次遍历当前层
            while p:
                # 拿到下一层的结点
                if p.left:
                    # 设置下一层的头
                    if not nextLevelHead:
                        nextLevelHead = p.left
                    # 前下一层的前驱结点的next指向下一层的结点
                    if nextLevelPreNode:
                        nextLevelPreNode.next = p.left
                    nextLevelPreNode = p.left
                if p.right:
                    if not nextLevelHead:
                        nextLevelHead = p.right
                    if nextLevelPreNode:
                        nextLevelPreNode.next = p.right
                    nextLevelPreNode = p.right
                p = p.next
            # 指向下一层的头
            Q = nextLevelHead

        return root
