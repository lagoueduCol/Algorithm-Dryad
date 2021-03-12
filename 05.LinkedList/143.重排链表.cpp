/*
 * @lc app=leetcode.cn id=143 lang=cpp
 *
 * [143] 重排链表
 *
 * https://leetcode-cn.com/problems/reorder-list/description/
 *
 * algorithms
 * Medium (59.50%)
 * Likes:    500
 * Dislikes: 0
 * Total Accepted:    77.9K
 * Total Submissions: 130.9K
 * Testcase Example:  '[1,2,3,4]'
 *
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 示例 1:
 *
 * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
 *
 * 示例 2:
 *
 * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 *
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
class Solution {
    ListNode* split(ListNode* head) {
        auto pre = head;
        auto s1 = head;
        auto s2 = head;

        while (s2 && s2->next) {
            pre = s1;
            s1 = s1->next;
            s2 = s2->next->next;
        }

        return s2 ? s1 : pre;
    }

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
    void reorderList(ListNode* head) {
        if (!head || !head->next) {
            return;
        }

        auto mid = split(head);
        auto front = head;
        auto back = mid->next;
        mid->next = nullptr;

        back = reverse(back);

        ListNode dummy;
        ListNode* tail = &dummy;

        bool is_front = true;
        while (front || back) {
            if (!back || is_front && front) {
                tail->next = front;
                tail = tail->next;
                front = front->next;
            } else {
                tail->next = back;
                tail = tail->next;
                back = back->next;
            }
            is_front = !is_front;
        }

        tail->next = nullptr;
    }
};
// @lc code=end
