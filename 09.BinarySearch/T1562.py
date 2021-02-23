# -*- coding: utf-8 -*-

# 测试平台: https://nanti.jisuanke.com/t/T1562

def upperBound(A, target):
    l = 0
    r = len(A) if A else 0
    while l < r:
        m = l + ((r-l)>>1)
        if (A[m] <= target):
            l = m + 1
        else:
            r = m
    return l

s = raw_input().split()
n = int(s[0])
m = int(s[1])
s = raw_input().split()
A = [int(x) for x in s]
A.sort()

for i in range(0, m):
    x = int(raw_input())
    idx = upperBound(A, x)
    print '%s' % (-1 if idx >= len(A) else A[idx])


