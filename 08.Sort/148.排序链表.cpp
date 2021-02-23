/*
 * @lc app=leetcode.cn id=148 lang=cpp
 *
 * [148] 排序链表
 *
 * https://leetcode-cn.com/problems/sort-list/description/
 *
 * algorithms
 * Medium (67.66%)
 * Likes:    960
 * Dislikes: 0
 * Total Accepted:    135.1K
 * Total Submissions: 199.6K
 * Testcase Example:  '[4,2,1,3]'
 *
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 *
 * 进阶：
 *
 *
 * 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 *
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 *
 *
 * 示例 2：
 *
 *
 * 输入：head = [-1,5,3,4,0]
 * 输出：[-1,0,3,4,5]
 *
 *
 * 示例 3：
 *
 *
 * 输入：head = []
 * 输出：[]
 *
 *
 *
 *
 * 提示：
 *
 *
 * 链表中节点的数目在范围 [0, 5 * 10^4] 内
 * -10^5 
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
  ListNode *split(ListNode *head) {
    auto s1 = head;
    auto s2 = head;
    auto pre = s1;
    while (s2 && s2->next) {
      pre = s1;
      s1 = s1->next;
      s2 = s2->next->next;
    }
    return s2 ? s1 : pre;
  }

public:
  ListNode *sortList(ListNode *head) {
    if (!head || !head->next) {
      return head;
    }

    auto mid = split(head);
    auto back = mid->next;
    mid->next = nullptr;

    auto i = sortList(head);
    auto j = sortList(back);

    ListNode dummy;
    ListNode *tail = &dummy;

    while (i || j) {
      if (!j || i && i->val <= j->val) {
        tail->next = i;
        tail = i;
        i = i->next;
      } else {
        tail->next = j;
        tail = j;
        j = j->next;
      }
    }
    tail->next = nullptr;

    return dummy.next;
  }
};
// @lc code=end
