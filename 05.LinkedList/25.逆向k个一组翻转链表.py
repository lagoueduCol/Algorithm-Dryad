"""
 *
 * 给定一个链表，将链表从尾部开始，k个一组进行反转。 如果最左边的那个片段不足k个，那么不去翻转。 
 * 
 * A = [1, 2, 3, 4, 5], k = 3
 * 
 * 输出：[1, 2, 5, 4, 3]
 *
 """

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

    def reverseKGroup(self, head, k):
        # 假设输入为 head = [1, 2, 3, 4, 5]
        # 首先我们将链表进行翻转 head = [5,4,3,2,1]
        head = self.reverse(head)
        tmp = ListNode()
        tmp_tail = tmp
        len = 0

        ans = ListNode()
        ans_tail = ans

        p = head

        while p:
            back = p.next
            p.next = None

            # 将p节点添加到tmp链表中
            tmp_tail.next = p
            tmp_tail = p
            len += 1

            # 如果tmp链表长度为k
            if len == k:
                # 需要将tmp链表进行反转
                tail = tmp.next
                head = self.reverse(tmp.next)

                ans_tail.next = head
                ans_tail = tail

                len = 0
                tmp.next = None
                tmp_tail = tmp

            p = back


        if len > 0:
            ans_tail.next = tmp.next
            ans_tail = tmp_tail
    
        return self.reverse(ans.next)


# @lc code=end

