// https://www.lintcode.com/problem/617/

public class Solution {
  private int getC(int[] A, double[] B, double m, int k) {
    final int N = A == null ? 0 : A.length;
    for (int i = 0; i < N; i++) {
      B[i] = (double)A[i] - m;
    }
    // 再把B[]数组变成自身的前缀和数组
    for (int i = 1; i < N; i++) {
      B[i] += B[i - 1];
    }

    double pre_min = 0;
    for (int i = k - 1; i < N; i++) {
      // i >= k - 1
      // 才有可能形成长度 >= k的子数组
      // 形成的子数组是[i + 1 - k, i + 1)
      if (B[i] >= pre_min) {
        return 0;
      }
      // 滑动窗口要移出去的时候
      // 要把B[i + 1 - k]移出去了
      pre_min = Math.min(pre_min, B[i + 1 - k]);
    }

    return 1;
  }

  public double maxAverage(int[] A, int k) {
    final int N = A == null ? 0 : A.length;

    int small = Integer.MAX_VALUE;
    int large = Integer.MIN_VALUE;

    for (int i = 0; i < N; i++) {
      small = Math.min(small, A[i]);
      large = Math.max(large, A[i]);
    }

    double[] B = new double[N];

    double l = small;
    double r = (double)large + 1.0;
    while (r - l > 1e-6) {
      double m = (l + r) / 2.0;
      int mov = getC(A, B, m, k);
      if (mov <= 0) {
        l = m;
      } else {
        r = m;
      }
    }

    return Math.abs(l) < 1e-6 ? 0 : l;
  }
}
