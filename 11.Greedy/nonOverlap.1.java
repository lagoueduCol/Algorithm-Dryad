public int nonOverlapIntervals(int[][] A) {
  final int N = A == null ? 0 : A.length;

  Arrays.sort(A, new Comparator<int[]>() {
    public int compare(int[] a, int[] b) {
      return a[1] == b[1] ? 0 : (a[1] < b[1] ? -1 : 1);
    }
  });

  int preEnd = Integer.MIN_VALUE;
  int ans = 0;

  for (int i = 0; i < N; i++) {
    final int start = A[i][0];
    if (preEnd <= start) {
      preEnd = A[i][1];
      ans++;
    }
  }

  return ans;
}
