#
# @lc app=leetcode.cn id=21 lang=python
#
# [21] 合并两个有序链表
#

# @lc code=start
# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next


class Solution(object):
    def mergeTwoLists(self, l1, l2):
        """
        :type l1: ListNode
        :type l2: ListNode
        :rtype: ListNode
        """

        dummy = ListNode()
        tail = dummy

        while l1 or l2:
            if not l2 or (l1 and l1.val <= l2.val):
                back = l1.next

                tail.next = l1
                tail = l1

                l1 = back
            else:
                back = l2.next

                tail.next = l2
                tail = l2

                l2 = back
        tail.next = None

        return dummy.next

# @lc code=end
