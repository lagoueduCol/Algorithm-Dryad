// 测试平台 http://poj.org/problem?id=3984 Java

import java.io.*;
import java.util.*;

// your code here
class Node {
  public int r;
  public int c;
  public Node() {}
  public Node(int a, int b) {
    r = a;
    c = b;
  }
}

class Solution {
  private List<Node> shortPath = null;
  private int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

  private List<Node> Clone(List<Node> a) {
    List<Node> ans = new ArrayList<Node>();
    for (int i = 0; i < a.size(); i++) {
      Node x = a.get(i);
      ans.add(new Node(x.r, x.c));
    }
    return ans;
  }

  /**
   * @param A     迷宫图
   * @param vis   访问记录
   * @param r     出发点(r, c)
   * @param c
   * @param tmp   走到r,c的路径
   */
  public void dfs(int[][] A, int[][] step, boolean[][] vis, int r, int c, List<Node> tmp) {
    // 如果越界
    final int R = A.length;
    final int C = A[0].length;

    // 如果已经走到终点
    if (r == R - 1 && c == C - 1) {
      if (shortPath == null || shortPath.size() > tmp.size()) {
        shortPath = Clone(tmp);
      }
      return;
    }

    // 剪枝1
    if (shortPath != null && tmp.size() >= shortPath.size()) return;
  
    // 剪枝2
    // 如果发现走到step[r][c]比以前用了更多的步数，那么直接返回
    if (tmp.size() - 1 > step[r][c]) {
      return;
    }

    step[r][c] = tmp.size() - 1;

    // 接下来看当前出发点的四个选择
    for (int d = 0; d < 4; d++) {
      final int nr = r + dir[d][0];
      final int nc = c + dir[d][1];

      // 如果是越界的
      if (nr < 0 || nr >= R || nc < 0 || nc >= C) {
        continue;
      }

      // 如果是不能访问的
      // 或者说已经访问过了
      if (A[nr][nc] == 1 || vis[nr][nc] == true) {
        continue;
      }

      vis[nr][nc] = true;
      tmp.add(new Node(nr, nc));

      dfs(A, step, vis, nr, nc, tmp);

      vis[nr][nc] = false;
      tmp.remove(tmp.size()-1);
    }
  }

  /**
   * @param A     5x5的迷宫图
   * @param ans   路径int[x][2]
   * @return      路径的长度，所有的路径会存放在ans中，假设ans总是足够长
   */
  public List<Node> findMinPath(int[][] A) {
    List<Node> ans = null;
    if (A == null || A[0].length == 0) {
      return ans;
    }

    final int R = A.length;
    final int C = A[0].length;

    boolean[][] vis = new boolean[R][C];
    int[][] step = new int[R][C];

    // 记录每个点的从出发点走的最小的步数，一开始为 R * C
    for (int r = 0; r < R; r++) {
      for (int c = 0; c < C; c++) {
        step[r][c] = R * C + 1;
      }
    }

    // 路径最长为遍历所有的点
    List<Node> tmp = new ArrayList<Node>();

    // 出发点[0, 0]
    tmp.add(new Node(0, 0));
    vis[0][0] = true;

    dfs(A, step, vis, 0, 0, tmp);

    tmp.remove(tmp.size() - 1);
    vis[0][0] = false;

    return shortPath;
  }
}

// Test code
public class Main {
  static private int pointsNumber = 0;
  static private int[][] A = new int[5][5];
  static private Solution s = new Solution();
  public static void main(String args[]) throws Exception {
    Scanner sc = new Scanner(System.in);

    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        A[i][j] = sc.nextInt();
      }
    }

    List<Node> ans = s.findMinPath(A);
    for (int i = 0; ans != null && i < ans.size(); i++) {
      Node x = ans.get(i);
      System.out.println("(" + x.r + ", " + x.c + ")");
    }
  }
}
