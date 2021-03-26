
// 测试平台 http://poj.org/problem?id=3984 c++
// 注意，POJ只识别c++98

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include <algorithm>
#include <iostream>
#include <numeric>
#include <queue>
#include <string>
#include <vector>

using namespace std;

// your code here.
typedef vector<vector<int>> path;

int dir[][2] = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

// DFS
static void dfs(vector<vector<int>> &A,    /* 迷宫图     */
                vector<vector<bool>> &vis, /* 是否访问过 */
                int r,                     /* 出发点r,c  */
                int c, path &tmp,          /* 当前走到r,c的路径*/
                path &ans)                 /*存储最优结果的路径*/
{
  const int R = A.size();
  const int C = A[0].size();

  // 首先看一下是否越界
  if (r < 0 || r >= R || c < 0 || c >= C) {
    return;
  }

  // 是否走到了终点?
  // 根据题目，终点就是右下角的点
  if (r == R - 1 && c == C - 1) {
    if (ans.empty() || tmp.size() < ans.size()) {
      ans = tmp;
    }
    return;
  }

  vector<int> next(2);

  // 遍历当前点的四个选项
  for (int d = 0; d < 4; d++) {
    int nr = r + dir[d][0];
    int nc = c + dir[d][1];
    if (!(nr < 0 || nc < 0 || nr >= R || nc >= C)) {
      // 如果遇到的是不能走过去的地方
      if (A[nr][nc] == 1)
        continue;
      if (!vis[nr][nc]) {
        vis[nr][nc] = true;
        next[0] = nr;
        next[1] = nc;
        tmp.push_back(next);

        dfs(A, vis, nr, nc, tmp, ans);

        tmp.pop_back();
        vis[nr][nc] = false;
      }
    }
  }
}

// 找到给定迷宫里面的最小路径
// 题目保证有解!
path findMinPath(vector<vector<int>> &A) {
  path ans, tmp;
  if (A.empty() || A[0].empty()) {
    return ans;
  }

  const int R = A.size();
  const int C = A[0].size();

  vector<vector<bool>> vis(R, vector<bool>(C, false));

  vector<int> start(2, 0);
  vis[0][0] = true;
  tmp.push_back(start);
  dfs(A, vis, 0, 0, tmp, ans);
  tmp.pop_back();
  vis[0][0] = false;
  return ans;
}

// 处理程序的输入
int main(void) {
  vector<vector<int>> A(5, vector<int>(5));

  while (true) {

    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        int x = 0;
        if (scanf("%d", &x) == EOF) {
          return 0;
        }

        A[i][j] = x;
      }
    }

    path ans = findMinPath(A);
    for (int i = 0; i < ans.size(); i++) {
      printf("(%d, %d)\n", ans[i][0], ans[i][1]);
    }
  }

  return 0;
}
