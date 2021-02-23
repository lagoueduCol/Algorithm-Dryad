# -*- coding: utf-8 -*-

from random import random

class increaseQueue:
    def __init__(self, N):
        self.head = 0
        self.tail = 0
        self.used = 0
        self.totoalSize = N
        self.Q = [0]*N

    def back(self):
        return (self.tail - 1 + self.totoalSize) % self.totoalSize

    # 环形队列
    # 这里的tail指向能存放的位置
    # [head, tail)
    def push(self, v):
        # 递增队列
        # 注意相等的元素仍然是需要存在队列中的
        while self.used > 0 and self.Q[self.back()] > v:
            self.tail = self.back()
            self.used -= 1

        self.Q[self.tail] = v
        self.used += 1
        self.tail = (self.tail + 1) % self.totoalSize

    def front(self):
        return self.Q[self.head]

    # 注意这里弹元素的时候
    # 1. 有元素可以弹
    # 2. 队首元素等于value.
    def pop(self, v):
        if self.used > 0 and self.Q[self.head] == v:
            self.head = (self.head + 1) % self.totoalSize
            self.used -= 1

# 求一个数组，连续子数组长度 <= k的最大和
# 这里仍然采用落差法：即利用当前的值减去之前
# 的最小值。
# 所以接下来，需要求k区间的最小值，我们可以认为是在求
# 滑动窗口的最小值。可以利用单调队列来操作。
def maxSumInLength(A, k):
    N = len(A) if A else 0
    # 对于无效输入，这里直接返回0
    if N == 0 or k <= 0:
        return 0

    B = [0] * N
    B[0] = A[0]
    for i in range(1, N):
        B[i] = B[i - 1] + A[i]

    # 利用落差法在B数组上进行求最大落差时
    # B[i] - B[j]
    # 表示的是[j + 1 .... i]
    # 即[j + 1 ... i + 1)
    # 其长度为i - j
    # 如果希望i - j <= k
    # 那么i - k <= j
    # 前面的查询范围是[i - k, .... i - 0]
    # 这个长度就是 k + 1
    # 验证这个比较简单，考虑k = 1的时候的情况
    # 如果不k++，那么将找不到数组里面的最大值。
    k += 1

    Q = increaseQueue(N)
    ans = float('-inf')
    for i in range(0, N):
        Q.push(B[i])
        # 这里拿到窗口里面的最小值
        # 落差法，利用当前值减去前面的最小值
        ans = max(ans, B[i] - Q.front())
        if i + 1 - k >= 0:
            Q.pop(B[i + 1 - k])
        else:
            ans = max(ans, B[i])

    return ans

# 没有测试数据，利用暴力算法来验证
def bf(A, k):
    N = len(A) if A else 0
    B = [0] * N

    # B数组是A数组的前缀和
    B[0] = A[0]
    for i in range(1, N):
        B[i] = B[i - 1] + A[i]

    ans = 0
    for i in range(0, N):
        if i + 1 <= k:
            ans = max(ans, B[i])

        j = i - 1
        # [0 .... j] 前面的和是B[j]
        # B[i] - B[j] 表示的是[j + 1, ... , i]
        # 那么范围就是[j + 1, ... , i + 1)
        # 长度就是i - j
        while i - j <= k and j >= 0:
            # assert(i >= 0 && i < B.size())
            # assert(j >= 0 && j < B.size())
            delta = B[i] - B[j]
            ans = max(ans, delta)
            j -= 1

    return ans

N = 0
k = 0
testCases = 100
while testCases > 0:
    N = int(random() * 1000) 
    N += 1

    k = int(random() * 2000)
    if k == 0:
        k = 1
      
    A = [0] * N
    for i in range(0, N):
        A[i] = int(random()) - 50

    x = bf(A, k)
    y = maxSumInLength(A, k)
    assert x == y
    testCases -= 1
    