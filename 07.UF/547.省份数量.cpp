/*
 * @lc app=leetcode.cn id=547 lang=cpp
 *
 * [547] 省份数量
 *
 * https://leetcode-cn.com/problems/number-of-provinces/description/
 *
 * algorithms
 * Medium (61.41%)
 * Likes:    496
 * Dislikes: 0
 * Total Accepted:    116.6K
 * Total Submissions: 189.8K
 * Testcase Example:  '[[1,1,0],[1,1,0],[0,0,1]]'
 *
 *
 *
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b
 * 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 *
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 *
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i
 * 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
 *
 * 返回矩阵中 省份 的数量。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 * 输出：2
 *
 *
 * 示例 2：
 *
 *
 * 输入：isConnected = [[1,0,0],[0,1,0],[0,0,1]]
 * 输出：3
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1
 * n == isConnected.length
 * n == isConnected[i].length
 * isConnected[i][j] 为 1 或 0
 * isConnected[i][i] == 1
 * isConnected[i][j] == isConnected[j][i]
 *
 *
 *
 *
 */

// @lc code=start

#include <vector>

using namespace std;

class UF
{
public:
  // 并查集数组
  vector<int> F;
  // 记录并查集中集合的个数
  int count = 0;
  // 记录集合中点的个数，比如要知道i所在集合的点有多少个: C[Find(i)]
  // 注意：这里不能直接使用C[i]
  // 因为只有根结点的统计才是正确的
  vector<int> Cnt;

  // 并查集的初始化
  UF(int n)
  {
    F.resize(n);
    Cnt.resize(n);
    for (int i = 0; i < n; i++) {
      F[i] = i;
      Cnt[i] = 1;
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
      // y集合里面的个数要增加
      Cnt[ypar] += Cnt[xpar];
      count--;
    }
  }

  int Size(int i)
  {
    return Cnt[Find(i)];
  }
};
class Solution
{
public:
  int findCircleNum(vector<vector<int>>& A)
  {
    if (A.empty() || A[0].empty()) {
      return 0;
    }

    const int N = A.size();

    UF uf(N);

    auto get_id = [&](int r, int c) {
      return r * N + c;
    };

    for (int r = 0; r < N; r++) {
      for (int c = r + 1; c < N; c++) {
        const int v = A[r][c];
        if (v) {
          uf.Union(r, c);
        }
      }
    }

    return uf.count;
  }
};
// @lc code=end
