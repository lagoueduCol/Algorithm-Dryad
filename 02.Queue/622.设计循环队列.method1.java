/*
 *
 * [622] 设计循环队列
 *
 * https://leetcode-cn.com/problems/design-circular-queue/description/
 *
 * algorithms
 * Medium (42.07%)
 * Likes:    155
 * Dislikes: 0
 * Total Accepted:    43.6K
 * Total Submissions: 103.7K
 * Testcase Example:  '["MyCircularQueue","enQueue","enQueue","enQueue","enQueue","Rear","isFull","deQueue","enQueue","Rear"]\n' +
  '[[3],[1],[2],[3],[4],[],[],[],[4],[]]'
 *
 * 设计你的循环队列实现。 循环队列是一种线性数据结构，其操作表现基于
 * FIFO（先进先出）原则并且队尾被连接在队首之后以形成一个循环。它也被称为“环形缓冲器”。
 * 
 * 
 * 循环队列的一个好处是我们可以利用这个队列之前用过的空间。在一个普通队列里，一旦一个队列满了，我们就不能插入下一个元素，即使在队列前面仍有空间。但是使用循环队列，我们能使用这些空间去存储新的值。
 * 
 * 你的实现应该支持如下操作：
 * 
 * 
 * MyCircularQueue(k): 构造器，设置队列长度为 k 。
 * Front: 从队首获取元素。如果队列为空，返回 -1 。
 * Rear: 获取队尾元素。如果队列为空，返回 -1 。
 * enQueue(value): 向循环队列插入一个元素。如果成功插入则返回真。
 * deQueue(): 从循环队列中删除一个元素。如果成功删除则返回真。
 * isEmpty(): 检查循环队列是否为空。
 * isFull(): 检查循环队列是否已满。
 * 
 * 
 * 
 * 
 * 示例：
 * 
 * MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3
 * circularQueue.enQueue(1);  // 返回 true
 * circularQueue.enQueue(2);  // 返回 true
 * circularQueue.enQueue(3);  // 返回 true
 * circularQueue.enQueue(4);  // 返回 false，队列已满
 * circularQueue.Rear();  // 返回 3
 * circularQueue.isFull();  // 返回 true
 * circularQueue.deQueue();  // 返回 true
 * circularQueue.enQueue(4);  // 返回 true
 * circularQueue.Rear();  // 返回 4
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 所有的值都在 0 至 1000 的范围内；
 * 操作数将在 1 至 1000 的范围内；
 * 请不要使用内置的队列库。
 * 
 * 
 */


class MyCircularQueue {
    // 已经使用的元素个数
    private int used = 0;
    // 第一个元素所在位置
    private int front = 0;
    // rear是enQueue可在存放的位置
    // 注意开闭原则
    // [front, rear)
    private int rear = 0;
    // 循环队列最多可以存放的元素个数
    private int capacity = 0;
    // 循环队列的存储空间
    private int[] a = null;

    public MyCircularQueue(int k) {
        // 初始化循环队列
        capacity = k;
        a = new int[capacity];
    }

    public boolean enQueue(int value) {
        // 如果已经放满了
        if (used == capacity) {
            return false;
        }
        // 如果没有放满，那么a[rear]用来存放新进来的元素
        a[rear] = value;
        // rear注意取模
        rear = (rear + 1) % capacity;
        // 已经使用的空间
        used++;
        // 存放成功!
        return true;
    }

    public boolean deQueue() {
        // 如果是一个空队列，当然不能出队
        if (used == 0) {
            return false;
        }
        // 第一个元素取出
        int ret = a[front];
        // 注意取模
        front = (front + 1) % capacity;
        // 已经存放的元素减减
        used--;
        // 取出元素成功
        return true;
    }

    public int Front() {
        // 如果为空，不能取出队首元素
        if (used == 0) {
            return -1;
        }
        // 取出队首元素
        return a[front];
    }

    public int Rear() {
        // 如果为空，不能取出队尾元素
        if (used == 0) {
            return -1;
        }
        // 注意：这里不能使用rear - 1
        // 需要取模
        int tail = (rear - 1 + capacity) % capacity;
        return a[tail];
    }

    // 队列是否为空
    public boolean isEmpty() {
        return used == 0;
    }

    // 队列是否满了
    public boolean isFull() {
        return used == capacity;
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * 
 * MyCircularQueue obj = new MyCircularQueue(k);
 * 
 * boolean param_1 = obj.enQueue(value);
 * 
 * boolean param_2 = obj.deQueue();
 * 
 * int param_3 = obj.Front();
 * 
 * int param_4 = obj.Rear();
 * 
 * boolean param_5 = obj.isEmpty();
 * 
 * boolean param_6 = obj.isFull();
 */
