/*
 * @lc app=leetcode.cn id=200 lang=java
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
 * Testcase Example:  '[["1","1","1","1","0"],["1","1","0","1","0"],["1","1","0","0","0"],["0","0","0","0","0"]]'
 *
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
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

// @lc code=start
class Solution {
    private int[][] dir = { { 0, 1 }, { 1, 0 } };
    private int[] F = null;
    private int count = 0;

    private void Init(int n) {
        F = new int[n];
        for (int i = 0; i < n; i++) {
            F[i] = i;
        }
        count = n;
    }

    private int Find(int x) {
        if (x == F[x]) {
            return x;
        }
        F[x] = Find(F[x]);
        return F[x];
    }

    private void Union(int x, int y) {
        if (Find(x) != Find(y)) {
            count--;
        }
        F[Find(x)] = Find(y);
    }

    public int numIslands(char[][] A) {
        if (A == null || A[0] == null) {
            return 0;
        }

        final int R = A.length;
        final int C = A[0].length;

        Init(R * C);

        // 统计黑色像素的个数
        int blackNumber = 0;

        // 每个白色像素点与周围的点进行Union
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (A[r][c] == '1') {
                    // 与四周的点进行Union
                    for (int d = 0; d < 2; d++) {
                        final int nr = r + dir[d][0];
                        final int nc = c + dir[d][1];
                        if (!(nr < 0 || nr >= R || nc < 0 || nc >= C)) {
                            if (A[nr][nc] == '1') {
                                Union(r * C + c, nr * C + nc);
                            }
                        }
                    }
                } else {
                    blackNumber++;
                }
            }
        }

        return count - blackNumber;
    }
}
// @lc code=end
