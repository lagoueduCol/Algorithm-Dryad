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

    def postorderTraversal(self, root):
        """
        :type root: TreeNode
        :rtype: List[int]
        """

        def reverse(head, tail):
            dummy = TreeNode()
            p = head
            while p != tail:
                back = p.right
                p.right = dummy.right
                dummy.right = p
                p = back
            return dummy.right

        def scan(head, ans):
            p = head
            while p:
                back = p.right
                ans.append(p.val)
                p = back

        def get_nodes(par, ans):
            head = reverse(par.left, par)
            scan(head, ans)
            reverse(head, None)
            head.right = par

        if not root:
            return []

        dummy = TreeNode()
        dummy.left = root

        cur = dummy
        ans = []

        while cur:
            if cur.left:
                pre = cur.left
                while pre.right and pre.right != cur:
                    pre = pre.right
                # 这里左子树没有处理过
                if not pre.right:
                    pre.right = cur
                    cur = cur.left
                else:
                    # 这里左子树已经处理过了
                    # 把最后的那一波一起带走
                    # pre.right这个时候就是父结点
                    get_nodes(cur, ans)
                    pre.right = None
                    cur = cur.right
            else:
                # 没有左边的子树，直接处理右子树
                cur = cur.right
        return ans

