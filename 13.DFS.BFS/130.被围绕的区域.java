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
    private char VIS = 'A';

    private int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private int R, C;

    private void dfs(char[][] A, int r, int c) {
        for (int d = 0; d < 4; d++) {
            final int nr = r + dir[d][0];
            final int nc = c + dir[d][1];
            if (nr < 0 || nr >= R || nc < 0 || nc >= C) {
                continue;
            }

            if (A[nr][nc] == 'O') {
                A[nr][nc] = VIS;
                dfs(A, nr, nc);
            }
        }
 
    }
    public void solve(char[][] A) {
        if (A == null || A.length == 0) {
            return;
        }

        R = A.length;
        C = A[0].length;

        // 看四条边
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (r == 0 || c == 0 || r == R - 1 || c == C - 1) {
                    if (A[r][c] == 'O') {
                        A[r][c] = VIS;
                        dfs(A, r, c);
                    }
                }
            }
        }

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (A[r][c] != VIS) {
                    A[r][c] = 'X';
                } else {
                    A[r][c] = 'O';
                }
            }
        }
    }
}
// @lc code=end

