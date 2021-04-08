
//测试平台 https://www.acwing.com/problem/content/2/
import java.io.*;
import java.util.*;

// your code here
class Solution {
  public int Solve(int N, int V, int[] v, int[] w) {
    // dp[x]表示当占用掉x空间的容量时
    // 能够得到的最大的收益
    int[] dp = new int[V + 1];

    // 可以认为一开始的点集为S0 = {0/*占用的空间*/, 0/*收益*/}
    // 也就是dp[0] = 0;
    // 但是这里不需要去设置，省略!

    int ans = 0;
    for (int i = 0; i < N; i++) {
      // 我们遍历旧有的点集
      // 注意遍历的方向
      for (int space = V; space >= v[i]; space--) {
        final int oldSpace = space - v[i];
        final int newSpace = space;

        // 当我们选择<v[i], w[i]>累加到
        // <oldSpace, dp[oldSpace]>的时候
        // 我们可以得到
        // <newSpace, dp[newSpace] = dp[oldSpace] + w[i]>
        // 当然，这里我们dp[newSpace]
        // 可能原来就有值
        // 我们需要取个更大一点的值
        dp[newSpace] = Math.max(dp[newSpace], dp[oldSpace] + w[i]);

        ans = Math.max(ans, dp[newSpace]);
      }
    }

    // 最后返回整个数组中的最大值
    return ans;
  }
}

// Test code
public class Main {
  static private int pointsNumber = 0;
  static private int[][] conn = new int[7000][3];
  static private Solution s = new Solution();

  public static void main(String args[]) throws Exception {
    Scanner sc = new Scanner(System.in);
    while (sc.hasNext()) {
      // 背包的数目
      int N = sc.nextInt();
      if (N <= 0) {
        break;
      }
      // 容量
      int V = sc.nextInt();

      int[] v = new int[N];
      int[] w = new int[N];
      // 读入每个背包
      for (int i = 0; i < N; i++) {
        v[i] = sc.nextInt();
        w[i] = sc.nextInt();
      }

      System.out.println(s.Solve(N, V, v, w));
    }
  }
}
