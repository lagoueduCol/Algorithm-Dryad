// https://www.lintcode.com/problem/903/description

public class Solution {
    /**
     * @param length: the length of the array
     * @param updates: update operations
     * @return: the modified array after all k operations were executed
     */
    public int[] getModifiedArray(int length, int[][] updates) {
        // Write your code here
        int[] A = new int[length];

        for (int i = 0; i < updates.length; i++) {
            int start = updates[i][0];
            int end = updates[i][1];
            int val = updates[i][2];

            if (start > end) {
                continue;
            }

            if (0 <= start && start < length) {
                A[start] += val;
            }

            if (0 <= end && end + 1 < length) {
                A[end+1] -= val;
            }
        }

        int pre = 0;
        for (int i = 0; i < length; i++) {
            pre += A[i];
            A[i] = pre;
        }

        return A;
    }
}
