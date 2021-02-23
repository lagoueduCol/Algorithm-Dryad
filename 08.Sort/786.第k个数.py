# https://www.acwing.com/problem/content/description/788/
def swap(A, i, j):
  t = A[i]
  A[i] = A[j]
  A[j] = t

def kth(A, b, e, k):
  if b >= e:
    return 0

  if b + 1 >= e:
    return A[b]

  x = A[b + ((e - b) >> 1)]
  i = b
  l = b
  r = e - 1

  while i <= r:
    if A[i] < x:
      swap(A, l, i)
      i += 1
      l += 1
    elif (A[i] == x):
      i += 1
    else:
      swap(A, r, i)
      r -= 1

  lcnt = l - b
  mcnt = i - l

  if k < lcnt:
    return kth(A, b, l, k)
  if k >= (lcnt + mcnt):
    return kth(A, i, e, k - lcnt - mcnt)
  return x

def kthNumber(A, n, k):
  return kth(A, 0, n, k - 1)

s = raw_input().split()
n = int(s[0])
k = int(s[1])
s = raw_input().split()
A = [int(x) for x in s]
print kthNumber(A, n, k)


