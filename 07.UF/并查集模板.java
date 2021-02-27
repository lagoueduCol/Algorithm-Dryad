// 并查集数组
int[] F = null;
// 记录并查集中集合的个数
int count = 0;
// 记录集合中点的个数，比如要知道i所在集合的点有多少个: C[Find(i)]
// 注意：这里不能直接使用C[i]
// 因为只有根结点的统计才是正确的
int[] Cnt = null;


// 并查集的初始化
void Init(int n) {
  F = new int[n];
  Cnt = new int[n];
  for (int i = 0; i < n; i++) {
    F[i] = i;
    Cnt[i] = 1;
  }
  count = n;
}


int Find(int x) {
  if (x == F[x]) {
    return x;
  }
  F[x] = Find(F[x]);
  return F[x];
}


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


int Size(int i) {
  return Cnt[Find(i);
}
