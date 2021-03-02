#
# @lc app=leetcode.cn id=206 lang=python
#
# [206] 反转链表
#
# https://leetcode-cn.com/problems/reverse-linked-list/description/
#
# algorithms
# Easy (71.37%)
# Likes:    1542
# Dislikes: 0
# Total Accepted:    445.9K
# Total Submissions: 623.8K
# Testcase Example:  '[1,2,3,4,5]'
#
# 反转一个单链表。
# 
# 示例:
# 
# 输入: 1->2->3->4->5->NULL
# 输出: 5->4->3->2->1->NULL
# 
# 进阶:
# 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
# 
#

# @lc code=start
# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution(object):
    def reverseList(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """

        dummy = ListNode()

        p = head

        while p:
            back = p.next

            p.next = dummy.next
            dummy.next = p

            p = back
        
        return dummy.next
# @lc code=end

