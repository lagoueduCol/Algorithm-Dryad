import java.util.*;
import java.io.*;

class Solution {
  private int[] F = null;
  private int totalCost = 0;

  // 注意，编号是从1 ~ n
  private void Init(int n) {
    F = new int[n + 1];
    for (int i = 0; i <= n; i++) {
      F[i] = i;
    }
    totalCost = 0;
  }

  private int Find(int x) {
    if (x == F[x]) {
      return x;
    }
    F[x] = Find(F[x]);
    return F[x];
  }

  private void Union(int x, int y, int pay) {
    if (Find(x) != Find(y)) {
      totalCost += pay;
    }
    F[Find(x)] = Find(y);
  }

  // N 表示结点数目
  // cost[i-1]表示结点i自己买路由器的代价
  // es[x] = [a, b, c]表示大楼a,b之间拉网线的费用
  // 输出所有大楼通网的最小费用
  public int minCostToSupplyWater(int N, int[] cost, int[][] es) {
    // 初始化并查集
    Init(N);

    // 每个结点都要自己挖水，那么我们可以认为这样
    // 0号楼已经有网络了，可以用0费用上网
    // i号楼与0号楼拉网线，需要的费用是cost[i-1]
    // 那么这里就多了N条边
    int[][] conn = new int[es.length + N][3];
    for (int i = 0; i < es.length; i++) {
      conn[i][0] = es[i][0];
      conn[i][1] = es[i][1];
      conn[i][2] = es[i][2];
    }

    int to = es.length;
    for (int i = 1; i <= N; i++) {
      conn[to][0] = 0;
      conn[to][1] = i;
      conn[to][2] = cost[i - 1];
      to++;
    }

    // 接下来采用Krukal最小生成树算法
    Arrays.sort(conn, new Comparator<int[]>() {
      public int compare(int[] a, int[] b) {
        return a[2] - b[2];
      }
    });

    for (int i = 0; i < conn.length; i++) {
      Union(conn[i][0], conn[i][1], conn[i][2]);
    }

    return totalCost;
  }
}

public class Main {
  public static void main(String[] argv) {
    Solution s = new Solution();
    // n = 5, wells=[1,2,2,3,2], pipes=[[1,2,1],[2,3,1],[4,5,7]]
    final int N = 5;
    final int[] cost = { 1, 2, 2, 3, 2 };
    final int[][] es = { { 1, 2, 1 }, { 2, 3, 1 }, { 4, 5, 7 } };
    System.out.println(s.minCostToSupplyWater(N, cost, es));
  }
}