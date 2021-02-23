/*
 * @lc app=leetcode.cn id=130 lang=java
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
 * Testcase Example:  '[["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]'
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
 * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O'
 * 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 * 
 */

// @lc code=start
class Solution {
    private int[][] dir = { { 0, 1 }, { 1, 0 } };
    private int[] F = null;

    private void Init(int n) {
        F = new int[n];
        for (int i = 0; i < n; i++) {
            F[i] = i;
        }
    }

    private int Find(int x) {
        if (x == F[x]) {
            return x;
        }
        F[x] = Find(F[x]);
        return F[x];
    }

    private void Union(int x, int y) {
        F[Find(x)] = Find(y);
    }

    public void solve(char[][] A) {
        if (A == null || A[0] == null) {
            return;
        }

        final int R = A.length;
        final int C = A[0].length;

        Init(R * C + 1);

        // 我们将vNode设置为R * C
        // 这是一个在矩阵中不存在的点
        final int vNode = R * C;

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (A[r][c] == 'O') {
                    // 如果是边上的点
                    if (r == 0 || r == R - 1 || c == 0 || c == C - 1) {
                        // 那么将其与vNode进行Union
                        Union(r * C + c, vNode);
                    }

                    // 将其与四面的点进行Union
                    for (int d = 0; d < 2; d++) {
                        final int nr = r + dir[d][0];
                        final int nc = c + dir[d][1];
                        if (!(nr < 0 || nr >= R || nc < 0 || nc >= C)) {
                            if (A[nr][nc] == 'O') {
                                Union(r * C + c, nr * C + nc);
                            }
                        }
                    }
                }
            }
        }

        // 查看是不是和vNode一个集合，如果不是就要修改成'X'
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
}
// @lc code=end
