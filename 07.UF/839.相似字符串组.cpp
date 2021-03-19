/*
 * @lc app=leetcode.cn id=839 lang=cpp
 *
 * [839] 相似字符串组
 *
 * https://leetcode-cn.com/problems/similar-string-groups/description/
 *
 * algorithms
 * Hard (57.25%)
 * Likes:    119
 * Dislikes: 0
 * Total Accepted:    17.3K
 * Total Submissions: 30.2K
 * Testcase Example:  '["tars","rats","arts","star"]'
 *
 * 如果交换字符串 X 中的两个不同位置的字母，使得它和字符串 Y 相等，那么称 X 和 Y
 * 两个字符串相似。如果这两个字符串本身是相等的，那它们也是相似的。
 *
 * 例如，"tars" 和 "rats" 是相似的 (交换 0 与 2 的位置)； "rats" 和 "arts"
 * 也是相似的，但是 "star" 不与 "tars"，"rats"，或 "arts" 相似。
 *
 * 总之，它们通过相似性形成了两个关联组：{"tars", "rats", "arts"} 和
 * {"star"}。注意，"tars" 和 "arts"
 * 是在同一组中，即使它们并不相似。形式上，对每个组而言，要确定一个单词在组中，只需要这个词和该组中至少一个单词相似。
 *
 * 给你一个字符串列表 strs。列表中的每个字符串都是 strs
 * 中其它所有字符串的一个字母异位词。请问 strs 中有多少个相似字符串组？
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：strs = ["tars","rats","arts","star"]
 * 输出：2
 *
 *
 * 示例 2：
 *
 *
 * 输入：strs = ["omv","ovm"]
 * 输出：1
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1
 * 1
 * strs[i] 只包含小写字母。
 * strs 中的所有单词都具有相同的长度，且是彼此的字母异位词。
 *
 *
 *
 *
 * 备注：
 *
 * 字母异位词（anagram），一种把某个字符串的字母的位置（顺序）加以改换所形成的新词。
 *
 */

#include "../stdc++.h"

using namespace std;

// @lc code=start
class Solution {
    // 并查集数组
    vector<int> F;
    // 记录并查集中集合的个数
    int count = 0;
    // 记录集合中点的个数，比如要知道i所在集合的点有多少个: C[Find(i)]
    // 注意：这里不能直接使用C[i]
    // 因为只有根结点的统计才是正确的
    vector<int> Cnt;

    // 并查集的初始化
    void Init(int n) {
        F.resize(n);
        Cnt.resize(n);
        for (int i = 0; i < n; i++) {
            F[i] = i;
            Cnt[i] = 1;
        }
        count = n;
    }

    int Find(int x) { return x == F[x] ? x : F[x] = Find(F[x]); }

    void Union(int x, int y) {
        int xpar = Find(x);
        int ypar = Find(y);
        // 将x所在集合，合并到y所在集合
        if (xpar != ypar) {
            F[xpar] = ypar;
            // y集合里面的个数要增加
            Cnt[ypar] += Cnt[xpar];
            count--;
        }
    }

    int Size(int i) { return Cnt[Find(i)]; }

   public:
    int numSimilarGroups(vector<string> &A) {
        const int N = A.size();
        Init(N);

        // 构建边集
        // 将排序之后一样的字符放到同一个地方
        unordered_map<string, vector<int>> G;
        for (int i = 0; i < A.size(); i++) {
            auto t = A[i];
            sort(t.begin(), t.end());
            G[t].push_back(i);
        }

        auto diffCnt = [](const string &a, const string &b) {
            int cnt = 0;
            for (int i = 0; i < a.length(); i++) {
                cnt += a[i] != b[i];
            }
            return cnt;
        };

        // 然后我们再看哪些单词之间可以构成边
        for (auto &p : G) {
            auto &vs = p.second;
            for (int i = 0; i < vs.size(); i++) {
                for (int j = i + 1; j < vs.size(); j++) {
                    auto &a = A[i], &b = A[j];
                    auto dn = diffCnt(a, b);
                    if (dn == 0 || dn == 2) {
                        Union(i, j);
                    }
                }
            }
        }

        return count;
    }
};
// @lc code=end
