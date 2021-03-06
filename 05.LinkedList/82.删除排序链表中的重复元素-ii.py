#
# @lc app=leetcode.cn id=82 lang=python
#
# [82] 删除排序链表中的重复元素 II
#
# https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/description/
#
# algorithms
# Medium (50.21%)
# Likes:    464
# Dislikes: 0
# Total Accepted:    87.5K
# Total Submissions: 173.8K
# Testcase Example:  '[1,2,3,3,4,4,5]'
#
# 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
# 
# 示例 1:
# 
# 输入: 1->2->3->3->4->4->5
# 输出: 1->2->5
# 
# 
# 示例 2:
# 
# 输入: 1->1->1->2->3
# 输出: 2->3
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
        # 新生成两个链表

        # tmp链表：
        # 这个链表中只存放一样的元素
        # 所有的新元素都要追加到这里
        # 当发现和tmp中元素不一样的时候
        # 就根据情况，看是否需要把tmp链表中的元素推到
        # ans链表中
        # 由于这里我们要求是没有重复出现过的元素
        # 所以我们只能当tmp链表中结点个数为1的时候
        # 才能把tmp链表放到ans链表中
        tmp = ListNode()
        tmp_tail = tmp

        # ans链表
        # 用来存放返回值
        ans = ListNode()
        ans_tail = ans

        p = head
        while p:
            back = p.next

            # 如果tmp为空，或者与tmp链表中的元素值相同
            if tmp_tail == tmp or tmp_tail.val == p.val:
                tmp_tail.next = p
                tmp_tail = p
            else:
                # 如果与 tmp中的元素不同
                # 1. 如果tmp中只有一个元素
                # 将tmp链表中的元素加到ans中
                if tmp_tail == tmp.next:
                    ans_tail.next = tmp_tail
                    ans_tail = ans_tail.next
                
                # 然后将tmp链表只装上 p这个结点
                tmp.next = p
                tmp_tail = p

            p = back
        
        # 如果tmp中还是只有一个结点
        if tmp_tail == tmp.next:
            ans_tail.next = tmp.next
            ans_tail = ans_tail.next
        
        ans_tail.next = None

        return ans.next


# @lc code=end

