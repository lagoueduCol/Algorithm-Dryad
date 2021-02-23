# https://www.acwing.com/problem/content/description/788/
import Queue
from Queue import PriorityQueue

s = raw_input().split()
n = int(s[0])
k = int(s[1])
s = raw_input().split()
A = [int(x) for x in s]

Q = PriorityQueue()
for x in A:
  Q.put(-x)
  while Q.qsize() > k:
    Q.get()

print 0-Q.get()


