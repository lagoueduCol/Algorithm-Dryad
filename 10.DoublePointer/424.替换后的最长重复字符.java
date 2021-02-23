/*
 * @lc app=leetcode.cn id=424 lang=java
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
    public int characterReplacement(String s, int k) {
        final int N = s.length();
        int left = -1;

        int[] cnt = new int[256];
        int oneNumber = 0;
        int ans = 0;

        for (int i = 0; i < N; i++) {
            final int c = s.charAt(i);
            // 统计字符个数
            cnt[c]++;

            // 考点：
            // 只有统计值发生变动的那个字符才有可能
            // 成为最多的字符. 如果我们把出现次数
            // 最多的字符当成主力。这里就是选出主力字符
            oneNumber = Math.max(oneNumber, cnt[c]);

            // 考点：最长区间
            // 坏了才移动原则：当新字符加进来
            // 如果除了主力字符，剩下的还是太多
            // 那么需要移除一个就可以了
            if (i - left - oneNumber > k) {
                final int old = s.charAt(++left);
                cnt[old]--;
            }

            // 到这里为止，(left, i]就是一个满足要求的区间
            ans = Math.max(ans, i - left);
        }

        return ans;
    }
}
// @lc code=end

