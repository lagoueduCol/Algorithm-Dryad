/*
 * @lc app=leetcode.cn id=1631 lang=java
 *
 * [1631] 最小体力消耗路径
 *
 * https://leetcode-cn.com/problems/path-with-minimum-effort/description/
 *
 * algorithms
 * Medium (49.37%)
 * Likes:    211
 * Dislikes: 0
 * Total Accepted:    21.9K
 * Total Submissions: 44.4K
 * Testcase Example:  '[[1,2,2],[3,8,2],[5,3,5]]'
 *
 * 你准备参加一场远足活动。给你一个二维 rows x
 * columns 的地图 heights ，其中 heights[row][col] 表示格子 (row,
 * col) 的高度。一开始你在最左上角的格子 (0,
 * 0) ，且你希望去最右下角的格子 (rows-1, columns-1) （注意下标从 0
 * 开始编号）。你每次可以往 上，下，左，右 四个方向之一移动，你想要找到耗费 体力
 * 最小的一条路径。
 *
 * 一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。
 *
 * 请你返回从左上角走到右下角的最小 体力消耗值 。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 *
 * 输入：heights = [[1,2,2],[3,8,2],[5,3,5]]
 * 输出：2
 * 解释：路径 [1,3,5,3,5] 连续格子的差值绝对值最大为 2 。
 * 这条路径比路径 [1,2,2,2,5] 更优，因为另一条路径差值最大值为 3 。
 *
 *
 * 示例 2：
 *
 *
 *
 *
 * 输入：heights = [[1,2,3],[3,8,4],[5,3,5]]
 * 输出：1
 * 解释：路径 [1,2,3,4,5] 的相邻格子差值绝对值最大为 1 ，比路径 [1,3,5,3,5]
 * 更优。
 *
 *
 * 示例 3：
 *
 *
 * 输入：heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
 * 输出：0
 * 解释：上图所示路径不需要消耗任何体力。
 *
 *
 *
 *
 * 提示：
 *
 *
 * rows == heights.length
 * columns == heights[i].length
 * 1
 * 1
 *
 *
 */

// @lc code=start
class Solution
{
  // 行数
  private int Rows = 0;

  // 列数
  private int Cols = 0;

  // 一个点周围的四个方向
  int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

  public int minimumEffortPath(int[][] heights)
  {
    if (heights == null || heights[0] == null) {
      return 0;
    }

    Rows = heights.length;
    Cols = heights[0].length;

    // 如果只有一个结点
    if (Rows == 1 && Cols == 1) {
      return 0;
    }

    // 采用BF算法
    // 从左上角走到右下角，最多只需要走Rows + Cols次
    // 所以我们在更新的时候，最多只需要更新Rows + Cols次
    // 并且，在更新的过程中，如果我们发现，没有任何一个点被更新的时候
    // 我们就可以退出来了

    final int maxDist = Integer.MAX_VALUE >> 4;

    int[][] dist = new int[Rows][Cols];

    // 初始化整个距离
    for (int r = 0; r < Rows; r++) {
      for (int c = 0; c < Cols; c++) {
        dist[r][c] = maxDist;
      }
    }

    dist[0][0] = 0;

    final int maxUpdateTimes = Rows + Cols;

    // 用BF算法来更新
    for (int updateTimes = 0; updateTimes < maxUpdateTimes; updateTimes++) {
      boolean hasUpdateItem = false;
      // 用所有的边来进行更新
      for (int r = 0; r < Rows; r++) {
        for (int c = 0; c < Cols; c++) {
          for (int d = 0; d < 4; d++) {
            int nr = r + dir[d][0];
            int nc = c + dir[d][1];
            if (!(nr < 0 || nc < 0 || nr >= Rows || nc >= Cols)) {
              // 拿到边的代价
              final int cost = Math.abs(heights[r][c] - heights[nr][nc]);
              // 这条路径走过来的最大代价
              final int nextCost = Math.max(dist[r][c], cost);
              if (nextCost < dist[nr][nc]) {
                dist[nr][nc] = nextCost;
                hasUpdateItem = true;
              }
            }
          }
        }
      }
      // 如果没有更新
      if (!hasUpdateItem) {
        break;
      }
    }

    return dist[Rows - 1][Cols - 1];
  }
}
// @lc code=end
