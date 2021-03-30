// https://www.lintcode.com/problem/minimum-risk-path/

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

class Solution {
 public:
  /**
   * @param n: maximum index of position.
   * @param m: the number of undirected edges.
   * @param x: undirected edges
   * @param y: undirected edges
   * @param w: undirected edges risk
   * @return: return the minimum risk value.
   */
  int getMinRiskValue(int n, int m, vector<int> &x, vector<int> &y,
                      vector<int> &w) {
    vector<vector<pair<int, int>>> G(n + 1);

    for (int i = 0; i < m; i++) {
      const int a = x[i], b = y[i], c = w[i];
      G[a].push_back({b, c});
      G[b].push_back({a, c});
    }

    vector<int> risk(n + 1, INT_MAX);
    // Q 里面记录了走到某个点的最小代价
    // 每次出队的时候，我们总是出队最小的
    auto cmp = [&](int a, int b) { return risk[a] > risk[b]; };
    priority_queue<int, vector<int>, decltype(cmp)> Q(cmp);
    // 将起始点放到队列中
    Q.push(0);
    // 一开始出发点risk为0
    risk[0] = 0;

    while (!Q.empty()) {
      auto cur = Q.top();
      Q.pop();

      // 查看当前结点的后继
      for (auto nextValue : G[cur]) {
        const int curRisk = risk[cur];
        const int nextRisk = max(curRisk, nextValue.second);
        if (nextRisk < risk[nextValue.first]) {
          risk[nextValue.first] = nextRisk;
          Q.push(nextValue.first);
        }
      }
    }

    return risk[n];
  }
};