class Solution {
    // 如果一个字符串可以由一个子串重复构成，比如
    // AAAA
    // 那么前后缀的最长匹配为"AAA"
    // 那么s.length() - 这个最长匹配
    // 就是得到重复部分的长度
    // 构建PMT
    public int[] buildPMT(String sub) {
        final int N = sub == null ? 0 : sub.length();

        int[] PMT = new int[N];

        int i = 1;
        int j = 0;

        PMT[0] = 0;

        while (i < N) {
            if (sub.charAt(i) == sub.charAt(j)) {
                // 当相等的时候，
                i++;
                j++;
                PMT[i-1] = j;
            } else {
                if (0 == j) {
                    // 如果匹配失败，并且j已经为0
                    // 那么
                    i++;
                    PMT[i-1] = 0;
                } else {
                    j = PMT[j-1];
                }
            }
        }

        return PMT;
    }
}
