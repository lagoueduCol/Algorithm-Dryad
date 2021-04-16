class Solution(object):
    def buildNext(self, s):
        N = 0 if not s else len(s)

        next = [0] * (N + 1)

        i = 0
        j = -1

        next[0] = -1

        while i < N:
            if -1 == j or s[i] == s[j]:
                i += 1
                j += 1
                next[i] = j
            else:
                j = next[j]
        return next
