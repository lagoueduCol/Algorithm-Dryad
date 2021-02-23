public class BinarySearch {
  private boolean binarySearch(int[] A, int target) {
    bool binary_search(int[] A, int target) {
      if (A == null || A.length == 0) {
        return false;
      }
      int l = 0, r = A.length;
      while (l < r) {
        final int m = l + ((r - l) >> 1);
        if (A[m] == target) {
          return true;
        }
        if (A[m] < target) {
          l = m + 1;
        } else {
          r = m;
        }
      }
      return false;
    }
  }
  public static void main(String[] args) {}
}
