#
# @lc app=leetcode.cn id=501 lang=python
#
# [501] 二叉搜索树中的众数
#
# https://leetcode-cn.com/problems/find-mode-in-binary-search-tree/description/
#
# algorithms
# Easy (50.05%)
# Likes:    279
# Dislikes: 0
# Total Accepted:    49.3K
# Total Submissions: 98.4K
# Testcase Example:  '[1,null,2,2]'
#
# 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
# 
# 假定 BST 有如下定义：
# 
# 
# 结点左子树中所含结点的值小于等于当前结点的值
# 结点右子树中所含结点的值大于等于当前结点的值
# 左子树和右子树都是二叉搜索树
# 
# 
# 例如：
# 给定 BST [1,null,2,2],
# 
# ⁠  1
# ⁠   \
# ⁠    2
# ⁠   /
# ⁠  2
# 
# 
# 返回[2].
# 
# 提示：如果众数超过1个，不需考虑输出顺序
# 
# 进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）
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
    def __init__(self):
        self.preCnt = 0
        self.preValue = 0
        self.maxCnt = 0

    def mid(self, root, ret):
        if root:
            self.mid(root.left, ret)

            if self.preValue == root.val:
                self.preCnt += 1
            else:
                self.preValue = root.val
                self.preCnt = 1
            
            self.maxCnt = max(self.maxCnt, self.preCnt)

            if ret != None:
                if self.preCnt == self.maxCnt:
                    ret.append(self.preValue)
            
            self.mid(root.right, ret)

    def findMode(self, root):
        if not root:
            return []

        self.mid(root, None)

        self.preCnt = 0
        ret = []

        self.mid(root, ret)
        return ret

# @lc code=end

