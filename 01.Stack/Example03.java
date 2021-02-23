import java.util.Arrays;
import java.util.Stack;

class Solution {
  public static int[] findRightSmall(int[] A) {
    // 结果数组
    int[] ans = new int[A.length];
    // 注意，栈中的元素记录的是下标
    Stack<Integer> t = new Stack<>();

    for (int i = 0; i < A.length; i++) {
      final int x = A[i];
      // 每个元素都向左遍历栈中的元素完成消除动作
      while (!t.empty() && A[t.peek()] > x) {
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
class Example03 {
    public static void main(String[] args) {
        assert(Arrays.equals(new int[]{1,-1}, Solution.findRightSmall(new int[]{5,4})));
        assert(Arrays.equals(new int[]{5, 5, 5, 4, 5, -1, -1}, Solution.findRightSmall(new int[]{1, 2, 4, 9, 4, 0, 5})));
    }
}

