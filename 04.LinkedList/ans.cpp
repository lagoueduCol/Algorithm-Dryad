
#include <assert.h>
#include <limits.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include <algorithm>
#include <iostream>
#include <numeric>
#include <queue>
#include <string>
#include <unordered_map>
#include <unordered_set>
#include <vector>

using namespace std;

struct ListNode {
    int val = 0;
    ListNode *next = nullptr;
    ListNode() {}
    ListNode(int x) : val(x), next(NULL) {}
};

/*
 * 思考题：如果在链表中进行查找的时候，
 * 给定的并不是下标，而是一个数 target，
 * 或者是一个结点 ListNode target，
 * 应该如何正确地编写这个查找函数呢？
 *
 * 注意：给定的参数链表头head并不是一个假头
 */

// ------------ PART 1 -----------------------

// 如果给定的不是下标，而是一个具体的数值。
ListNode *findNode(ListNode *head, int target) {
    auto p = head;
    while (p) {
        if (p->val == target) {
            return p;
        }
        p = p->next;
    }
    return nullptr;
}

// 如果给定的是一个ListNode *target
ListNode *findNode(ListNode *head, ListNode *target) {
    auto p = head;
    while (p && target) {
        if (p == target) {
            return p;
        }
        p = p->next;
    }
    return nullptr;
}

// ------------ PART 2 -----------------------

// 但是有时候，往往找到这个target之后，我们还需要执行一些操作。
// 比如将这个target给删除掉的操作。那么，找到target的前面一个结点
ListNode *getPrevNode(ListNode *dummy, int target) {
    auto pre = dummy;
    auto p = dummy->next;

    while (p) {
        if (p->val == target) {
            return pre;
        }
        pre = p;
        p = p->next;
    }

    return pre;
}

// 当给定的链表不是一个带dummy head的链表的时候，我们改造成一个带dummy
// head的链表再操作
ListNode *deleteNode(ListNode *head, int target) {
    ListNode dummy;
    dummy.next = head;

    auto pre = getPrevNode(&dummy, target);

    // 删除结点
    if (pre->next) {
        pre->next = pre->next->next;
    }

    return dummy.next;
}

// =========   TEST CODE ==============

// 以下是测试代码
ListNode *fromVectorToList(const vector<int> &A) {
    ListNode dummy;
    ListNode *tail = &dummy;

    for (auto &x : A) {
        auto p = new ListNode(x);

        tail->next = p;
        tail = tail->next;
    }

    tail->next = nullptr;
    return dummy.next;
}

int main(void) {
    vector<int> A{1, 2, 3, 4, 5, 6, 7};
    auto head = fromVectorToList(A);

    for (auto x : A) {
        auto ret = findNode(head, x);
        assert(ret->val == x);
        assert(ret == findNode(head, ret));
    }

    return 0;
}