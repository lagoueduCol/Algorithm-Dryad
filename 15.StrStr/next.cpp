class Solution {
public:
    vector<int> buildNext(string &s) {
        const int N = s.length();
        vector<int> next(N+1);

        int i = 0;
        int j = -1;
        next[0] = -1;

        while (i < N) {
            if (-1 == j || s[i] == s[j]) {
                i++;
                j++;
                next[i] = j;
            } else {
                j = next[j];
            }
        }

        // 最后一项就是整个字符串的前后缀的最长匹配
        return next;
    }
};
