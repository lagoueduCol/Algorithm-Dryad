/*
 * @lc app=leetcode.cn id=567 lang=java
 *
 * [567] 字符串的排列
 *
 * https://leetcode-cn.com/problems/permutation-in-string/description/
 *
 * algorithms
 * Medium (41.56%)
 * Likes:    305
 * Dislikes: 0
 * Total Accepted:    73.9K
 * Total Submissions: 177.3K
 * Testcase Example:  '"ab"\n"eidbaooo"'
 *
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 * 
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 输入的字符串只包含小写字母
 * 两个字符串的长度都在 [1, 10,000] 之间
 * 
 * 
 */

// @lc code=start

class Counter extends HashMap<Character, Integer> {
    public int get(Character k) {
        return containsKey(k) ? super.get(k) : 0;
    }

    public void add(Character k, int v) {
        put(k, get(k) + v);
        if (get(k) <= 0) {
            remove(k);
        }
    }
}

class Solution {
    public boolean checkInclusion(String A, String B) {
        // B contains A?
        final int blen = B == null ? 0 : B.length();
        final int alen = A == null ? 0 : A.length();

        Counter H = new Counter();
        for (int i = 0; i < alen; i++) {
            H.add(A.charAt(i), 1);
        }

        Counter R = new Counter();
        int left = -1;
        int equal = 0;

        for (int i = 0; i < blen; i++) {
            Character c = B.charAt(i);
            R.add(c, 1);
            if (R.get(c) == H.get(c)) {
                equal++;
            }

            if (i - left < alen) {
                continue;
            }

            if (equal == H.size()) {
                return true;
            }

            // reamove head
            left++;
            Character rm = B.charAt(left);
            if (R.get(rm) == H.get(rm)) {
                equal--;
            }
            R.add(rm, -1);
        }

        return false;
    }
}

// @lc code=end
