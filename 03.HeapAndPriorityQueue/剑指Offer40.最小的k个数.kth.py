"""
输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。

示例 1：

输入：arr = [3,2,1], k = 2
输出：[1,2] 或者 [2,1]
示例 2：

输入：arr = [0,1,2,1], k = 1
输出：[0]
 

限制：

0 <= k <= arr.length <= 10000
0 <= arr[i] <= 10000


// 测试平台链接：https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/submissions/
"""

class Solution(object):
    # 这里采用类似快排的思想来找一个数组中第k小的元素
    # 这里采用的是三路快排的思路
    def kth(self, a, b, e, k):
        if b >= e:
            return 0
        
        if b + 1 >= e:
            return a[b]
        # 首先找到一个数，将这个数组分成三个部分
        m = b + ((e-b)/2)
        x = a[m]

        l = b
        i = b
        r = e - 1
        # 这里会有四个区间
        # [b, l)[l, i)[i, r](r..N)
        # [b, l) 是小于x的数所在区间
        # [l, i) 是等于x的数所在区间
        # [i..r]是未处理的数据所在的区间
        # (r...N)是大于x的数据的区间
        while i <= r:
            if a[i] < x:
                a[l], a[i] = a[i], a[l]
                i += 1
                l += 1
            elif a[i] == x:
                i += 1
            else:
                a[r], a[i] = a[i], a[r]
                r -= 1
        
        # 由于数据已经被分成了三个部分。那么需要根据这三个部分来判断
        # kth在哪个部分里面
        # 如果kth在前面的那一部分
        if l - b > k:
            return self.kth(a, b, l, k)
        # 如果kth在中间等于x的那一部分
        if i - b >= k:
            return x
        # 如果kth在大于x的那一部分
        return self.kth(a, i, e, k - (i - b))

    def getLeastNumbers(self, arr, k):
        """
        :type arr: List[int]
        :type k: int
        :rtype: List[int]
        """

        ans = []
        kthValue = self.kth(arr, 0, len(arr), k)
        kthCnt = 0

        # 首先将小于kth的那部分数放到ans里面。
        for x in arr:
            if x < kthValue:
                ans.append(x)
            if x == kthValue:
                kthCnt += 1
        # 如果不足，那么并且有多余的，等于kthValue的元素
        # append它
        # 比如[1, 2, 2, 2, 2, 2, 2, 2, 2] k = 2
        # 前面的处理，只会把[1]放到ans里面。
        # 很明显，我们还需要把kthValue=2放到ans里面。
        while len(ans) < k and kthCnt > 0:
            ans.append(kthValue)
            kthCnt -= 1

        return ans
