class Solution(object):
    def buildPMT(self, s):
        N = 0 if not s else len(s)
        pmt = [0] * N

        i = 1
        j = 0
        pmt[0] = 0

        while i < N:
            if s[i] == s[j]:
                i += 1
                j += 1
                pmt[i-1] = j
            else:
                if 0 == j:
                    i += 1
                    pmt[i-1] = 0
                else:
                    j = pmt[j-1]

        return pmt
