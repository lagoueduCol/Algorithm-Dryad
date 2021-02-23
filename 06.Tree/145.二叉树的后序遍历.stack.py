#
#
# [145] 二叉树的后序遍历
#
# https://leetcode-cn.com/problems/binary-tree-postorder-traversal/description/
#
# algorithms
# Medium (73.92%)
# Likes:    507
# Dislikes: 0
# Total Accepted:    180.4K
# Total Submissions: 244K
# Testcase Example:  '[1,null,2,3]'
#
# 给定一个二叉树，返回它的 后序 遍历。
# 
# 示例:
# 
# 输入: [1,null,2,3]  
# ⁠  1
# ⁠   \
# ⁠    2
# ⁠   /
# ⁠  3 
# 
# 输出: [3,2,1]
# 
# 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
# 
#

# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution(object):
    def postorderTraversal(self, t):
        """
        :type root: TreeNode
        :rtype: List[int]
        """
        ans = []
        # pre 在后序遍历时的前面一个结点
        pre = None
        s = []
        # 如果栈中还有元素，或者当前结点t非空
        while len(s) > 0 or t:
            # 顺着左子树走，并且将所有的元素压入栈中
            while t:
                s.append(t);
                t = t.left;

            # 当没有任何元素可以压栈的时候
            # 拿栈顶元素，注意这里并不将栈顶元素弹出
            # 因为在迭代时，根结点需要遍历两次，这里需要判断一下
            # 如果是第一次遍历是不能弹栈的。
            t = s[-1]
            # 1. 如果当前结点左子树为空，那么右子树没有遍历的必要
            # 需要将当前结点放到ans中
            # 2. 当t.right == pre时，说明右子树已经被打印过了
            # 那么此时需要将当前结点放到ans中
            if not t.right or t.right == pre:
                # 此时为第二次遍历根结点，所以放到ans中。
                ans.append(t.val)
                # 因为已经遍历了当前结点，所以需要更新pre结点
                s.pop()
                # 已经打印完毕。需要设置为空，否则下一轮循环
                # 还会遍历t的左子树。
                pre = t
                t = None
            else:
                # 第一次走到t结点，不能放到ans中，因为t的右子树还没有遍历。
                # 需要将t结点的右子树遍历
                t = t.right;
        return ans

