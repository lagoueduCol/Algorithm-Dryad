import java.util.ArrayList;

/*
 * @lc app=leetcode.cn id=784 lang=java
 *
 * [784] 字母大小写全排列
 *
 * https://leetcode-cn.com/problems/letter-case-permutation/description/
 *
 * algorithms
 * Medium (66.73%)
 * Likes:    256
 * Dislikes: 0
 * Total Accepted:    30.6K
 * Total Submissions: 45.8K
 * Testcase Example:  '"a1b2"'
 *
 * 给定一个字符串S，通过将字符串S中的每个字母转变大小写，我们可以获得一个新的字符串。返回所有可能得到的字符串集合。
 * 
 * 
 * 
 * 示例：
 * 输入：S = "a1b2"
 * 输出：["a1b2", "a1B2", "A1b2", "A1B2"]
 * 
 * 输入：S = "3z4"
 * 输出：["3z4", "3Z4"]
 * 
 * 输入：S = "12345"
 * 输出：["12345"]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * S 的长度不超过12。
 * S 仅由数字和字母组成。
 * 
 * 
 */

// @lc code=start
class Solution {
    private void backTrace(String s,
                           int i,
                           StringBuilder box,
                           List<String> ans) {
        final int N = s.length();
        if (i > N) {
            return;
        }

        if (i == N) {
            ans.add(box.toString());
            return;
        }

        final char c = s.charAt(i);
        if ('0' <= c && c <= '9') {
            box.append(c);
            backTrace(s, i + 1, box, ans);
            box.setLength(box.length()-1);
        } else {
            // lower case
            box.append(Character.toLowerCase(c));
            backTrace(s, i + 1, box, ans);
            box.setLength(box.length()-1);

            // upper case
            box.append(Character.toUpperCase(c));
            backTrace(s, i + 1, box, ans);
            box.setLength(box.length()-1);
        }
    }
    public List<String> letterCasePermutation(String S) {
        List<String> ans = new ArrayList<>();
        StringBuilder box = new StringBuilder();
        backTrace(S, 0, box, ans);
        return ans;
    }
}
// @lc code=end

