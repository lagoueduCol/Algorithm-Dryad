/*
 * @lc app=leetcode.cn id=203 lang=cpp
 *
 * [203] 移除链表元素
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
class Solution {
   public:
    ListNode* removeElements(ListNode* head, int val) {
        ListNode dummy, *tail = &dummy;

        auto p = head;

        while (p) {
            auto back = p->next;

            if (p->val != val) {
                tail->next = p;
                tail = p;
            }

            p = back;
        }
        tail->next = nullptr;

        return dummy.next;
    }
};
// @lc code=end
