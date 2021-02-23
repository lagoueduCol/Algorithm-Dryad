import java.util.HashMap;

/*
 * @lc app=leetcode.cn id=399 lang=java
 *
 * [399] 除法求值
 *
 * https://leetcode-cn.com/problems/evaluate-division/description/
 *
 * algorithms
 * Medium (59.38%)
 * Likes:    468
 * Dislikes: 0
 * Total Accepted:    32.1K
 * Total Submissions: 54.1K
 * Testcase Example:  '[["a","b"],["b","c"]]\n' +
  '[2.0,3.0]\n' +
  '[["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]'
 *
 * 给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，其中 equations[i] = [Ai, Bi] 和
 * values[i] 共同表示等式 Ai / Bi = values[i] 。每个 Ai 或 Bi 是一个表示单个变量的字符串。
 * 
 * 另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj
 * = ? 的结果作为答案。
 * 
 * 返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。如果问题中出现了给定的已知条件中没有出现的字符串，也需要用 -1.0
 * 替代这个答案。
 * 
 * 注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries =
 * [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
 * 输出：[6.00000,0.50000,-1.00000,1.00000,-1.00000]
 * 解释：
 * 条件：a / b = 2.0, b / c = 3.0
 * 问题：a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
 * 结果：[6.0, 0.5, -1.0, 1.0, -1.0 ]
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0],
 * queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
 * 输出：[3.75000,0.40000,5.00000,0.20000]
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：equations = [["a","b"]], values = [0.5], queries =
 * [["a","b"],["b","a"],["a","c"],["x","y"]]
 * 输出：[0.50000,2.00000,-1.00000,-1.00000]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * equations[i].length == 2
 * 1 i.length, Bi.length 
 * values.length == equations.length
 * 0.0 < values[i] 
 * 1 
 * queries[i].length == 2
 * 1 j.length, Dj.length 
 * Ai, Bi, Cj, Dj 由小写英文字母与数字组成
 * 
 * 
 */
import java.util.*;

// @lc code=start
class Solution {
    private void addToMap(String key, Map<String, Integer> H) {
        final int id = H.size();
        if (!H.containsKey(key)) {
            H.put(key, id);
        }
    }

    private int[] F = null;
    private double[] C = null;

    private void Init(int n) {
        F = new int[n];
        C = new double[n];
        for (int i = 0; i < n; i++) {
            F[i] = i;
            C[i] = 1;
        }
    }

    private int Find(int x) {
        int b = x;
        // base用来保存从x -> .... root
        // 这条路径上所有的乘积
        // 最后保证可以得到
        // x = base * root
        double base = 1;
        while (x != F[x]) {
            base *= C[x];
            x = F[x];
        }
        // 这里x就是root
        // base x -> root的映射值
        // 把路径上的其他值一并压缩
        int root = x;
        while (F[b] != root) {
            // 修改值上的变化
            double next = base / C[b];
            C[b] = base;
            base = next;

            int par = F[b];
            F[b] = root;
            b = par;
        }
        return root;
    }

    private void Union(int T, int D, double v) {
        // T / D = v;
        // 给定的输入表示 T = v * D;
        // 那么找到T的root
        int tpar = Find(T);
        // T = C[T] * par
        int dpar = Find(D);
        // D = C[D] * dpar;

        // T = v * D = v * C[D] * dpar = C[T] * tpar;
        // 如果我们要让tpar 指向dpar
        // tpar = v * C[D] * dpar / C[T]
        F[tpar] = dpar;
        C[tpar] = v * C[D] / C[T];
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // 为了方便后面操作，我们把所有的字符串都映射成整数
        Map<String, Integer> H = new HashMap<>();
        for (List<String> l : equations) {
            String t = l.get(0), d = l.get(1);
            addToMap(t, H);
            addToMap(d, H);
        }

        // 初始化并查集
        Init(H.size());

        // 开始执行Union操作
        for (int i = 0; i < equations.size(); i++) {
            List<String> l = equations.get(i);
            Union(H.get(l.get(0)), H.get(l.get(1)), values[i]);
        }

        double[] ans = new double[queries.size()];

        for (int i = 0; i < queries.size(); i++) {
            List<String> l = queries.get(i);
            int tidx = H.containsKey(l.get(0)) ? H.get(l.get(0)) : -1;
            int didx = H.containsKey(l.get(1)) ? H.get(l.get(1)) : -1;

            if (tidx == -1 || didx == -1) {
                ans[i] = -1;
            } else {
                int troot = Find(tidx);
                int droot = Find(didx);
                if (troot != droot) {
                    ans[i] = -1;
                } else {
                    ans[i] = C[tidx] / C[didx];
                }
            }
        }

        return ans;
    }
}
// @lc code=end
