int nonOverlapIntervals(int[][] A) {
  final int N = A == null ? 0 : A.length;

  int preStart = Integer.MAX_VALUE;
  int ans = 0;

  Arrays.sort(A, new Comparator<int[]>() {
    public int compare(int[] a, int[] b) {
      return a[0] == b[0] ? 0 : (a[0] < b[0] ? -1 : 1);
    }
  });

  for (int i = N - 1; i >= 0; i--) {
    final int start = A[i][0];
    final int end = A[i][1];
    if (end <= preStart) {
      preStart = start;
      ans++;
    }
  }

  return ans;
}
