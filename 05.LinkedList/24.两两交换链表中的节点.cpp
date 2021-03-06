/*
 * @lc app=leetcode.cn id=24 lang=cpp
 *
 * [24] 两两交换链表中的节点
 *
 * https://leetcode-cn.com/problems/swap-nodes-in-pairs/description/
 *
 * algorithms
 * Medium (69.06%)
 * Likes:    836
 * Dislikes: 0
 * Total Accepted:    226K
 * Total Submissions: 326.5K
 * Testcase Example:  '[1,2,3,4]'
 *
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 *
 *
 * 示例 2：
 *
 *
 * 输入：head = []
 * 输出：[]
 *
 *
 * 示例 3：
 *
 *
 * 输入：head = [1]
 * 输出：[1]
 *
 *
 *
 *
 * 提示：
 *
 *
 * 链表中节点的数目在范围 [0, 100] 内
 * 0
 *
 *
 *
 *
 * 进阶：你能在不修改链表节点值的情况下解决这个问题吗?（也就是说，仅修改节点本身。）
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
    ListNode *merge(ListNode *odd, ListNode *even) {
        ListNode dummy, *tail = &dummy;
        bool isEven = true;
        while (odd || even) {
            // 注意这里if 条件的写法!
            if (!odd || isEven && even) {
                tail->next = even;
                even = even->next;
            } else {
                tail->next = odd;
                odd = odd->next;
            }
            tail = tail->next;
            isEven = !isEven;
        }
        tail->next = nullptr;
        return dummy.next;
    }

   public:
    ListNode *swapPairs(ListNode *head) {
        ListNode odd, *odd_tail = &odd;
        ListNode even, *even_tail = &even;

        auto p = head;
        int idx = 1;
        while (p) {
            auto back = p->next;
            if (idx & 0x01) {
                odd_tail->next = p;
                odd_tail = p;
            } else {
                even_tail->next = p;
                even_tail = p;
            }
            p = back;
            idx++;
        }
        odd_tail->next = nullptr;
        even_tail->next = nullptr;

        return merge(odd.next, even.next);
    }
};
// @lc code=end
