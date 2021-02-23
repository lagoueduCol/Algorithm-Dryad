#
# @lc app=leetcode.cn id=69 lang=python
#
# [69] x 的平方根
#
# https://leetcode-cn.com/problems/sqrtx/description/
#
# algorithms
# Easy (39.16%)
# Likes:    585
# Dislikes: 0
# Total Accepted:    253.6K
# Total Submissions: 647.6K
# Testcase Example:  '4'
#
# 实现 int sqrt(int x) 函数。
# 
# 计算并返回 x 的平方根，其中 x 是非负整数。
# 
# 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
# 
# 示例 1:
# 
# 输入: 4
# 输出: 2
# 
# 
# 示例 2:
# 
# 输入: 8
# 输出: 2
# 说明: 8 的平方根是 2.82842..., 
# 由于返回类型是整数，小数部分将被舍去。
# 
# 
#

# @lc code=start
class Solution(object):
    def mySqrt(self, x):
        """
        :type x: int
        :rtype: int
        """

        l = 0
        r = x

        def getC(x, m):
            if m  * m < x:
                return -1
            elif m * m == x:
                return 0
            else:
                return 1

        while l < r:
            m = l + ((r-l)>>1)
            mov = getC(x, m)
            if mov < 0:
                l = m + 1
            else:
                r = m
        
        # 按照题意，这里不能直接返回l
        # 比如当sqrt(4)的时候，应该返回2
        # 此时l == 2但是当执行
        # sqrt(5)的时候，此时l = 3
        # 由于需要去掉小数部分，所以这里需要返回l - 1
        if l * l == x:
            return l
        return l - 1

# @lc code=end

