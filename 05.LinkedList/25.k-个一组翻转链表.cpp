/*
 * @lc app=leetcode.cn id=25 lang=cpp
 *
 * [25] K 个一组翻转链表
 *
 * https://leetcode-cn.com/problems/reverse-nodes-in-k-group/description/
 *
 * algorithms
 * Hard (64.03%)
 * Likes:    932
 * Dislikes: 0
 * Total Accepted:    139.4K
 * Total Submissions: 216.8K
 * Testcase Example:  '[1,2,3,4,5]\n2'
 *
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 *
 * k 是一个正整数，它的值小于或等于链表的长度。
 *
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * 进阶：
 *
 *
 * 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[2,1,4,3,5]
 *
 *
 * 示例 2：
 *
 *
 * 输入：head = [1,2,3,4,5], k = 3
 * 输出：[3,2,1,4,5]
 *
 *
 * 示例 3：
 *
 *
 * 输入：head = [1,2,3,4,5], k = 1
 * 输出：[1,2,3,4,5]
 *
 *
 * 示例 4：
 *
 *
 * 输入：head = [1], k = 1
 * 输出：[1]
 *
 *
 *
 *
 *
 * 提示：
 *
 *
 * 列表中节点的数量在范围 sz 内
 * 1
 * 0
 * 1
 *
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

        return ans.next;
    }
};
// @lc code=end
