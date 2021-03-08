#
# @lc app=leetcode.cn id=707 lang=python
#
# [707] 设计链表
#
# https://leetcode-cn.com/problems/design-linked-list/description/
#
# algorithms
# Medium (30.23%)
# Likes:    213
# Dislikes: 0
# Total Accepted:    38.9K
# Total Submissions: 128.5K
# Testcase Example:  '["MyLinkedList","addAtHead","addAtTail","addAtIndex","get","deleteAtIndex","get"]\n' +
  '[[],[1],[3],[1,2],[1],[1],[1]]'
#
# 设计链表的实现。您可以选择使用单链表或双链表。单链表中的节点应该具有两个属性：val 和 next。val 是当前节点的值，next
# 是指向下一个节点的指针/引用。如果要使用双向链表，则还需要一个属性 prev 以指示链表中的上一个节点。假设链表中的所有节点都是 0-index 的。
# 
# 在链表类中实现这些功能：
# 
# 
# get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回-1。
# addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
# addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。
# addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val  的节点。如果 index
# 等于链表的长度，则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
# deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。
# 
# 
# 
# 
# 示例：
# 
# MyLinkedList linkedList = new MyLinkedList();
# linkedList.addAtHead(1);
# linkedList.addAtTail(3);
# linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
# linkedList.get(1);            //返回2
# linkedList.deleteAtIndex(1);  //现在链表是1-> 3
# linkedList.get(1);            //返回3
# 
# 
# 
# 
# 提示：
# 
# 
# 所有val值都在 [1, 1000] 之内。
# 操作次数将在  [1, 1000] 之内。
# 请不要使用内置的 LinkedList 库。
# 
# 
#

# @lc code=start


#单链表结点的定义
class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None

# 实现单链表
# 1. 假设链表中的所有节点都是 0-index的。

class MyLinkedList(object):
    def __init__(self):
        self.dummy = ListNode(0)
        self.tail = self.dummy
        self.length = 0

    def getPrevNode(self, index):
        front = self.dummy.next
        back = self.dummy
        for i in range(index):
            back = front
            front = front.next
        return back

    def get(self, index):
        if index < 0 or index >= self.length:
            return -1
        return self.getPrevNode(index).next.val

    def addAtHead(self, val):
        p = ListNode(val)
        p.next = self.dummy.next
        self.dummy.next = p

        # 注意，这里一定要记得修改tail
        if self.tail == self.dummy:
            self.tail = p

        self.length += 1

    def addAtTail(self, val):
        self.tail.next = ListNode(val)
        self.tail = self.tail.next
        self.length += 1

    # 在链表中的第 index 个节点之前添加值为 val  的节点。
    # 1. 如果 index 等于链表的长度，则该节点将附加到链表的末尾。
    # 2. 如果 index 大于链表长度，则不会插入节点。
    # 3. 如果index小于0，则在头部插入节点。
    def addAtIndex(self, index, val):
        if index > self.length:
            return
        elif index == self.length:
            self.addAtTail(val)
            return
        elif index <= 0:
            self.addAtHead(val)
            return

        pre = self.getPrevNode(index)
        p = ListNode(val)
        p.next = pre.next
        pre.next = p

        # NOTE: here tail has been changed
        self.length += 1

    # 如果索引 index 有效，则删除链表中的第 index 个节点。
    def deleteAtIndex(self, index):
        if index < 0 or index >= self.length:
            return

        pre = self.getPrevNode(index)

        # NOTE:  change tail
        if self.tail == pre.next:
            self.tail = pre

        self.length -= 1

        pre.next = pre.next.next



# Your MyLinkedList object will be instantiated and called as such:
# obj = MyLinkedList()
# param_1 = obj.get(index)
# obj.addAtHead(val)
# obj.addAtTail(val)
# obj.addAtIndex(index,val)
# obj.deleteAtIndex(index)
# @lc code=end

