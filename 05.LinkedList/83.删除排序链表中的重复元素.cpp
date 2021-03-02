/*
 * @lc app=leetcode.cn id=83 lang=cpp
 *
 * [83] 删除排序链表中的重复元素
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
    ListNode* deleteDuplicates(ListNode* head) {
        ListNode dummy, *tail = &dummy;

        auto p = head;

        while (p) {
            auto back = p->next;

            if (tail == &dummy || tail->val != p->val) {
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
