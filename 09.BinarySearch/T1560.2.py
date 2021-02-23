# -*- coding: utf-8 -*-

# 测试平台: https://nanti.jisuanke.com/t/T1560

def lowerBound(A, target):
    l = 0
    r = len(A) if A else 0
    while l < r:
        m = l + ((r-l)>>1)
        if (A[m] < target):
            l = m + 1
        else:
            r = m
    return l

def binarySearch(A, target):
    l = lowerBound(A, target)
    return True if l < len(A) and A[l] == target else False

s = raw_input().split()
n = int(s[0])
m = int(s[1])
s = raw_input().split()
A = [int(x) for x in s]
A.sort()

for i in range(0, m):
    x = int(raw_input())
    print "%s" % ("YES" if binarySearch(A, x) else "NO")


