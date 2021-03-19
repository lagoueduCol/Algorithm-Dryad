// 并查集数组
vector<int> F;
// 记录并查集中集合的个数
int count = 0;
// 记录集合中点的个数，比如要知道i所在集合的点有多少个: C[Find(i)]
// 注意：这里不能直接使用C[i]
// 因为只有根结点的统计才是正确的
vector<int> Cnt;

// 并查集的初始化
void Init(int n) {
    F.resize(n);
    Cnt.resize(n);
    for (int i = 0; i < n; i++) {
        F[i] = i;
        Cnt[i] = 1;
    }
    count = n;
}

int Find(int x) { return x == F[x] ? x : F[x] = Find(F[x]); }

void Union(int x, int y) {
    int xpar = Find(x);
    int ypar = Find(y);
    // 将x所在集合，合并到y所在集合
    if (xpar != ypar) {
        F[xpar] = ypar;
        // y集合里面的个数要增加
        Cnt[ypar] += Cnt[xpar];
        count--;
    }
}

int Size(int i) { return Cnt[Find(i)]; }

static void rev2(int[] A, int i) {
    final int N = A == null ? 0 : A.length;

    // 不能再递归下去
    if (i > N) {
        return;
    }

    // print array
    System.out.print("A[] = {");
    for (int j = 0; j < i; j++) {
        System.out.print(A[j] + ", ");
    }
    System.out.println("}");

    // 打印后面的
    rev2(A, i + 1);
}

static void rev3(int[] A, int i, Strange s) {
    final int N = A == null ? 0 : A.length;
    s.print();

    if (i >= N) {
        return;
    }

    s.push(A[i]);
    rev3(A, i + 1, s);
}

void rev4(int[] A, int i, Strange s) {
    final int N = A == null ? 0 : A.length;
    s.print();

    Strange t = s.clone();

    if (i >= N) {
        return;
    }

    s.push(A[i]);
    rev3(A, i + 1, s);
    s.pop();

    assert isSame(s, t);
}