/*
 * @lc app=leetcode.cn id=1091 lang=java
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
 * 给你一个 n x n 的二进制矩阵 grid 中，返回矩阵中最短 畅通路径 的长度。如果不存在这样的路径，返回 -1 。
 * 
 * 二进制矩阵中的 畅通路径 是一条从 左上角 单元格（即，(0, 0)）到 右下角 单元格（即，(n - 1, n -
 * 1)）的路径，该路径同时满足下述要求：
 * 
 * 
 * 路径途经的所有单元格都的值都是 0 。
 * 路径中所有相邻的单元格应当在 8 个方向之一 上连通（即，相邻两单元之间彼此不同且共享一条边或者一个角）。
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

import java.util.*;

// @lc code=start
class Solution {
    public int shortestPathBinaryMatrix(int[][] A) {
        int[][] dir = {
            {0, 1}  /*右*/,
            {0, -1} /*左*/,
            {1, 0}  /*下*/,
            {-1, 0} /*上*/,
            {-1,-1} /*左上*/,
            {-1,1}  /*右上*/,
            {1,-1}  /*左下*/,
            {1,1}   /*右下*/,
        };

        // 拿到矩阵的Row, Col
        final int R = A == null ? 0 : A.length;
        final int C = R > 0 ? (A[0] == null ? 0 : A[0].length) : 0;

        // 首先处理特殊情况
        // 为空，或者只有一个格子
        if (R <= 1 || C <= 1) {
            return Math.min(R, C);
        }

        // 首先处理起始点
        // 如果起始点或者说终点已经是1
        // 那么返回-1
        if (A[0][0] == 1 || A[R - 1][C - 1] == 1) {
            return -1;
        }

        Queue<int[]> Q = new LinkedList<>();

        int[] cur = new int[2]; // {0,0}
        // 将起始点 入队， 并且标记为已访问
        Q.add(cur);
        A[cur[0]][cur[1]] = 1;

        // 一开始就会占用一个格子
        int ans = 0;

        while (!Q.isEmpty()) {
            ans++;
            // 注意这里类似二叉树层次遍历的作法，取出qSize
            // 可以一层一层的遍历。
            int QSize = Q.size();
            while (QSize-- > 0) {
                cur = Q.remove();

                // 如果已经走到了目的地
                if (cur[0] == (R - 1) && cur[1] == (C - 1)) {
                    return ans;
                }

                for (int d = 0; d < 8; d++) {
                    int nr = cur[0] + dir[d][0];
                    int nc = cur[1] + dir[d][1];
                    if (!(nr < 0 || nc < 0 || nr >= R || nc >= C)) {
                        if (A[nr][nc] != 1) {
                            A[nr][nc] = 1;
                            Q.add(new int[] { nr, nc });
                        }
                    }
                }
            }
        }

        return -1;
    }
}

// @lc code=end
