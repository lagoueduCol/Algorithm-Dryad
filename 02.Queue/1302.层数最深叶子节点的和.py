#
# @lc app=leetcode.cn id=1302 lang=python
#
# [1302] 层数最深叶子节点的和
#
# https://leetcode-cn.com/problems/deepest-leaves-sum/description/
#
# algorithms
# Medium (81.31%)
# Likes:    47
# Dislikes: 0
# Total Accepted:    14.2K
# Total Submissions: 17.4K
# Testcase Example:  '[1,2,3,4,5,null,6,7,null,null,null,null,8]'
#
# 给你一棵二叉树，请你返回层数最深的叶子节点的和。
# 
# 
# 
# 示例：
# 
# 
# 
# 输入：root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
# 输出：15
# 
# 
# 
# 
# 提示：
# 
# 
# 树中节点数目在 1 到 10^4 之间。
# 每个节点的值在 1 到 100 之间。
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
    def deepestLeavesSum(self, root):
        """
        :type root: TreeNode
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
            # 存放当前层遍历的结果
            tmp = 0

            # 依次取出当前层的结点
            for i in range(qSize):
                # 把当前层的结点放到curLevel里面。
                cur = Q.get()
                tmp += cur.val

                # 按照顺序取出下一层
                if cur.left:
                    Q.put(cur.left)
                if cur.right:
                    Q.put(cur.right)
            # 把当前层添加到结果里面
            ans = tmp

        # 返回最终的结果
        return ans

# @lc code=end

