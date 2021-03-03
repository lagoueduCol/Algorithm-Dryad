class Heap(object):
    a = []
    n = 0

    def _sink(self, i):
        t = self.a[i]
        while i + i + 1 < self.n:
            j = i + i + 1
            if j < self.n - 1 and self.a[j] < self.a[j+1]:
                j += 1
            if self.a[j] > t:
                self.a[i] = self.a[j]
                i = j
            else:
                break
        self.a[i] = t

    def _swim(self, i):
        t = self.a[i]
        while i > 0:
            par = (i-1) / 2
            if self.a[par] < t:
                self.a[i] = self.a[par]
                i = par
            else:
                break
        self.a[i] = t

    def push(self, x):
        self.a.append(x)
        self._swim(self.n)
        self.n += 1

    def pop(self):
        ret = self.a[0]
        self.a[0] = self.a[self.n-1]
        self.a.pop()
        self.n -= 1
        self._sink(0)
        return ret

    def size(self):
        return self.n
