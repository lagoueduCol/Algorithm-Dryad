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

int dir[][2] = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

class Solution {
  char VIS = '2';
  int R, C;

  void dfs(vector<vector<char>>& A, int r, int c) {
    for (int d = 0; d < 4; d++) {
      int nr = r + dir[d][0];
      int nc = c + dir[d][1];
      if (!(nr < 0 || nc < 0 || nr >= R || nc >= C)) {
        if (A[nr][nc] == '1') {
          A[nr][nc] = VIS;
          dfs(A, nr, nc);
        }
      }
    }
  }

 public:
  int numIslands(vector<vector<char>>& A) {
    if (A.empty() || A[0].empty()) {
      return 0;
    }

    R = A.size();
    C = A[0].size();

    int ans = 0;

    for (int r = 0; r < R; r++) {
      for (int c = 0; c < C; c++) {
        const int v = A[r][c];
        if (v == '1') {
          ans++;
          A[r][c] = VIS;
          dfs(A, r, c);
        }
      }
    }

    return ans;
  }
};
// @lc code=end
