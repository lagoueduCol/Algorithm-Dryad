class UF(object):
    def __init__(self, N):
        self.F = [0] * N
        self.Cnt = [0] * N
        for i in range(N):
            self.F[i] = i
            self.Cnt[i] = 1

        self.count = N

    def Find(self, x):
        if x == self.F[x]:
            return x
        self.F[x] = self.Find(self.F[x])
        return self.F[x]

    def Union(self, x, y):
        xpar = self.Find(x)
        ypar = self.Find(y)
        if xpar != ypar:
            self.F[xpar] = ypar
            self.Cnt[ypar] += self.Cnt[xpar]
            self.count -= 1

