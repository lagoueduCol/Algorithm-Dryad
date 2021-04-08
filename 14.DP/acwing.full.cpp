/*

有 N
 种物品和一个容量是 V
 的背包，每种物品都有无限件可用。

第 i
 种物品的体积是 vi
，价值是 wi
。

求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。
输出最大价值。

输入格式

第一行两个整数，N，V
，用空格隔开，分别表示物品种数和背包容积。

接下来有 N
 行，每行两个整数 vi,wi
，用空格隔开，分别表示第 i
 种物品的体积和价值。

输出格式

输出一个整数，表示最大价值。

数据范围

0<N,V≤1000

0<vi,wi≤1000

输入样例

4 5
1 2
2 4
3 4
4 5
输出样例：

10

*/

// 测试平台: https://www.acwing.com/problem/content/3/

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

// your code here.

class Solution {
 public:
  int solve(int N, int V, vector<int> &v, vector<int> &w) {
    // dp[x]表示当占用掉x空间的容量时
    // 能够得到的最大的收益
    int dp[V + 1];

    // 一开始什么都没有选，那么肯定是0
    memset(dp, 0, sizeof(dp));

    // 可以认为一开始的点集为S0 = {0/*占用的空间*/, 0/*收益*/}
    // 也就是dp[0] = 0;
    // 但是这里不需要去设置，省略!

    for (int i = 0; i < N; i++) {
      // 我们遍历旧有的点集
      // 注意遍历的方向
      for (int space = v[i]; space <= V; space++) {
        const int oldSpace = space - v[i];
        const int newSpace = space;

        // 当我们选择<v[i], w[i]>累加到
        // <oldSpace, dp[oldSpace]>的时候
        // 我们可以得到
        // <newSpace, dp[newSpace] = dp[oldSpace] + w[i]>
        // 当然，这里我们dp[newSpace]
        // 可能原来就有值
        // 我们需要取个更大一点的值
        dp[newSpace] = max(dp[newSpace], dp[oldSpace] + w[i]);
      }
    }

    // 最后返回整个数组中的最大值
    return *max_element(dp, dp + V + 1);
  }
};

// Test Code

int main(void) {
  int N;
  int V;

  while (scanf("%d%d", &N, &V) != EOF) {
    vector<int> v;
    vector<int> w;

    // 读入每个物品的值
    for (int i = 0; i < N; i++) {
      int vi;
      int wi;

      scanf("%d%d", &vi, &wi);

      v.push_back(vi);
      w.push_back(wi);
    }

    Solution s;
    printf("%d\n", s.solve(N, V, v, w));
  }

  return 0;
}