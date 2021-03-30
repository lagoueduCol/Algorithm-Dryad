// https://www.lintcode.com/problem/minimum-risk-path/

import java.util.*;

public class Solution {
  class Node {
    public int node = 0;
    public int risk = 0;

    public Node() {
    }

    public Node(int a, int b) {
      node = a;
      risk = b;
    }
  }

  /**
   * @param n: maximum index of position.
   * @param m: the number of undirected edges.
   * @param x:
   * @param y:
   * @param w:
   * @return: return the minimum risk value.
   */
  public int getMinRiskValue(int n, int m, int[] x, int[] y, int[] w) {
    // 构建图的表示
    List<Node> G[] = new ArrayList[n + 1];
    for (int i = 0; i < n + 1; i++) {
      G[i] = new ArrayList<>();
    }

    for (int i = 0; i < m; i++) {
      final int a = x[i], b = y[i], c = w[i];
      G[a].add(new Node(b, c));
      G[b].add(new Node(a, c));
    }

    int[] risk = new int[n + 1];
    for (int i = 0; i <= n; i++) {
      risk[i] = Integer.MAX_VALUE;
    }
    // java小堆
    Queue<Integer> Q = new PriorityQueue<>((v1, v2) -> risk[v1] - risk[v2]);

    // 把起始点入堆
    Q.offer(0);
    risk[0] = 0;

    while (!Q.isEmpty()) {
      int cur = Q.poll();
      for (Node next : G[cur]) {
        final int back = next.node;
        final int backRisk = Math.max(risk[cur], next.risk);
        if (backRisk < risk[back]) {
          risk[back] = backRisk;
          Q.offer(back);
        }
      }
    }

    return risk[n];
  }
}