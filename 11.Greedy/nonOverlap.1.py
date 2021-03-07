def nonOverlapIntervals(self, A):
    if not A or len(A) == 0:
        return 0

    A.sort(key=lambda x: x[1])

    preEnd = float('-inf')
    ans = 0

    for r in A:
        start = r[0]
        end = r[1]

        if preEnd <= start:
            preEnd = end
            ans += 1

    return ans
