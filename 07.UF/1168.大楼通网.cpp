
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

class UF {
public:
  // 并查集数组
  vector<int> F;
  int totalCost = 0;
  // 记录集合中点的个数，比如要知道i所在集合的点有多少个: C[Find(i)]
  // 注意：这里不能直接使用C[i]
  // 因为只有根结点的统计才是正确的
  vector<int> Cnt;

  // 并查集的初始化
  UF(int n) {
    F.resize(n);
    for (int i = 0; i < n; i++) {
      F[i] = i;
    }
    totalCost = 0;
  }

  int Find(int x) { return x == F[x] ? x : F[x] = Find(F[x]); }

  void Union(int x, int y, int cost) {
    int xpar = Find(x);
    int ypar = Find(y);
    // 将x所在集合，合并到y所在集合
    if (xpar != ypar) {
      F[xpar] = ypar;
      totalCost += cost;
    }
  }
};

class Solution {

  // N 表示结点数目
  // cost[i-1]表示结点i自己买路由器的代价
  // es[x] = [a, b, c]表示大楼a,b之间拉网线的费用
  // 输出所有大楼通网的最小费用
public:
  int minCostToSupplyWater(int N, vector<int> &cost, vector<vector<int>> &es) {
    // 初始化并查集
    UF uf(N + 1);

    // 每个结点都要自己挖水，那么我们可以认为这样
    // 0号楼已经有网络了，可以用0费用上网
    // i号楼与0号楼拉网线，需要的费用是cost[i-1]
    // 那么这里就多了N条边
    for (int i = 1; i <= N; i++) {
      vector<int> tmp{0, i, cost[i - 1]};
      es.push_back(tmp);
    }

    sort(es.begin(), es.end(), [](const vector<int> &a, const vector<int> &b) {
      return a[2] < b[2];
    });

    for (auto &e : es) {
      uf.Union(e[0], e[1], e[2]);
    }

    return uf.totalCost;
  }
};

int main(void) {
  Solution s;
  // n = 5, wells=[1,2,2,3,2], pipes=[[1,2,1],[2,3,1],[4,5,7]]
  int N = 5;
  vector<int> cost = {1, 2, 2, 3, 2};
  vector<vector<int>> es = {{1, 2, 1}, {2, 3, 1}, {4, 5, 7}};
  std::cout << s.minCostToSupplyWater(N, cost, es) << std::endl;
  return 0;
}
