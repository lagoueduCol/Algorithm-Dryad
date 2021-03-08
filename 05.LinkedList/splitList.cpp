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
   ListNode *findMiddleNode(ListNode *head) {
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
  pair<ListNode*, ListNode*> split(ListNode *head) {
    // 这里获取了链表的中间结点
    auto mid = findMiddleNode(head);
    // 拿到链表的中间结点之后，可以得到链表的后半部分的开头
    auto back = mid->next;
    // 把链表拆分为两半
    mid->next = nullptr;
    // 返回两个链表的头部
    return {head, back};
  }
};
// @lc code=end
