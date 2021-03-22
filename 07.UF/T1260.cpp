// https://nanti.jisuanke.com/t/T1260

#include <assert.h>
#include <limits.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include <algorithm>
#include <iostream>
#include <numeric>
#include <queue>
#include <string>
#include <unordered_map>
#include <unordered_set>
#include <vector>

using namespace std;

class UF
{
public:
  // 并查集数组
  vector<int> F;
  // 记录并查集中集合的个数
  int count = 0;

  // 并查集的初始化
  UF(int n)
  {
    F.resize(n + 10);
    for (int i = 0; i <= n; i++) {
      F[i] = i;
    }
    count = n;
  }

  int Find(int x)
  {
    return x == F[x] ? x : F[x] = Find(F[x]);
  }

  void Union(int x, int y)
  {
    int xpar = Find(x);
    int ypar = Find(y);
    // 将x所在集合，合并到y所在集合
    if (xpar != ypar) {
      F[xpar] = ypar;
      count--;
    }
  }
};

class Solution
{
public:
  int findGangNumber(int n, vector<vector<int>>& es)
  {
    UF uf(n);
    for (auto& e : es) {
      uf.Union(e[0], e[1]);
    }
    return uf.count;
  }
};

int
main(void)
{
  int m, n, a, b;
  int test = 0;
  while (scanf("%d%d", &n, &m) != EOF && n > 0 && m > 0) {
    vector<vector<int>> es;
    for (int i = 0; i < m; i++) {
      scanf("%d%d", &a, &b);
      vector<int> tmp{ a, b };
      es.push_back(tmp);
    }

    Solution s;
    ++test;
    printf("Case %d: %d\n", test, s.findGangNumber(n, es));
  }
  return 0;
}