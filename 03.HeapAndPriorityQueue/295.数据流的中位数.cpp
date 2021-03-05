/*
 * @lc app=leetcode.cn id=295 lang=cpp
 *
 * [295] 数据流的中位数
 *
 * https://leetcode-cn.com/problems/find-median-from-data-stream/description/
 *
 * algorithms
 * Hard (51.11%)
 * Likes:    373
 * Dislikes: 0
 * Total Accepted:    31.7K
 * Total Submissions: 62.1K
 * Testcase Example:
 '["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]\n' +
  '[[],[1],[2],[],[3],[]]'
 *
 * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 *
 * 例如，
 *
 * [2,3,4] 的中位数是 3
 *
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 *
 * 设计一个支持以下两种操作的数据结构：
 *
 *
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 *
 *
 * 示例：
 *
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 *
 * 进阶:
 *
 *
 * 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
 * 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
 *
 *
 */

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

// @lc code=start
class MedianFinder {
    // 大顶堆
    priority_queue<int> front;
    // 小堆
    priority_queue<int, vector<int>, greater<int> > back;

    // 把数据分为两段，前面的小，后面的大
    // 为了方便把数字从前面移到后面-> 总是移动前面的最大的到后面
    // 为了方便把数字从后面移到前面-> 总是移动后面最小的到前面
    // 所以数据的前半部分用大堆
    // 数据的后半部分用小堆

   public:
    /** initialize your data structure here. */
    MedianFinder() {}

    void addNum(int num) {
        // 我们总是把数字加到前面
        // 如果前面的比后面的多
        // 那么就把前面较大的取出来，放到后面
        front.push(num);
        while (front.size() > back.size()) {
            auto l = front.top();
            front.pop();
            back.push(l);
        }

        // 再看一下大小
        // 如果前半部分有数大于后半部分，那么需要交换
        if (!front.empty() && !back.empty()) {
            while (back.top() < front.top()) {
                auto f = front.top();
                front.pop();
                auto b = back.top();
                back.pop();
                front.push(b);
                back.push(f);
            }
        }
    }

    double findMedian() {
        // 如果两个集合相等
        if (front.size() == back.size()) {
            if (!front.empty()) {
                return (double)(front.top() + back.top()) / 2.0;
            } else {
                return 0;
            }
        } else {
            return back.empty() ? 0 : back.top();
        }
    }
};

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder* obj = new MedianFinder();
 * obj->addNum(num);
 * double param_2 = obj->findMedian();
 */
// @lc code=end
