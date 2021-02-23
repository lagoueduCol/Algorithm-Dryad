
import java.util.Arrays;
import java.util.Stack;

class Solution {
  public static int[] findSmallSeq(int[] nums, int k) {
    int[] ans = new int[k];
    Stack<Integer> s = new Stack<>();

    // 这里生成单调栈
    for (int i = 0; i < nums.length; i++) {
      final int x = nums[i];
      final int left = nums.length - i;
      // 注意我们想要提取出k个数，所以注意控制扔掉的数的个数
      while (!s.empty() && (s.size() + left > k) && s.peek() > x) {
        s.pop();
      }
      s.push(x);
    }

    // 如果递增栈里面的数太多，那么我们只需要取出前k个就可以了。
    // 多余的栈中的元素需要扔掉。
    while (s.size() > k) {
      s.pop();
    }

    // 把k个元素取出来，注意这里取的顺序!
    for (int i = k - 1; i >= 0; i--) {
      ans[i] = s.peek();
      s.pop();
    }

    return ans;
  }
}

class Example04 {
  public static void main(String[] args) {
    assert(Arrays.equals(new int[]{1,2,3}, Solution.findSmallSeq(new int[]{9,2,4,5,1,2,6,3,100,4}, 3)));
    assert(Arrays.equals(new int[]{1,2}, Solution.findSmallSeq(new int[]{9,2,4,5,1,2,6,3,100,4}, 2)));
    assert(Arrays.equals(new int[]{1}, Solution.findSmallSeq(new int[]{9,2,4,5,1,2,6,3,100,4}, 1)));
    assert(Arrays.equals(new int[]{1}, Solution.findSmallSeq(new int[]{1,1,1,1,1}, 1)));
  }
}