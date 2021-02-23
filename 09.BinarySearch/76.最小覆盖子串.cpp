/*
 * @lc app=leetcode.cn id=76 lang=cpp
 *
 * [76] 最小覆盖子串
 *
 * https://leetcode-cn.com/problems/minimum-window-substring/description/
 *
 * algorithms
 * Hard (40.63%)
 * Likes:    949
 * Dislikes: 0
 * Total Accepted:    107.9K
 * Total Submissions: 265.4K
 * Testcase Example:  '"ADOBECODEBANC"\n"ABC"'
 *
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s
 * 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 *
 * 注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 *
 *
 * 示例 2：
 *
 *
 * 输入：s = "a", t = "a"
 * 输出："a"
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1
 * s 和 t 由英文字母组成
 *
 *
 *
 * 进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？
 */

// @lc code=start
class Solution {
   public:
    string minWindow(string A, string B) {
        const int alen = A.length();
        const int blen = B.length();

        constexpr int SZ = 256;
        int bcnt[SZ], acnt[SZ];
        memset(bcnt, 0, sizeof(bcnt));

        for (auto c : B) {
            bcnt[c]++;
        }

        int buniq = 0;
        for (int i = 0; i < SZ; i++) {
            buniq += !!bcnt[i];
        }

        string ans;

        // 注意这个映射函数的作用。
        // 也同时完成了找到最优值的功能
        // 每次找到最优结果之后，就将它放到ans里面。
        auto getC = [&](const int len) {
            memset(acnt, 0, sizeof(acnt));
            int large_cnt = 0;
            for (int i = 0; i < alen; i++) {
                const char c = A[i];
                if (++acnt[c] == bcnt[c]) {
                    large_cnt++;
                }

                if (i < len - 1) {
                    continue;
                }

                if (large_cnt >= buniq) {
                    ans = A.substr(i + 1 - len, len);
                    return 0;
                }

                // 移除长度为len的开头的那个字符
                // 为后面的计数做准备。
                const char old = A[i + 1 - len];
                if (bcnt[old] > 0 && acnt[old]-- == bcnt[old]) {
                    large_cnt--;
                }
            }

            return -1;
        };

        // 注意这里搜索的范围是[blen ~ alen + 1)
        // 从blen开始可以节省时间。因为最小的可能有效的字符串长度为
        // blen.
        int l = blen, r = alen + 1;
        while (l < r) {
            const int m = l + ((r - l) >> 1);
            const int mov = getC(m);
            if (mov < 0) {
                l = m + 1;
            } else {
                r = m;
            }
        }

        if (l > alen) return "";
        return ans;
    }
};
// @lc code=end
