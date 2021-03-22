/*
 * @lc app=leetcode.cn id=200 lang=cpp
 *
 * [200] 岛屿数量
 *
 * https://leetcode-cn.com/problems/number-of-islands/description/
 *
 * algorithms
 * Medium (52.28%)
 * Likes:    979
 * Dislikes: 0
 * Total Accepted:    206K
 * Total Submissions: 393.1K
 * Testcase Example:
 * '[["1","1","1","1","0"],["1","1","0","1","0"],["1","1","0","0","0"],["0","0","0","0","0"]]'
 *
 * 给你一个由 '1'（陆地）和
 * '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 *
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 *
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：grid = [
 * ⁠ ["1","1","1","1","0"],
 * ⁠ ["1","1","0","1","0"],
 * ⁠ ["1","1","0","0","0"],
 * ⁠ ["0","0","0","0","0"]
 * ]
 * 输出：1
 *
 *
 * 示例 2：
 *
 *
 * 输入：grid = [
 * ⁠ ["1","1","0","0","0"],
 * ⁠ ["1","1","0","0","0"],
 * ⁠ ["0","0","1","0","0"],
 * ⁠ ["0","0","0","1","1"]
 * ]
 * 输出：3
 *
 *
 *
 *
 * 提示：
 *
 *
 * m == grid.length
 * n == grid[i].length
 * 1
 * grid[i][j] 的值为 '0' 或 '1'
 *
 *
 */

#include <vector>
using namespace std;

// @lc code=start

int dir[][2] = { { 0, 1 }, { 1, 0 } };

constexpr int LAND = '1';
constexpr int WATER = '0';

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
  int numIslands(vector<vector<char>>& A)
  {
    if (A.empty() || A[0].empty()) {
      return 0;
    }

    const int R = A.size();
    const int C = A[0].size();

    UF uf(R * C);

    auto get_id = [&](const int r, const int c) {
      return r * C + c;
    };

    int blackNumber = 0;

    for (int r = 0; r < R; r++) {
      for (int c = 0; c < C; c++) {
        const int v = A[r][c];
        if (v == LAND) {
          for (int d = 0; d < 2; d++) {
            int nr = r + dir[d][0];
            int nc = c + dir[d][1];
            if (!(nr < 0 || nc < 0 || nr >= R || nc >= C)) {
              if (A[nr][nc] == LAND) {
                uf.Union(get_id(r, c), get_id(nr, nc));
              }
            }
          }
        } else {
          blackNumber++;
        }
      }
    }

    return uf.count - blackNumber;
  }
};
// @lc code=end
