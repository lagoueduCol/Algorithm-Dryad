#
#
# [622] 设计循环队列
#
# https://leetcode-cn.com/problems/design-circular-queue/description/
#
# algorithms
# Medium (42.07%)
# Likes:    155
# Dislikes: 0
# Total Accepted:    43.6K
# Total Submissions: 103.7K
# Testcase Example:  '["MyCircularQueue","enQueue","enQueue","enQueue","enQueue","Rear","isFull","deQueue","enQueue","Rear"]\n' +
  '[[3],[1],[2],[3],[4],[],[],[],[4],[]]'
#
# 设计你的循环队列实现。 循环队列是一种线性数据结构，其操作表现基于
# FIFO（先进先出）原则并且队尾被连接在队首之后以形成一个循环。它也被称为“环形缓冲器”。
# 
# 
# 循环队列的一个好处是我们可以利用这个队列之前用过的空间。在一个普通队列里，一旦一个队列满了，我们就不能插入下一个元素，即使在队列前面仍有空间。但是使用循环队列，我们能使用这些空间去存储新的值。
# 
# 你的实现应该支持如下操作：
# 
# 
# MyCircularQueue(k): 构造器，设置队列长度为 k 。
# Front: 从队首获取元素。如果队列为空，返回 -1 。
# Rear: 获取队尾元素。如果队列为空，返回 -1 。
# enQueue(value): 向循环队列插入一个元素。如果成功插入则返回真。
# deQueue(): 从循环队列中删除一个元素。如果成功删除则返回真。
# isEmpty(): 检查循环队列是否为空。
# isFull(): 检查循环队列是否已满。
# 
# 
# 
# 
# 示例：
# 
# MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3
# circularQueue.enQueue(1);  // 返回 true
# circularQueue.enQueue(2);  // 返回 true
# circularQueue.enQueue(3);  // 返回 true
# circularQueue.enQueue(4);  // 返回 false，队列已满
# circularQueue.Rear();  // 返回 3
# circularQueue.isFull();  // 返回 true
# circularQueue.deQueue();  // 返回 true
# circularQueue.enQueue(4);  // 返回 true
# circularQueue.Rear();  // 返回 4
# 
# 
# 
# 提示：
# 
# 
# 所有的值都在 0 至 1000 的范围内；
# 操作数将在 1 至 1000 的范围内；
# 请不要使用内置的队列库。
# 
# 
#

class MyCircularQueue(object):
    def __init__(self, k):
        """
        :type k: int
        """
        # 第一个元素所在位置
        self.front = 0
        # rear是enQueue可在存放的位置
        # 注意开闭原则
        # [front, rear)
        self.rear = 0
        # 已经使用的元素个数
        self.used = 0
        # 循环队列的存储空间
        self.a = [0] * k
        # 记录最大空量
        self.capacity = k

    def enQueue(self, value):
        """
        :type value: int
        :rtype: bool
        """
        # 如果已经满了，
        if self.isFull():
            return False
        # 如果没有放满，那么a[rear]用来存放新进来的元素
        self.a[self.rear] = value
        # rear向前进
        self.rear = (self.rear + 1) % self.capacity
        # 已经使用的空间
        self.used += 1
        return True

    def deQueue(self):
        """
        :rtype: bool
        """
        # 如果是一个空队列，当然不能出队
        if self.isEmpty():
            return False
        # 注意取模
        self.front = (self.front + 1) % self.capacity
        # 已经存放的元素减减
        self.used -= 1
        # 取出元素成功
        return True

    def Front(self):
        """
        :rtype: int
        """
        # 如果为空，不能取出队首元素
        if self.isEmpty():
            return -1
        # 取出队首元素
        return self.a[self.front]

    def Rear(self):
        """
        :rtype: int
        """
        # 如果为空，不能取出队尾元素
        if self.isEmpty():
            return -1
        # 注意：这里不能使用rear - 1
        # 需要取模
        tail = (self.rear - 1 + self.capacity) % self.capacity
        return self.a[tail]

    def isEmpty(self):
        """
        :rtype: bool
        """
        return self.used == 0

    def isFull(self):
        """
        :rtype: bool
        """
        return self.used == self.capacity


# Your MyCircularQueue object will be instantiated and called as such:
# obj = MyCircularQueue(k)
# param_1 = obj.enQueue(value)
# param_2 = obj.deQueue()
# param_3 = obj.Front()
# param_4 = obj.Rear()
# param_5 = obj.isEmpty()
# param_6 = obj.isFull()

