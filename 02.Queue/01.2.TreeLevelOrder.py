# 本题的测试平台链接：
# https://leetcode-cn.com/problems/binary-tree-level-order-traversal/

# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution(object):
    def levelOrder(self, root):
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
            ans.append([x for x in curResult])
        
        # 返回结果
        return ans
