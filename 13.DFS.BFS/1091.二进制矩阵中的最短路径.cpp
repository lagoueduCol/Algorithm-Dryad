/*
 * @lc app=leetcode.cn id=1091 lang=cpp
 *
 * [1091] 二进制矩阵中的最短路径
 *
 * https://leetcode-cn.com/problems/shortest-path-in-binary-matrix/description/
 *
 * algorithms
 * Medium (36.80%)
 * Likes:    95
 * Dislikes: 0
 * Total Accepted:    17.6K
 * Total Submissions: 47.8K
 * Testcase Example:  '[[0,1],[1,0]]'
 *
 * 给你一个 n x n 的二进制矩阵 grid 中，返回矩阵中最短 畅通路径
 * 的长度。如果不存在这样的路径，返回 -1 。
 *
 * 二进制矩阵中的 畅通路径 是一条从 左上角 单元格（即，(0, 0)）到 右下角
 * 单元格（即，(n - 1, n - 1)）的路径，该路径同时满足下述要求：
 *
 *
 * 路径途经的所有单元格都的值都是 0 。
 * 路径中所有相邻的单元格应当在 8 个方向之一
 * 上连通（即，相邻两单元之间彼此不同且共享一条边或者一个角）。
 *
 *
 * 畅通路径的长度 是该路径途经的单元格总数。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：grid = [[0,1],[1,0]]
 * 输出：2
 *
 *
 * 示例 2：
 *
 *
 * 输入：grid = [[0,0,0],[1,1,0],[1,1,0]]
 * 输出：4
 *
 *
 * 示例 3：
 *
 *
 * 输入：grid = [[1,0,0],[1,1,0],[1,1,0]]
 * 输出：-1
 *
 *
 *
 *
 * 提示：
 *
 *
 * n == grid.length
 * n == grid[i].length
 * 1
 * grid[i][j] 为 0 或 1
 *
 *
 */

// @lc code=start
class Solution {
 public:
  int shortestPathBinaryMatrix(vector<vector<int>>& A) {
    const int R = A.size();
    const int C = A[0].size();

    int dir[][2] = {
        {0, 1} /*右*/,     {0, -1} /*左*/,   {1, 0} /*下*/,    {-1, 0} /*上*/,
        {-1, -1} /*左上*/, {-1, 1} /*右上*/, {1, -1} /*左下*/, {1, 1} /*右下*/,
    };

    if (R <= 1 || C <= 1) {
      return min(R, C);
    }

    // 如果起点和终点都不可以走
    if (A[0][0] == 1 || A[R - 1][C - 1] == 1) {
      return -1;
    }

    // 处理起始点
    queue<pair<int, int>> Q;
    Q.push({0, 0});
    A[0][0] = 1;

    int ans = 0;

    while (!Q.empty()) {
      ans++;

      int QSize = Q.size();

      while (QSize--) {
        auto cur = Q.front();
        Q.pop();

        if (cur.first == R - 1 && cur.second == C - 1) {
          return ans;
        }

        for (int d = 0; d < 8; d++) {
          int nr = cur.first + dir[d][0];
          int nc = cur.second + dir[d][1];
          if (!(nr < 0 || nc < 0 || nr >= R || nc >= C)) {
            if (A[nr][nc] != 1) {
              A[nr][nc] = 1;
              Q.push({nr, nc});
            }
          }
        }
      }
    }

    return -1;
  }
};
// @lc code=end
