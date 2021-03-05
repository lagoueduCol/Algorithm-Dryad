#
# @lc app=leetcode.cn id=295 lang=python
#
# [295] 数据流的中位数
#
# https://leetcode-cn.com/problems/find-median-from-data-stream/description/
#
# algorithms
# Hard (51.11%)
# Likes:    373
# Dislikes: 0
# Total Accepted:    31.7K
# Total Submissions: 62.1K
# Testcase Example:  '["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]\n' +
  '[[],[1],[2],[],[3],[]]'
#
# 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
# 
# 例如，
# 
# [2,3,4] 的中位数是 3
# 
# [2,3] 的中位数是 (2 + 3) / 2 = 2.5
# 
# 设计一个支持以下两种操作的数据结构：
# 
# 
# void addNum(int num) - 从数据流中添加一个整数到数据结构中。
# double findMedian() - 返回目前所有元素的中位数。
# 
# 
# 示例：
# 
# addNum(1)
# addNum(2)
# findMedian() -> 1.5
# addNum(3) 
# findMedian() -> 2
# 
# 进阶:
# 
# 
# 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
# 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
# 
# 
#

import heapq
# 构建
heapq.heapify(heap)
# pop
cur = heapq.heappop(heap)
# push
heapq.heappush(heap, cur.next)

# @lc code=start

# 把数据分为两段，前面的小，后面的大
# 为了方便把数字从前面移到后面-> 总是移动前面的最大的到后面
# 为了方便把数字从后面移到前面-> 总是移动后面最小的到前面
# 所以数据的前半部分用大堆
# 数据的后半部分用小堆

class MedianFinder(object):

    def __init__(self):
        """
        initialize your data structure here.
        """

        self.front = []
        self.back = []


    def addNum(self, num):
        """
        :type num: int
        :rtype: None
        """
        # 不管三七二十一，先加到前面再说
        # 注意，front是用负数来表示大堆的
        heapq.heappush(self.front, 0 - num)
        # 我们总是想让后面比前面多一个
        while len(self.front) > len(self.back):
            l = heapq.heappop(self.front)
            # 注意，front是用负数来表示大堆的
            heapq.heappush(self.back, 0 - l)
        
        # 我们总是想让后面的数比前面的大
        if len(self.front) > 0 and len(self.back) > 0:
            while self.back[0] < 0 - self.front[0]:
                f = heapq.heappop(self.front)
                b = heapq.heappop(self.back)
                heapq.heappush(self.back, 0 - f)
                heapq.heappush(self.front, 0 - b)

    def findMedian(self):
        """
        :rtype: float
        """

        if len(self.back) == 0:
            return 0
        
        if len(self.back) > len(self.front):
            return self.back[0]
        
        f = 0 - self.front[0]
        b = self.back[0]
        return float(f + b) / 2.0



# Your MedianFinder object will be instantiated and called as such:
# obj = MedianFinder()
# obj.addNum(num)
# param_2 = obj.findMedian()
# @lc code=end

