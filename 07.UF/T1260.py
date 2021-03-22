# https://nanti.jisuanke.com/t/T1260

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

    def Size(self, i):
        return self.Cnt[self.Find(i)]

    def Count(self):
        return self.count


test = 0
while True:
    line = raw_input()
    ws = line.split()
    n = int(ws[0])
    m = int(ws[1])

    if n == 0 and m == 0:
        break
    
    uf = UF(n + 1)

    for i in range(m):
        line = raw_input()
        ws = line.split()
        a = int(ws[0])
        b = int(ws[1])        

        uf.Union(a,b)
    
    test += 1
    
    print 'Case %s: %s' % (test, uf.count)