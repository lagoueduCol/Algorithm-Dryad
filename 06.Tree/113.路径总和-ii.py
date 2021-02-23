#
#
# [113] 路径总和 II
#
# https://leetcode-cn.com/problems/path-sum-ii/description/
# https://www.lintcode.com/problem/path-sum-ii/description
#
# algorithms
# Medium (61.33%)
# Likes:    401
# Dislikes: 0
# Total Accepted:    109K
# Total Submissions: 177.8K
# Testcase Example:  '[5,4,8,11,null,13,4,7,2,null,null,5,1]\n22'
#
# 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
# 
# 说明: 叶子节点是指没有子节点的节点。
# 
# 示例:
# 给定如下二叉树，以及目标和 sum = 22，
# 
# ⁠             5
# ⁠            / \
# ⁠           4   8
# ⁠          /   / \
# ⁠         11  13  4
# ⁠        /  \    / \
# ⁠       7    2  5   1
# 
# 
# 返回:
# 
# [
# ⁠  [5,4,11,2],
# ⁠  [5,8,4,5]
# ]
# 
# 
#

# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution(object):
    def pathSum(self, root, sum):
        """
        :type root: TreeNode
        :type sum: int
        :rtype: List[List[int]]
        """
        def backtrace(root, path, sum, target, ans):
            if not root:
                return
            
            sum += root.val
            path.append(root.val)

            if not root.left and not root.right:
                if sum == target:
                    ans.append([p for p in path])
            else:
                backtrace(root.left, path, sum, target, ans)
                backtrace(root.right, path, sum, target, ans)

            path.pop()
        
        ans = []
        path = []
        backtrace(root, path, 0, sum, ans)
        return ans


