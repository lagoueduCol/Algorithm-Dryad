/*
 * @lc app=leetcode.cn id=130 lang=cpp
 *
 * [130] 被围绕的区域
 *
 * https://leetcode-cn.com/problems/surrounded-regions/description/
 *
 * algorithms
 * Medium (42.26%)
 * Likes:    475
 * Dislikes: 0
 * Total Accepted:    88.5K
 * Total Submissions: 209.4K
 * Testcase Example:
 * '[["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]'
 *
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
 *
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 *
 * 示例:
 *
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 *
 *
 * 运行你的函数后，矩阵变为：
 *
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 *
 *
 * 解释:
 *
 * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。
 * 任何不在边界上，或不与边界上的 'O' 相连的 'O'
 * 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 *
 */
#include <vector>
using namespace std;

// @lc code=start

int dir[][2] = { { 0, 1 }, { 1, 0 } };
class Solution
{
  // 并查集数组
  vector<int> F;

  // 并查集的初始化
  void Init(int n)
  {
    F.resize(n);
    for (int i = 0; i < n; i++) {
      F[i] = i;
    }
  }

  int Find(int x) { return x == F[x] ? x : F[x] = Find(F[x]); }
  void Union(int x, int y) { F[Find(x)] = F[Find(y)]; }

public:
  void solve(vector<vector<char>>& A)
  {
    if (A.empty() || A[0].empty()) {
      return;
    }

    const int R = A.size();
    const int C = A[0].size();

    // vnode id is R * C
    const int vNode = R * C;
    Init(R * C + 1);

    // 首先我们将四周的点与vNode进行union
    for (int c = 0; c < C; c++) {
      if (A[0][c] == 'O') {
        Union(c, vNode);
      }
      if (A[R - 1][c] == 'O') {
        Union((R - 1) * C + c, vNode);
      }
    }

    // 然后是最左边的列与最右边的例
    for (int r = 0; r < R; r++) {
      if (A[r][0] == 'O') {
        Union(r * C, vNode);
      }
      if (A[r][C - 1] == 'O') {
        Union(r * C + C - 1, vNode);
      }
    }

    // 然后是遍历所有的点, 如果发现'O'那么就与右边/下面的点进行Union
    for (int r = 0; r < R; r++) {
      for (int c = 0; c < C; c++) {
        if (A[r][c] == 'O') {
          for (int d = 0; d < 2; d++) {
            int nr = r + dir[d][0];
            int nc = c + dir[d][1];
            if (!(nr < 0 || nc < 0 || nr >= R || nc >= C)) {
              if (A[nr][nc] == 'O') {
                Union(r * C + c, nr * C + nc);
              }
            }
          }
        }
      }
    }

    // 再看一下哪些点与vNode同属于一个集合
    for (int r = 0; r < R; r++) {
      for (int c = 0; c < C; c++) {
        if (A[r][c] == 'O') {
          if (Find(r * C + c) != Find(vNode)) {
            A[r][c] = 'X';
          }
        }
      }
    }
  }
};
// @lc code=end
