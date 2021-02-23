/*
 * @lc app=leetcode.cn id=30 lang=java
 *
 * [30] 串联所有单词的子串
 *
 * https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words/description/
 *
 * algorithms
 * Hard (33.80%)
 * Likes:    417
 * Dislikes: 0
 * Total Accepted:    56.2K
 * Total Submissions: 166.2K
 * Testcase Example:  '"barfoothefoobarman"\n["foo","bar"]'
 *
 * 给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 * 
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：
 * ⁠ s = "barfoothefoobarman",
 * ⁠ words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：
 * 从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 * 
 * 
 * 示例 2：
 * 
 * 输入：
 * ⁠ s = "wordgoodgoodgoodbestword",
 * ⁠ words = ["word","good","best","word"]
 * 输出：[]
 * 
 * 
 */

import java.util.*;

// @lc code=start

class Counter extends HashMap<String, Integer> {
    public int get(String k) {
        return containsKey(k) ? super.get(k) : 0;
    }

    public void add(String k, int v) {
        put(k, get(k) + v);
        if (get(k) <= 0) {
            remove(k);
        }
    }
}

class Solution {
    public List<Integer> findSubstring(String s, String[] D) {
        Counter H = new Counter();
        int wordLength = 0;
        // 统计字典中单词出现的次数
        for (String w : D) {
            wordLength = w.length();
            H.add(w, 1);
        }

        List<Integer> ans = new ArrayList<>();

        for (int start = 0; start < wordLength; start++) {
            // 记录当前位置开始切分的单词的
            // 计数器
            Counter R = new Counter();
            int left = start - wordLength;
            int equalCount = 0;
            // 有效的区间是(left, i]
            int counter = 0;

            for (int i = start; i + wordLength <= s.length(); i += wordLength) {
                String tmp = s.substring(i, i + wordLength);
                R.add(tmp, 1);

                if (R.get(tmp) == H.get(tmp)) {
                    equalCount++;
                }

                counter++;

                // 如果窗口太小
                if (counter < D.length) {
                    continue;
                }

                // 到这里时，窗口的长度已经一样了
                // 看一下命中率
                if (equalCount == H.size()) {
                    ans.add(left + wordLength);
                }

                // 移除开头的那个元素
                left += wordLength;
                String rm = s.substring(left, left + wordLength);
                if (R.get(rm) == H.get(rm)) {
                    equalCount--;
                }
                R.add(rm, -1);
            }
        }

        return ans;
    }
}

// @lc code=end
