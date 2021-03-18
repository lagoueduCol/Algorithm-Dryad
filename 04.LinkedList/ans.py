# -*- coding: utf-8 -*-
#
# 思考题：如果在链表中进行查找的时候， 给定的并不是下标， 而是一个数 target， 或者是一个结点 ListNode target，
# 应该如何正确地编写这个查找函数呢？
#
# 注意：给定的参数链表头head并不是一个假头
#
# 如果给定的不是下标，而是一个具体的数值。

class ListNode:
    def __init__(self, x=0):
        self.val = x
        self.next = None

# ------------ PART 1 -----------------------

# 如果给定的不是下标，而是一个具体的数值。
def findNodeByValue(head, target):
    p = head
    while p:
        if (p.val == target):
            return p
        p = p.next

    return None

# 如果给定的是一个ListNode target
def findNodeByNode(head, target):
    p = head
    while (p and target):
        if (p == target):
            return p
        p = p.next

    return None


# ------------ PART 2 -----------------------
# 但是有时候，往往找到这个target之后，我们还需要执行一些操作。
# 比如将这个target给删除掉的操作。那么，找到target的前面一个结点
def getPrevNode(dummy, target):
    pre = dummy
    p = dummy.next

    while (p):
        if (p.val == target):
            return pre
        
        pre = p
        p = p.next
    

    return pre


# 当给定的链表不是一个带dummy head的链表的时候，我们改造成一个带dummy
# head的链表再操作
def deleteNode(head, target):
    dummy = ListNode
    dummy.next = head

    pre = getPrevNode(dummy, target)

    # 删除结点
    if (pre.next):
        pre.next = pre.next.next
    

    return dummy.next


# =========   TEST CODE ==============

# 以下是测试代码
def fromVectorToList(A):
    dummy = ListNode()
    tail = dummy

    for x in A:
        p = ListNode(x)

        tail.next = p
        tail = tail.next
    
    tail.next = None
    return dummy.next

# Main()

A = [1, 2, 3, 4, 5, 6, 7]
head = fromVectorToList(A)

for x in A:
    ret = findNodeByValue(head, x)
    assert ret and ret.val == x
    assert ret == findNodeByNode(head, ret)
