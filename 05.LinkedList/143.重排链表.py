#
# @lc app=leetcode.cn id=143 lang=python
#
# [143] 重排链表
#
# https://leetcode-cn.com/problems/reorder-list/description/
#
# algorithms
# Medium (59.50%)
# Likes:    500
# Dislikes: 0
# Total Accepted:    77.9K
# Total Submissions: 130.9K
# Testcase Example:  '[1,2,3,4]'
#
# 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
# 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
# 
# 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
# 
# 示例 1:
# 
# 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
# 
# 示例 2:
# 
# 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
# 
#

# @lc code=start
# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution(object):
    def reverse(self, head):
        dummy = ListNode()
        p = head
        while p:
            back = p.next
            p.next = dummy.next
            dummy.next = p
            p = back
        return dummy.next

    def split(self, head):
        pre = head
        s1 = head
        s2 = head

        while s2 and s2.next:
            pre = s1
            s1 = s1.next
            s2 = s2.next.next
        
        return s1 if s2 else pre

    def reorderList(self, head):
        if not head or not head.next:
            return
        
        mid = self.split(head)
        front = head
        back = mid.next
        mid.next = None

        back = self.reverse(back)


        dummy = ListNode()
        tail = dummy

        is_front = True
        while front or back:
            if not back or is_front and front:
                tail.next = front
                tail = tail.next
                front = front.next
            else:
                tail.next = back
                tail = tail.next
                back = back.next
            is_front = not is_front

        tail.next = None

# @lc code=end

