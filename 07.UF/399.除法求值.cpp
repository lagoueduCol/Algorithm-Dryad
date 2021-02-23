/*
 * @lc app=leetcode.cn id=399 lang=cpp
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
 * 给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，其中
 equations[i] = [Ai, Bi] 和
 * values[i] 共同表示等式 Ai / Bi = values[i] 。每个 Ai 或 Bi
 是一个表示单个变量的字符串。
 *
 * 另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j
 个问题，请你根据已知条件找出 Cj / Dj
 * = ? 的结果作为答案。
 *
 * 返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0
 替代这个答案。如果问题中出现了给定的已知条件中没有出现的字符串，也需要用 -1.0
 * 替代这个答案。
 *
 * 注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0
 的情况，且不存在任何矛盾的结果。
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

#include <assert.h>
#include <limits.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include <algorithm>
#include <iostream>
#include <numeric>
#include <queue>
#include <string>
#include <unordered_map>
#include <unordered_set>
#include <vector>

using namespace std;

// @lc code=start

class Solution {
    // 一开始初始化并查集
    // a->b这个关系记录在F里面
    // a/b的值记录在C里面。
    unordered_map<string, double> C;
    unordered_map<string, string> F;
    string Find(string x) {
        auto b = x;
        double base = 1;
        while (F[x] != x) {
            base *= C[x];
            x = F[x];
        }
        // 这里x就是root
        // base x -> root的映射值
        // 把路径上的其他值一并压缩
        auto root = x;
        while (F[b] != root) {
            // 修改值上的变化
            auto next = base / C[b];
            C[b] = base;
            base = next;

            auto par = F[b];
            F[b] = root;
            b = par;
        }
        return root;
    }

    // T / D = V;
    void Union(string T, string D, double v) {
        if (F[T] == T) {
            // 如果T就是一个根结点
            // 那么这里让T指向d parent
            auto par = Find(D);
            F[T] = par;
            C[T] = v * C[D];
        } else {
            // 那么找到T的root
            auto tpar = Find(T);
            // T = C[T] * par
            auto dpar = Find(D);
            // D = C[D] * dpar;

            // T = v * D = v * C[D] * dpar = C[T] * tpar;
            // 如果我们要让tpar 指向dpar
            // tpar = v * C[D] * dpar / C[T]
            F[tpar] = dpar;
            C[tpar] = v * C[D] / C[T];
        }
    }

   public:
    vector<double> calcEquation(vector<vector<string>> &E, vector<double> &V,
                                vector<vector<string>> &Q) {
        const int N = E.size();
        F.clear();
        C.clear();
        // 把每个公式化简
        // 主要是需要换两种方向来进行添加
        // 如果一个点已经有父结点了
        // 那么就要换个方向加进去
        for (auto &e : E) {
            auto &T = e[0];
            auto &D = e[1];
            simple(T, D);

            F[T] = T;
            C[T] = 1;
            F[D] = D;
            C[D] = 1;
        }

        // 然后看怎么把边加到集合中去
        for (int i = 0; i < N; i++) {
            auto &e = E[i];
            auto &T = e[0], D = e[1];
            Union(T, D, V[i]);
        }

        // 然后再看查询
        vector<double> ans;
        for (auto &q : Q) {
            auto T = q[0];
            auto D = q[1];

            if (T == D && F.count(D)) {
                ans.push_back(1);
                continue;
            }

            if (!F.count(T) || !F.count(D)) {
                ans.push_back(-1.0);
                continue;
            }

            auto tpar = Find(T);
            auto dpar = Find(D);

            if (tpar != dpar) {
                ans.push_back(-1);
            } else {
                ans.push_back(C[T] / C[D]);
            }
        }
        return ans;
    }
};

// @lc code=end

int main(void) {
    vector<vector<string>> E{{"a", "b"}, {"b", "c"}};
    vector<double> V{2.0, 3.0};
    vector<vector<string>> Q{
        {"a", "c"}, {"b", "a"}, {"a", "e"}, {"a", "a"}, {"x", "x"}};
    Solution s;
    s.calcEquation(E, V, Q);
    return 0;
}
