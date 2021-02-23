//测试平台 http://poj.org/problem?id=1287
import java.io.*;
import java.util.*;

// your code here
class Solution {
  private long cost = 0;
  // 这里直接申请了足够多的内存
  private int[] F = new int[3000];
  // 并查集初始化
  // 注意点的编号是从1开始
  private void Init(int n) {
    for (int i = 0; i <= n; i++) {
      F[i] = i;
    }
    cost = 0;
  }

  private int Find(int x) {
    if (x == F[x]) {
      return x;
    }
    F[x] = Find(F[x]);
    return F[x];
  }

  // 在合并的时候，需要加上代价
  private void Union(int x, int y, int pay) {
    if (Find(x) != Find(y)) cost += pay;
    F[Find(x)] = Find(y);
  }

  // 一共有n个点，编号从1~n
  // conn表示输入的边的集合
  // 每一项是一个三元组[点a, 点b, 需要费用c]
  public long Kruskal(int n, int m, int[][] conn) {
      Init(n);

      Arrays.sort(conn, 0, m, new Comparator<int[]>() {
        public int compare(int[] a, int[] b) {
            return a[2] - b[2];
        }
      });

      for (int i = 0; i < m; i++) {
          Union(conn[i][0], conn[i][1], conn[i][2]);
      }

      return cost;
  }
}


// Test code
public class Main {
  static private int pointsNumber = 0;
  static private int[][] conn = new int[7000][3];
  static private Solution s = new Solution();
  public static void main(String args[]) throws Exception {
    Scanner sc = new Scanner(System.in);

    int testCase = 0;

    while (sc.hasNext()) {
      // 点的数目
      int n = sc.nextInt();
      if (n == 0) {
         break;
      }
      // 边的数目
      int m = sc.nextInt();

      for (int i = 0; i < m; i++) {
          // 连接的点a
          conn[i][0] = sc.nextInt();
          // 连接的点b
          conn[i][1] = sc.nextInt();
          // 权重
          conn[i][2] = sc.nextInt();
      }

      System.out.println(s.Kruskal(n, m, conn));
    }
  }
}
