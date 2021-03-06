#
# @lc app=leetcode.cn id=24 lang=python
#
# [24] 两两交换链表中的节点
#
# https://leetcode-cn.com/problems/swap-nodes-in-pairs/description/
#
# algorithms
# Medium (69.06%)
# Likes:    836
# Dislikes: 0
# Total Accepted:    226K
# Total Submissions: 326.5K
# Testcase Example:  '[1,2,3,4]'
#
# 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
# 
# 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
# 
# 
# 
# 示例 1：
# 
# 
# 输入：head = [1,2,3,4]
# 输出：[2,1,4,3]
# 
# 
# 示例 2：
# 
# 
# 输入：head = []
# 输出：[]
# 
# 
# 示例 3：
# 
# 
# 输入：head = [1]
# 输出：[1]
# 
# 
# 
# 
# 提示：
# 
# 
# 链表中节点的数目在范围 [0, 100] 内
# 0 
# 
# 
# 
# 
# 进阶：你能在不修改链表节点值的情况下解决这个问题吗?（也就是说，仅修改节点本身。）
# 
#

# @lc code=start
# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution(object):
    def split(self, head):
        odd = ListNode()
        even = ListNode()

        odd_tail = odd
        even_tail = even

        p = head

        idx = 1 
        while p:
            back = p.next

            if idx % 2 == 1:
                odd_tail.next = p
                odd_tail = odd_tail.next
            else:
                even_tail.next = p
                even_tail = even_tail.next

            p = back
            idx += 1

        odd_tail.next = None
        even_tail.next = None

        return odd.next, even.next
    
    # 注意，这里在合并的时候，是先出even里面结点
    def merge(self, odd, even):
        isEven = True
        dummy = ListNode()
        tail = dummy

        while odd or even:
            if (not odd) or (isEven and even):
                tail.next = even
                even = even.next
            else:
                tail.next = odd
                odd = odd.next
            tail = tail.next
            isEven = not isEven

        tail.next = None

        return dummy.next

    def swapPairs(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        odd, even = self.split(head)
        return self.merge(odd, even)

# @lc code=end

