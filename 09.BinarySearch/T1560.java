// 测试平台: https://nanti.jisuanke.com/t/T1560
import java.io.*;
import java.util.*;

public class Main {
  static private boolean binarySearch(long[] A, long target) {
    if (A == null || A.length == 0) {
      return false;
    }
    // 首先设定初始区间，这里我们采用开闭原则[l, r)
    int l = 0, r = A.length;
    // 循环结束的判断条件是整个区间为空区间，那么运行结束。
    // 我们使用的是开闭原则来表示一个区间，所以当l < r的时候
    // 我们要查找的区间还不是一个空区间。
    while (l < r) {
      final int m = l + ((r - l) >> 1);
      if (A[m] == target) {
        return true;
      }
      if (A[m] < target) {
        // 当中点比目标值小时，需要把左边的部分扔掉。即[l, m]
        // 这个区间扔掉，由于我们采用的是开闭原则，所以新的区间需要是
        // [m + 1, r), 因引需要将l = m + 1
        l = m + 1;
      } else {
        // 当中点比目标值大时，需要把右边的部分扔掉，即[m, r)这个区间扔掉。
        // 那么新区间变成[l, m)。由于我们使用的是开闭原则，
        // 只需要设置r = m即可。
        r = m;
      }
    }
    return false;
  }

  public static void main(String args[]) throws Exception {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int m = sc.nextInt();
    long[] A = new long[n];

    for (int i = 0; i < n; i++) {
      A[i] = sc.nextLong();
    }
    Arrays.sort(A);
    for (int i = 0; i < m; i++) {
      long target = sc.nextLong();
      System.out.println(binarySearch(A, target) ? "YES" : "NO");
    }
  }
}
