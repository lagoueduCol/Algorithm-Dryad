# -*- coding: utf-8 -*-

class UF(object):
    def __init__(self, N):
        self.F = [0] * N
        for i in range(N):
            self.F[i] = i
        self.totalCost = 0

    def Find(self, x):
        if x == self.F[x]:
            return x
        self.F[x] = self.Find(self.F[x])
        return self.F[x]

    def Union(self, x, y, cost):
        xpar = self.Find(x)
        ypar = self.Find(y)
        if xpar != ypar:
            self.F[xpar] = ypar
            self.totalCost += cost

class MST(object):
    def solve(self, N, es):
        es = sorted(es, key=(lambda x:x[2]))

        uf = UF(N + 1)
        for e in es:
            uf.Union(e[0], e[1], e[2])

        return uf.totalCost

while True:
    line = raw_input()
    ws = line.split()
    if len(ws) == 1 and int(ws[0]) == 0:
        break

    P = int(ws[0])
    R = int(ws[1])

    es = []
    for i in range(R):
        line = raw_input()
        ws = line.split()
        f, t, cost = ws
        es.append([int(f), int(t), int(cost)])

    mst = MST()
    print mst.solve(P, es)


    raw_input()
