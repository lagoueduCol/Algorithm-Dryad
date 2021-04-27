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
 * 你准备参加一场远足活动。给你一个二维 rows x columns 的地图 heights ，其中 heights[row][col] 表示格子
 * (row, col) 的高度。一开始你在最左上角的格子 (0, 0) ，且你希望去最右下角的格子 (rows-1, columns-1) （注意下标从
 * 0 开始编号）。你每次可以往 上，下，左，右 四个方向之一移动，你想要找到耗费 体力 最小的一条路径。
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
 * 解释：路径 [1,2,3,4,5] 的相邻格子差值绝对值最大为 1 ，比路径 [1,3,5,3,5] 更优。
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
class Solution {
    //  二分搜索

    // 行数
    private int Rows = 0;

    // 列数
    private int Cols = 0;

    // 一个点周围的四个方向
    int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    boolean[][] vis = null;

    private void clearVisRecord() {
        for (int r = 0; r < Rows; r++) {
            for (int c = 0; c < Cols; c++) {
                vis[r][c] = false;
            }
        }
    }

    // 这里采用DFS来寻路
    // <r,c>是当前的出发点
    private boolean dfs(int[][] heights,
                        int maxValue,
                        int r,
                        int c) {

        // 如果已经走到了目标点<rows-1, cols-1>
        if (r == Rows - 1 && c == Cols - 1) {
            return true;
        }

        // 查看 <r,c>点的四周
        for (int d = 0; d < 4; d++) {
            final int nr = r + dir[d][0];
            final int nc = c + dir[d][1];
            // 如果周边的点有效，并且没有被访问过
            if ((!(nr < 0 || nc < 0 || nr >= Rows || nc >= Cols)) &&
                !vis[nr][nc]) {
                // 获取边的代价
                final int cost = Math.abs(heights[r][c] - heights[nr][nc]);

                // 在走的时候，如果比midValue大，那么这条路就不能走了
                if (cost <= maxValue) {
                    vis[nr][nc] = true;
                    if (dfs(heights, maxValue, nr, nc)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    // 重新映射之的一维的C数组
    // midValue是在二分的时候给定的值
    // 我们在进行搜索的时候，路径上的绝对值不能比这个大
    // 只能是 <= midValue.
    // 此时我们只需要寻找看看是否存在一条路径即可
    // 如果存在一条路径，上面的绝对值 <= midValue
    // 那么满足条件-> 返回1
    // 如果没有这样的路径，那么返回0
    private int getC(int[][] heights, int midValue) {
        clearVisRecord();
        vis[0][0] = true;
        return dfs(heights, midValue, 0, 0) ? 1 : 0;
    }

    public int minimumEffortPath(int[][] heights) {
        if (heights == null || heights[0] == null) {
            return 0;
        }

        Rows = heights.length;
        Cols = heights[0].length;

        // if just one node
        if (Rows == 1 && Cols == 1) {
            return 0;
        }

        // 生成vis数组
        vis = new boolean[Rows][Cols];

        // 二分搜索
        // 找到搜索范围里：最大值/最小值

        int minCost = Integer.MAX_VALUE;
        int maxCost = 0;

        for (int r = 0; r < Rows; r++) {
            for (int c = 0; c < Cols; c++) {
                // 看一下 右边的点
                if (c + 1 < Cols) {
                    int rightValue = Math.abs(heights[r][c] - heights[r][c+1]);
                    minCost = Math.min(minCost, rightValue);
                    maxCost = Math.max(maxCost, rightValue);
                }
                if (r + 1 < Rows) {
                    int downValue = Math.abs(heights[r][c] - heights[r+1][c]);
                    minCost = Math.min(minCost, downValue);
                    maxCost = Math.max(maxCost, downValue);
                }
            }
        }

        // 那么应该有一个值 target
        // 当 路径的最大绝对值差为 x
        // 并且 x >= target的时候
        // 总是可以走通的

        // 所以我们二分搜索的范围就为[minCost, maxCost + 1)
        // 我们定义0: 表示左上角与右下有没有通路
        //        1: 表示左上角与右下角有通路
        // 那么形成的C数组就是[0,0,0,0,1,1,1,1,1,1]
        // 这样的结构
        // 因此，我们在利用二分搜索的时候，只需要找到最左边的
        // 1的位置就可以了。
        int l = minCost, r = maxCost + 1;

        while (l < r) {
            final int mid = l + ((r-l)>>1);
            final int mv = getC(heights, mid);
            if (mv < 1) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        return l;
    }
}
// @lc code=end

public class Main {
    public static void main(String[] args) {
        int[][] heights = new int[][]{{1,10,6,7,9,10,4,9}};
        Solution s = new Solution();
        System.out.println(s.minimumEffortPath(heights));
    }
}