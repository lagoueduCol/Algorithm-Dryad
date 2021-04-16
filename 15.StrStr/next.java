class Solution {
    // 如果一个字符串可以由一个子串重复构成，比如
    // AAAA
    // 那么前后缀的最长匹配为"AAA"
    // 那么s.length() - 这个最长匹配
    // 就是得到重复部分的长度
    public int[] buildNext(String sub) {
        final int N = sub == null ? 0 : sub.length();
        int[] next = new int[N+1];

        int i = 0;
        int j = -1;

        next[0] = -1;

        while (i < N) {
            if (-1 == j || sub.charAt(i) == sub.charAt(j)) {
                i++;
                j++;
                next[i] = j;
            } else {
                j = next[j];
            }
        }

        return next;
    }
}
