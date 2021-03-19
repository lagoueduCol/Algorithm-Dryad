/*
 * @lc app=leetcode.cn id=839 lang=java
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
 * 例如，"tars" 和 "rats" 是相似的 (交换 0 与 2 的位置)； "rats" 和 "arts" 也是相似的，但是 "star" 不与
 * "tars"，"rats"，或 "arts" 相似。
 * 
 * 总之，它们通过相似性形成了两个关联组：{"tars", "rats", "arts"} 和 {"star"}。注意，"tars" 和 "arts"
 * 是在同一组中，即使它们并不相似。形式上，对每个组而言，要确定一个单词在组中，只需要这个词和该组中至少一个单词相似。
 * 
 * 给你一个字符串列表 strs。列表中的每个字符串都是 strs 中其它所有字符串的一个字母异位词。请问 strs 中有多少个相似字符串组？
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

import java.util.*;

// @lc code=start

class Counter extends HashMap<String, List<Integer>> {
    public List<Integer> get(String k) {
        return super.get(k);
    }

    public void add(String k, int i) {
        List<Integer> ret = get(k);
        if (ret == null) {
            ret = new ArrayList<>();
            ret.add(i);
            super.put(k, ret);
        } else {
            ret.add(i);
        }
    }
}

class Solution {
    private int[] F = null;
    private int count = 0;

    private void Init(int n) {
        F = new int[n];
        for (int i = 0; i < n; i++) {
            F[i] = i;
        }
        count = n;
    }

    private int Find(int x) {
        if (x == F[x]) {
            return x;
        }
        F[x] = Find(F[x]);
        return F[x];
    }

    private void Union(int x, int y) {
        if (Find(x) != Find(y)) {
            count--;
        }
        F[Find(x)] = Find(y);
    }

    private static String sortString(String s) {
        char t[] = s.toCharArray();
        Arrays.sort(t);
        return new String(t);
    }

    private int diffCount(String a, String b) {
        int ans = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                ans++;
            }
        }
        return ans;
    }

    public int numSimilarGroups(String[] A) {
        final int N = A == null ? 0 : A.length;
        Init(N);

        // 构建图
        Counter G = new Counter();
        for (int i = 0; i < N; i++) {
            String t = sortString(A[i]);
            G.add(t, i);
        }

        // 查看哪些字符串之间会存在边
        for (Map.Entry<String, List<Integer>> e : G.entrySet()) {
            List<Integer> vs = e.getValue();
            for (int i = 0; i < vs.size(); i++) {
                for (int j = i + 1; j < vs.size(); j++) {
                    int dn = diffCount(A[i], A[j]);
                    if (dn == 0 || dn == 2) {
                        Union(i, j);
                    }
                }
            }
        }

        return count;

    }
}
// @lc code=end
