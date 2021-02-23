/*
给定字符串S，找到最多有k个不同字符的最长子串T。

样例
样例 1:

输入: S = "eceba" 并且 k = 3
输出: 4
解释: T = "eceb"
样例 2:

输入: S = "WORLD" 并且 k = 4
输出: 4
解释: T = "WORL" 或 "ORLD"
挑战
O(n) 时间复杂度

https://www.lintcode.com/problem/386/
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
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        // write your code here
        final int N = s == null ? 0 : s.length();
        int left = -1;
        int ans = 0;
        
        Counter H = new Counter();
        
        for (int i = 0; i < N; i++) {
            H.add(s.charAt(i), 1);
            
            while (H.size() > k) {
                Character c = s.charAt(++left);
                H.add(c, -1);
            }
            
            ans = Math.max(ans, i - left);
        }
        return ans;
    }
}


