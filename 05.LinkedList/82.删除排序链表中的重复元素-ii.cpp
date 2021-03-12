/*
 * @lc app=leetcode.cn id=82 lang=cpp
 *
 * [82] 删除排序链表中的重复元素 II
 *
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/description/
 *
 * algorithms
 * Medium (50.21%)
 * Likes:    464
 * Dislikes: 0
 * Total Accepted:    87.5K
 * Total Submissions: 173.8K
 * Testcase Example:  '[1,2,3,3,4,4,5]'
 *
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 *
 * 示例 1:
 *
 * 输入: 1->2->3->3->4->4->5
 * 输出: 1->2->5
 *
 *
 * 示例 2:
 *
 * 输入: 1->1->1->2->3
 * 输出: 2->3
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
   public:
    ListNode* deleteDuplicates(ListNode* head) {
        ListNode tmp;
        ListNode* tmp_tail = &tmp;

        ListNode ans;
        ListNode* ans_tail = &ans;

        auto p = head;

        while (p) {
            auto back = p->next;

            if (tmp_tail == &tmp || tmp_tail->val == p->val) {
                tmp_tail->next = p;
                tmp_tail = p;
            } else {
                // 如果只有一个结点
                if (tmp.next == tmp_tail) {
                    ans_tail->next = tmp.next;
                    ans_tail = ans_tail->next;
                }

                // 然后tmp结点变成只有一个结点的链表
                tmp.next = p;
                tmp_tail = p;
            }

            p = back;
        }

        // 如果tmp链表中只有一个结点
        if (tmp_tail == tmp.next) {
            ans_tail->next = tmp.next;
            ans_tail = ans_tail->next;
        }

        // 注意把尾巴处理一下。
        ans_tail->next = nullptr;

        return ans.next;
    }
};
// @lc code=end
