/*
 * @lc app=leetcode.cn id=21 lang=cpp
 *
 * [21] 合并两个有序链表
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
   public:
    ListNode* mergeTwoLists(ListNode* l1, ListNode* l2) {
        ListNode dummy, *tail = &dummy;
        while (l1 || l2) {
            if (!l2 || l1 && l1->val < l2->val) {
                auto back = l1->next;

                tail->next = l1;
                tail = l1;

                l1 = back;
            } else {
                auto back = l2->next;

                tail->next = l2;
                tail = l2;

                l2 = back;
            }
        }
        tail->next = nullptr;
        return dummy.next;
    }
};
// @lc code=end
