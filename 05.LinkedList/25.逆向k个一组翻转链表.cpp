/*
 * 给定一个链表，将链表从尾部开始，k个一组进行反转。
 * 如果最左边的那个片段不足k个，那么不去翻转。
 *
 * A = [1, 2, 3, 4, 5], k = 3
 *
 * 输出：[1, 2, 5, 4, 3]
 */

class Solution {
    ListNode* reverse(ListNode* head) {
        ListNode dummy;

        auto p = head;
        while (p) {
            auto back = p->next;

            p->next = dummy.next;
            dummy.next = p;

            p = back;
        }
        return dummy.next;
    }

   public:
    ListNode* reverseKGroup(ListNode* head, int k) {
        // 假设输入为 head = [1, 2, 3, 4, 5]
        // 首先我们将链表进行翻转 head = [5,4,3,2,1]
        head = reverse(head);

        ListNode dummy;
        ListNode* tail = &dummy;
        int len = 0;

        ListNode ans;
        ListNode* ans_tail = &ans;

        auto append = [&](ListNode* p) {
            tail->next = p;
            tail = tail->next;
            len++;

            if (len == k) {
                // 反转之后的链表的尾部为t
                auto t = dummy.next;
                // 反转之后的链表的头部为r
                auto r = reverse(dummy.next);
                // 新整个链表添加到ans中。
                ans_tail->next = r;
                ans_tail = t;

                // 清空旧的k长度的链表
                len = 0;
                dummy.next = nullptr;
                tail = &dummy;
            }
        };

        auto p = head;
        while (p) {
            auto back = p->next;

            p->next = nullptr;
            append(p);

            p = back;
        }

        if (len) {
            ans_tail->next = dummy.next;
            ans_tail = tail;
        }
        ans_tail->next = nullptr;

        // 最后再反转一下
        return reverse(ans.next);
    }
};
// @lc code=end
