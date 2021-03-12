#
# @lc app=leetcode.cn id=21 lang=python
#
# [21] 合并两个有序链表
#
# https://leetcode-cn.com/problems/merge-two-sorted-lists/description/
#
# algorithms
# Easy (65.18%)
# Likes:    1499
# Dislikes: 0
# Total Accepted:    457.5K
# Total Submissions: 702K
# Testcase Example:  '[1,2,4]\n[1,3,4]'
#
# 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
# 
# 
# 
# 示例 1：
# 
# 
# 输入：l1 = [1,2,4], l2 = [1,3,4]
# 输出：[1,1,2,3,4,4]
# 
# 
# 示例 2：
# 
# 
# 输入：l1 = [], l2 = []
# 输出：[]
# 
# 
# 示例 3：
# 
# 
# 输入：l1 = [], l2 = [0]
# 输出：[0]
# 
# 
# 
# 
# 提示：
# 
# 
# 两个链表的节点数目范围是 [0, 50]
# -100 
# l1 和 l2 均按 非递减顺序 排列
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
    def mergeTwoLists(self, l1, l2):

        # 首先生成空链表
        dummy = ListNode()
        tail = dummy

        # 遍历两个有序链表,每次只取一个结点append到新链表里面
        while (l1 != None or l2 != None):
            # 如果l2链表为空，或者l1链表里面的值更小，那么取l1结点追加到
            # 新链表尾部
            if (l2 == None or l1 != None and l1.val < l2.val):
                tail.next = l1
                tail = l1
                l1 = l1.next
            else:
                # 其他情况，则把l2结点添加到新链表尾部
                tail.next = l2
                tail = l2
                l2 = l2.next

        # 注意：这里一定要记得把tail.next设置为空。
        # 虽然这个题可能并不需要，但是应该养成收尾的好习惯
        tail.next = None

        # 返回dummy.next, 不要返回dummy!!
        return dummy.next
# @lc code=end

