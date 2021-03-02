import java.util.Arrays;
import java.util.Stack;

/*
 * 题目：给定一个数组，要找到这个数组里面每个元素右边比我大的元素的位置
 * - 注意：是右边第一个比我大的，如果有多个的话
 * - 如果没有，那么用-1表示。
 * 返回：一个数组，表示右边比我大的数的下标位置
 *
 * 输入：[5, 6]
 * 输出：[1, -1]
 * 解释：A[0] = 5，右边比我大的是A[1] = 6, 所以记录为 = 1
 *       A[1] = 6, 右边比我大的元素没有，所以记录为   = -1
 *       所以返回值是[1, -1]
 */

class Solution {
  // 当我们要找右边比我大的元素的时候，需要用递减栈
  public static int[] findRightLarge(int[] A) {
    if (A == null || A.length == 0) {
      return new int[0];
    }

    // 结果数组
    int[] ans = new int[A.length];
    // 注意，栈中的元素记录的是下标
    Stack<Integer> t = new Stack<>();

    for (int i = 0; i < A.length; i++) {
      final int x = A[i];
      // 每个元素都向左遍历栈中的元素完成消除动作
      // 这里是递减栈
      // 如果发现进来的元素x与栈中元素相比
      // 如果大于栈中的元素，那么要把栈中的元素弹出去
      while (!t.empty() && A[t.peek()] < x) {
        // 消除的时候，记录一下被谁消除了
        ans[t.peek()] = i;
        // 消除时候，值更大的需要从栈中消失
        t.pop();
      }
      // 剩下的入栈
      t.push(i);
    }
    // 栈中剩下的元素，由于没有人能消除他们，因此，只能将结果设置为-1。
    while (!t.empty()) {
      ans[t.peek()] = -1;
      t.pop();
    }

    return ans;
  }
}

// 测试代码

class Verify {
  private static int findRightLarge(int[] A, int i) {
    for (int j = i + 1; j < A.length; j++) {
      if (A[j] > A[i]) {
        return j;
      }
    }
    return -1;
  }

  private static void check(int[] A, int[] ans) {
    if (A == null || A.length == 0) {
      return;
    }

    final int N = A.length;
    for (int i = 0; i < N; i++) {
      final int r = ans[i];
      if (r != findRightLarge(A, i)) {
        System.out.println("ERROR");
      }
    }
  }

  public static void DoubleCheck(int[] A) {
    int[] ans = Solution.findRightLarge(A);
    check(A, ans);
  }

  private static int NextInt() {
    final double d = Math.random();
    final int i = (int) (d * 1000);
    return i;
  }

  public static void RandomCheck() {
    for (int i = 0; i < 100; i++) {
      final int len = NextInt() + 1;
      int[] A = new int[len];
      for (int j = 0; j < len; j++) {
        A[j] = NextInt();
      }

      DoubleCheck(A);
    }
  }
}

class Practice03A {
  public static void main(String[] args) {
    Verify.DoubleCheck(new int[] { 5, 4 });
    Verify.DoubleCheck(new int[] { 1, 2, 4, 9, 4, 0, 5 });

    Verify.RandomCheck();

  }
}
