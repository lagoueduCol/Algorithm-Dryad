# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution(object):
    def findMiddleNode(self, head):
        # 找到链表的中点
        s1 = head
        s2 = head
        pre = s1
        while s2 and s2.next:
            pre = s1
            s1 = s1.next
            s2 = s2.next.next

        # 把链表拆成两半
        return s1 if s2 else pre
    
    def split(self, head):
        # 这里获取了链表的中间结点
        mid = self.findMiddleNode(head)
        # 拿到链表的中间结点之后，可以得到链表的后半部分的开头
        back = mid.next
        # 把链表拆分为两半
        mid.next = None
        # 返回两个链表的头部
        return head, back
