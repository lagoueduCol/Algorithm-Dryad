class Solution {
public:
    vector<int> buildPMT(string &s) {
        const int N = s.length();
        vector<int> pmt(N);

        int i = 1;
        int j = 0;

        pmt[0] = 0;

        while (i < N) {
            if (s[i] == s[j]) {
                i++;
                j++;
                pmt[i-1] = j;
            } else {
                if (0 == j) {
                    i++;
                    pmt[i-1] = 0;
                } else {
                    j = pmt[j-1];
                }
            }
        }

        return pmt;
    }
};
