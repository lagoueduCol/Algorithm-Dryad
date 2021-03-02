#
# @lc app=leetcode.cn id=83 lang=python
#
# [83] 删除排序链表中的重复元素
#
# https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/description/
#
# algorithms
# Easy (51.98%)
# Likes:    481
# Dislikes: 0
# Total Accepted:    186.7K
# Total Submissions: 358.3K
# Testcase Example:  '[1,1,2]'
#
# 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
# 
# 示例 1:
# 
# 输入: 1->1->2
# 输出: 1->2
# 
# 
# 示例 2:
# 
# 输入: 1->1->2->3->3
# 输出: 1->2->3
# 
#

# @lc code=start
# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution(object):
    def deleteDuplicates(self, head):
        dummy = ListNode()
        tail = dummy

        p = head

        while p:
            back = p.next

            if tail == dummy or p.val != tail.val:
                tail.next = p
                tail = p
            
            p = back
        
        tail.next = None
        return dummy.next

# @lc code=end

