#
# @lc app=leetcode.cn id=203 lang=python
#
# [203] 移除链表元素
#
# https://leetcode-cn.com/problems/remove-linked-list-elements/description/
#
# algorithms
# Easy (46.96%)
# Likes:    539
# Dislikes: 0
# Total Accepted:    134.8K
# Total Submissions: 286.5K
# Testcase Example:  '[1,2,6,3,4,5,6]\n6'
#
# 删除链表中等于给定值 val 的所有节点。
# 
# 示例:
# 
# 输入: 1->2->6->3->4->5->6, val = 6
# 输出: 1->2->3->4->5
# 
# 
#

# @lc code=start
# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution(object):
    def removeElements(self, head, val):
        dummy = ListNode()
        tail = dummy
        p = head

        while p:
            back = p.next

            if p.val != val:
                tail.next = p
                tail = p

            p = back
        tail.next = None

        return dummy.next
# @lc code=end

