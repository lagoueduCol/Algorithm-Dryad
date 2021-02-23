/*
928. 最多有两个不同字符的最长子串
中文
English
给定一个字符串，找出最长子串
T
T的长度，它最多包含2个不同的字符。

样例
Example 1

Input: “eceba”
Output: 3
Explanation:
T is "ece" which its length is 3.
Example 2

Input: “aaa”
Output: 3

测试平台： https://www.lintcode.com/problem/longest-substring-with-at-most-two-distinct-characters/
*/

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

public class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        // Write your code here
        final int N = s == null ? 0 : s.length();
        int left = -1;
        int ans = 0;
        
        Counter H = new Counter();
        
        for (int i = 0; i < N; i++) {
            // 将s[i]加到区间里面
            H.add(s.charAt(i), 1);
            
            // 如果H里面的数目超过2
            while (H.size() > 2) {
                Character c = s.charAt(++left);
                H.add(c, -1);
            }
            
            ans = Math.max(ans, i - left);
        }
        
        return ans;
    }
}

