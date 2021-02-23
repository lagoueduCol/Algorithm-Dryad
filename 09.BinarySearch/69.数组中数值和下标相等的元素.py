// 测试平台: https://www.acwing.com/problem/content/65/
class Solution(object):
    def getNumberSameAsIndex(self, A):
        """
        :type A: List[int]
        :rtype: int
        """
        if not A or len(A) == 0:
            return -1
        l = 0
        r = len(A)
        def getC(A, i):
            v = A[i]
            if v < i:
                return -1
            elif v == i:
                return 0
            else:
                return 1
        while l < r:
            m = l + ((r-l)>>1)
            mov = getC(A, m)
            if mov < 0:
                l = m + 1
            else:
                r = m
        if l < len(A) and A[l] == l:
            return l
        return -1
