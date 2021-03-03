class Heap {
  public:
    vector<int> a;
    int n = 0;

    void sink(int i) {
        int j = 0;
        int t = a[i];
        while ((j = (i << 1) + 1) < n) {
            if (j < n - 1 && a[j] < a[j + 1]) {
                j++;
            }
            if (a[j] > t) {
                a[i] = a[j];
                i = j;
            } else {
                break;
            }
        }
        a[i] = t;
    }

    void swim(int i) {
        int par = 0;
        int t = a[i];
        while (i > 0) {
            par = (i - 1) >> 1;
            if (a[par] < t) {
                a[i] = a[par];
                i = par;
            } else {
                break;
            }
        }
        a[i] = t;
    }

    void push(int x) {
        a[n++] = x;
        swim(n - 1);
    }

    int pop() {
        int ret = a[0];
        a[0] = a[--n];
        sink(0);
        return ret;
    }

    int size() const { return n; }
};

