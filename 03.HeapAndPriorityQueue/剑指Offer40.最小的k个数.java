// 测试平台链接：https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/
class Solution {
    public int[] getLeastNumbers(int[] arr, int k) {
        if (k <= 0 || arr == null || arr.length == 0) {
            return new int[0];
        }

        Queue<Integer> Q = new PriorityQueue<>((v1, v2) -> v2 - v1);
        for (int x: arr) {
            Q.offer(x);
            while (Q.size() > k) {
                Q.poll();
            }
        }

        int[] ans = new int[k];
        int i = 0;
        for (int x: Q) {
            ans[i++] = x;
        }

        return ans;
    }
}
