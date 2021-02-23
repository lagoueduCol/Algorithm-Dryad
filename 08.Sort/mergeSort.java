class Solution {
  private void msort(int[] a, int b, int e, int t[]) {
    // 空区间
    if (b >= e) {
      return;
    }
    // 空区间中只有一个结点
    if (b + 1 == e) {
      return;
    }
    // 分成两半, 二叉树可以自动取得root.left, root.right
    // 这里我们需要通过计算来得到左右子数组。
    final int m = b + ((e - b) >> 1);

    // 类比二叉树分别遍历左子树和右子树。
    msort(a, b, m, t);
    msort(a, m, e, t);

    int i = b;
    int j = m;
    int to = b;

    // 将两个子数组进行合并, 注意下面是一个很重要的模板
    while (i < m || j < e) {
      if (j >= e || i < m && a[i] <= a[j]) {
        t[to++] = a[i++];
      } else {
        t[to++] = a[j++];
      }
    }
    // 把合并的结果拷回原来的数组a[]
    for (i = b; i < e; i++) {
      a[i] = t[i];
    }
  }
  public void mergeSort(int[] nums) {
    // 如果传进来的数组为空
    if (nums == null || nums.length == 0) {
      return;
    }
    // t是一个临时中转的数组
    int[] t = new int[nums.length];
    msort(nums, 0, nums.length, t);
  }
}
