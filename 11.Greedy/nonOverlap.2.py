def nonOverlapIntervals(self, A):
    if not A or len(A) == 0:
        return 0

    A.sort(key=lambda x: x[0])

    preStart = float('inf')
    ans = 0

    N = len(A)

    for i in range(N-1,-1,-1):
        start = A[i][0]
        end = A[i][1]

        if end <= preStart:
            ans += 1
            preStart = start

    return ans
