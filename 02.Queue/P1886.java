import java.io.*;
import java.util.*;

public class Main {
    // 这里cin, out都是属于java read/write时的一些优化
    // 与核心代码关系不大
    static BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
    static StreamTokenizer tk = new StreamTokenizer(cin);

    private static int nextInt() throws IOException {
        tk.nextToken();
        return (int) tk.nval;
    }

    static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

    public static void main(String args[]) throws Exception {
        int n = nextInt();
        int k = nextInt();

        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = nextInt();
        }

        ArrayDeque<Integer> Q = new ArrayDeque<>();
        Q.clear();

        // 首先求
        for (int i = 0; i < n; i++) {
            // push到单调队列中
            // 这里是要求最小，所以需要用单调递增队列
            while (!Q.isEmpty() && Q.getLast() > A[i]) {
                Q.pollLast();
            }
            Q.offerLast(A[i]);

            // 如果队列中元素太少
            if (i < k - 1) {
                continue;
            }

            // 输出最小元素
            out.print(Q.getFirst() + " ");
            if (i % 100 == 0) {
                out.flush();
            }

            // pop单调队列
            if (Q.getFirst() == A[i-k+1]) {
                Q.pollFirst();
            }
        }
        out.println();
        out.flush();

        Q.clear();
        for (int i = 0; i < n; i++) {
            while (!Q.isEmpty() && Q.getLast() < A[i]) {
                Q.pollLast();
            }
            Q.offerLast(A[i]);

            // 如果队列中元素太少
            if (i < k - 1) {
                continue;
            }

            // 输出滑动窗口最大值
            out.print(Q.getFirst() + " ");
            if (i % 100 == 0) {
                out.flush();
            }

            // pop单调队列
            if (Q.getFirst() == A[i-k+1]) {
                Q.pollFirst();
            }
        }
        out.println();
        out.flush();
    }
}
