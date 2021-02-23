#
# @lc app=leetcode.cn id=33 lang=python
#
# [33] 搜索旋转排序数组
#
# https://leetcode-cn.com/problems/search-in-rotated-sorted-array/description/
#
# algorithms
# Medium (40.44%)
# Likes:    1176
# Dislikes: 0
# Total Accepted:    219K
# Total Submissions: 541.4K
# Testcase Example:  '[4,5,6,7,0,1,2]\n0'
#
# 升序排列的整数数组 nums 在预先未知的某个点上进行了旋转（例如， [0,1,2,4,5,6,7] 经旋转后可能变为 [4,5,6,7,0,1,2]
# ）。
# 
# 请你在数组中搜索 target ，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
# 
# 
# 
# 示例 1：
# 
# 
# 输入：nums = [4,5,6,7,0,1,2], target = 0
# 输出：4
# 
# 
# 示例 2：
# 
# 
# 输入：nums = [4,5,6,7,0,1,2], target = 3
# 输出：-1
# 
# 示例 3：
# 
# 
# 输入：nums = [1], target = 0
# 输出：-1
# 
# 
# 
# 
# 提示：
# 
# 
# 1 
# -10^4 
# nums 中的每个值都 独一无二
# nums 肯定会在某个点上旋转
# -10^4 
# 
# 
#

# @lc code=start

# -*- coding: utf-8 -*-
#
# 映射的总体思路:
#
# 1. 如果x在数组左边，比如
# [4,5,6,7,0,1,2], x = 5
# 那么把数组成处理成
# [4,5,6,7,inf,inf,inf]再进行查找
#
# 2. 如果x在数组右边，比如：
# [4,5,6,7,0,1,2], x = 1
# 那么把左边部分的值全部设置成-inf
# [-inf,-inf,-inf,-inf,0,1,2]
#
# 更进一步的，我们将数组里面的值全部变成
# {-1小于, 0等于, 1大于}
#
# 再进行二分

class Solution(object):
    def search(self, A, x):

        N = len(A) if A else 0
        # 一个数组经过旋转之后，可以分为左边与右边
        # 左边是一个升序
        # 右边也是一个升序
        # 判断一个元素在左边还是右边。
        # 一个元素如果大于等于A[0] => 那么就在左边
        def is_left(v):
            return v >= A[0]

        x_pos = is_left(x)

        # 映射函数 => 返回A[m]与x的关系
        # {-1 = 小于； 0 = 相等； +1 = 大于x}
        #
        # 首先要判断x所在位置
        # 1. x在数组的左边
        #    a) 如果A[m]在数组的右边 -> 返回1
        #    b) 如果A[m]也在数组的左边 -> 根据A[m]与x的大小返回{-1,0,1}
        # 2. x在数组的右边
        #    c) 如果A[m]在数组的左边 -> 返回-1
        #    d) 如果A[m]也在数组的右边 -> 根据A[m]与x的大小返回{-1,0,1}
        #
        # 通过a), b), c), d)四种情况，我们可以发现
        # 在不同一边的时候，可以直接根据在左边还是在右边决定返回值。
        def getC(m):
            m_pos = is_left(A[m])
            # 都在同一边，那么直接比较大小
            if x_pos == m_pos:
                return 0 if A[m] == x else  -1 if A[m] < x else 1

            # 如果x在数组左边，那么m在数组右边
            # => 由于我们要把整个数组映射成一个有序的数组
            #    右边更小的部分我们要映射成 > x
            #    [4,5,6,7,0,1,2], x = 5 => [4,5,6,7,inf,inf,inf], x = 5
            #    映射成C[]数组就是[-1, 0, 1, 1, 1, 1, 1, 1]
            #    所以此时返回1
            # 如果x在数组右边，m在数组左边，左边的都设置成更小的。
            #    比如[4,5,6,7,0,1,2], x = 1
            # 那么把左边部分的值全部设置成-inf
            #    [-inf,-inf,-inf,-inf,0,1,2]
            #    映射成C[]数组就是[-1, -1, -1, -1, -1, 0, 1];
            #    所以m在左边 && x在右边，返回-1
            return 1 if x_pos else -1
        
        l = 0
        r = N
        while l < r:
            m = l + ((r-l)>>1)
            mov = getC(m)
            if mov < 0:
                l = m + 1
            else:
                r = m
        
        return -1 if (l >= N or A[l] != x) else l
# @lc code=end

