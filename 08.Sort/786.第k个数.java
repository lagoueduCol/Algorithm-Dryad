// https://www.acwing.com/problem/content/description/788/
import java.io.*;
import java.util.*;

public class Main {
  static int[] A = new int[100001];

  private static void swap(int[] A, int i, int j) {
    int t = A[i];
    A[i] = A[j];
    A[j] = t;
  }

  // 注意这里区间为[b, e), k也是从0开始算的
  private static int kth(int[] A, int b, int e, int k) {
    // 如果为空
    if (b >= e) {
      return 0;
    }
    // 如果只有一个元素
    if (b + 1 >= e) {
      return A[b];
    }

    // 进行三路切分
    final int x = A[b + ((e - b) >> 1)];
    int i = b;
    int l = b;
    int r = e - 1;

    while (i <= r) {
      if (A[i] < x)
        swap(A, l++, i++);
      else if (A[i] == x)
        i++;
      else
        swap(A, r--, i);
    }

    // 分别拿到三段的长度
    final int lcnt = l - b;
    final int mcnt = i - l;

    // 如果第k个数落在左区间
    if (k < lcnt)
      return kth(A, b, l, k);
    // 如果第k个数落在右区间
    if (k >= (lcnt + mcnt))
      return kth(A, i, e, k - lcnt - mcnt);
    // 如果第k个数落在中间，那么直接返回x
    return x;
  }

  private static int kthNumber(int[] A, int n, int k) {
    return kth(A, 0, n, k - 1);
  }

  public static void main(String args[]) throws Exception {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int k = sc.nextInt();

    for (int i = 0; i < n; i++) {
      A[i] = sc.nextInt();
    }

    System.out.println(kthNumber(A, n, k));
  }
}
