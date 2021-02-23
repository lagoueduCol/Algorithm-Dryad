#
# @lc app=leetcode.cn id=81 lang=python
#
# [81] 搜索旋转排序数组 II
#
# https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii/description/
#
# algorithms
# Medium (36.74%)
# Likes:    283
# Dislikes: 0
# Total Accepted:    52.9K
# Total Submissions: 143.9K
# Testcase Example:  '[2,5,6,0,0,1,2]\n0'
#
# 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
# 
# ( 例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2] )。
# 
# 编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false。
# 
# 示例 1:
# 
# 输入: nums = [2,5,6,0,0,1,2], target = 0
# 输出: true
# 
# 
# 示例 2:
# 
# 输入: nums = [2,5,6,0,0,1,2], target = 3
# 输出: false
# 
# 进阶:
# 
# 
# 这是 搜索旋转排序数组 的延伸题目，本题中的 nums  可能包含重复元素。
# 这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？
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
            return v > A[0]

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
            if A[l] == x or A[r-1] == x or A[m] == x:
                return True
            if A[l] == A[m]:
                l += 1
                continue
            mov = getC(m)
            if mov < 0:
                l = m + 1
            else:
                r = m

        return False if (l >= N or A[l] != x) else True
# @lc code=end

