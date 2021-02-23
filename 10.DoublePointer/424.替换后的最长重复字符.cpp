/*
 * @lc app=leetcode.cn id=424 lang=cpp
 *
 * [424] 替换后的最长重复字符
 *
 * https://leetcode-cn.com/problems/longest-repeating-character-replacement/description/
 *
 * algorithms
 * Medium (52.64%)
 * Likes:    383
 * Dislikes: 0
 * Total Accepted:    38.7K
 * Total Submissions: 73.5K
 * Testcase Example:  '"ABAB"\n2'
 *
 * 给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，总共可最多替换 k
 * 次。在执行上述操作后，找到包含重复字母的最长子串的长度。
 *
 * 注意：字符串长度 和 k 不会超过 10^4。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：s = "ABAB", k = 2
 * 输出：4
 * 解释：用两个'A'替换为两个'B',反之亦然。
 *
 *
 * 示例 2：
 *
 *
 * 输入：s = "AABABBA", k = 1
 * 输出：4
 * 解释：
 * 将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
 * 子串 "BBBB" 有最长重复字母, 答案为 4。
 *
 *
 */

// @lc code=start
class Solution {
   public:
    int characterReplacement(string s, int k) {
        const int N = s.length();
        int left = -1;
        int max_repeat_times = 0;

        int cnt[256];
        memset(cnt, 0, sizeof(cnt));

        int ans = 0;

        for (int i = 0; i < N; i++) {
            // 这里要把s[i]加进来
            // 计数要增加
            cnt[s[i]]++;

            // 这里去拿最多重复次数
            max_repeat_times = max(max_repeat_times, cnt[s[i]]);

            // 如果破坏掉了
            if (i - left - max_repeat_times > k) {
                // 那么要移动左指针
                // 直到合法为止
                cnt[s[++left]]--;
            }

            ans = max(ans, i - left);
        }

        return ans;
    }
};
// @lc code=end
