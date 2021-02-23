# -*- coding: utf-8 -*-

"""
868. 子数组的最大平均值
中文
English
给定一个由n个整数组成的数组，找到给定长度k的连续子数组，该子数组具有最大平均值。你需要输出最大平均值。

样例
Example1

Input:  nums = [1,12,-5,-6,50,3] and k = 4
Output: 12.75
Explanation:
Maximum average is (12-5-6+50)/4 = 51/4 = 12.75
Example2

Input:  nums = [4,2,1,3,3] and k = 2
Output: 3.00
Explanation:
Maximum average is (3+3)/2 = 6/2 = 3.00
注意事项
1 <= k <= n <= 30,000.
给定数组的元素范围是[-10,000, 10,000]。

https://www.lintcode.com/problem/maximum-average-subarray/
"""
# -*- coding: utf-8 -*-

"""
868. 子数组的最大平均值
中文
English
给定一个由n个整数组成的数组，找到给定长度k的连续子数组，该子数组具有最大平均值。你需要输出最大平均值。

样例
Example1

Input:  nums = [1,12,-5,-6,50,3] and k = 4
Output: 12.75
Explanation:
Maximum average is (12-5-6+50)/4 = 51/4 = 12.75
Example2

Input:  nums = [4,2,1,3,3] and k = 2
Output: 3.00
Explanation:
Maximum average is (3+3)/2 = 6/2 = 3.00
注意事项
1 <= k <= n <= 30,000.
给定数组的元素范围是[-10,000, 10,000]。

https://www.lintcode.com/problem/maximum-average-subarray/
"""

class Solution:
    # 求给定长度的子数组的最大和
    def maxSumInKLength(self, A, k):
        N = 0 if not A else len(A)
        s = 0
        ans = float('-inf')
        for i in range(0, N):
            s += A[i]
            if i < k - 1:
                continue
            ans = max(ans, s)
            # 现在处理的区间是[i + 1 - k, i + 1)
            # 窗口滑动时，现在A[i + 1 - k]元素要扔掉了
            # 所以应该把A[i + 1 - k]从s里面减掉
            s -= A[i+1-k]
        return ans

    def findMaxAverage(self, A, k):
        # 我们先求出给定长度的子数组的最大和，然后
        # 再除以给定长度
        return self.maxSumInKLength(A, k) / float(k)
