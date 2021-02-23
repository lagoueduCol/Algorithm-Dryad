class increaseQueue {
  private int head = 0;
  private int tail = 0;
  private int used = 0;
  private int totoalSize = 0;
  private long[] Q = null;

  private int back() {
    return (tail - 1 + totoalSize) % totoalSize;
  }

  public increaseQueue(int N) {
    Q = new long[N];
    totoalSize = N;
  }

  // 环形队列
  // 这里的tail指向能存放的位置
  // [head, tail)
  public void push(long v) {
    // 递增队列
    // 注意相等的元素仍然是需要存在队列中的
    while (used > 0 && Q[back()] > v) {
      tail = back();
      used--;
    }
    Q[tail] = v;
    used++;
    tail = (tail + 1) % totoalSize;
  }

  public long front() {
    return Q[head];
  }

  // 注意这里弹元素的时候
  // 1. 有元素可以弹
  // 2. 队首元素等于value.
  public void pop(long v) {
    if (used > 0 && Q[head] == v) {
      head = (head + 1) % totoalSize;
      used--;
    }
  }
}

public class MaxSumLengthSmallerK {
  // 求一个数组，连续子数组长度 <= k的最大和
  // 这里仍然采用落差法：即利用当前的值减去之前
  // 的最小值。
  // 所以接下来，需要求k区间的最小值，我们可以认为是在求
  // 滑动窗口的最小值。可以利用单调队列来操作。
  public static long maxSumInLength(int[] A, int k) {
    final int N = A == null ? 0 : A.length;
    // 对于无效输入，这里直接返回0
    if (N == 0 || k <= 0) {
      return 0;
    }

    long[] B = new long[N];
    B[0] = A[0];
    for (int i = 1; i < N; i++) {
      B[i] = B[i - 1] + A[i];
    }

    // 利用落差法在B数组上进行求最大落差时
    // B[i] - B[j]
    // 表示的是[j + 1 .... i]
    // 即[j + 1 ... i + 1)
    // 其长度为i - j
    // 如果希望i - j <= k
    // 那么i - k <= j
    // 前面的查询范围是[i - k, .... i - 0]
    // 这个长度就是 k + 1
    // 验证这个比较简单，考虑k = 1的时候的情况
    // 如果不k++，那么将找不到数组里面的最大值。
    k++;

    increaseQueue Q = new increaseQueue(N);
    long ans = Integer.MIN_VALUE;
    for (int i = 0; i < N; i++) {
      Q.push(B[i]);
      // 这里拿到窗口里面的最小值
      // 落差法，利用当前值减去前面的最小值
      ans = Math.max(ans, B[i] - Q.front());
      // assert(i + 1 - k >= 0 && (i + 1 - k) < B.size());
      if (i + 1 - k >= 0) {
        Q.pop(B[i + 1 - k]);
      } else {
        ans = Math.max(ans, B[i]);
      }
    }

    return ans;
  }

  // 没有测试数据，利用暴力算法来验证
  public static long bf(int[] A, int k) {
    final int N = A == null ? 0 : A.length;
    long[] B = new long[N];

    // B数组是A数组的前缀和
    B[0] = A[0];
    for (int i = 1; i < N; i++) {
      B[i] = B[i - 1] + A[i];
    }

    long ans = 0;
    for (int i = 0; i < N; i++) {
      if (i + 1 <= k) {
        ans = Math.max(ans, B[i]);
      }
      int j = i - 1;
      // [0 .... j] 前面的和是B[j]
      // B[i] - B[j] 表示的是[j + 1, ... , i]
      // 那么范围就是[j + 1, ... , i + 1)
      // 长度就是i - j
      while (i - j <= k && j >= 0) {
        // assert(i >= 0 && i < B.size());
        // assert(j >= 0 && j < B.size());
        long delta = B[i] - B[j];
        ans = Math.max(ans, delta);
        j--;
      }
    }

    return ans;
  }

  public static void main(String[] args) {
    int N = 0;
    int k = 0;

    int testCases = 100;
    while (testCases-- > 0) {
      N = (int) (Math.random()) * 1000;
      N += 1;

      k = (int) (Math.random()) * 2000;
      if (k == 0) {
        k = 1;
      }

      int[] A = new int[N];
      for (int i = 0; i < N; i++) {
        A[i] = (int) (Math.random()) - 50;
      }

      long x = bf(A, k);
      long y = maxSumInLength(A, k);
      assert x == y;
    }
  }
}
