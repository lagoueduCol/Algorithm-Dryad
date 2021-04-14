
void make_next(char *t, int *next) {
    int i = 0;
    int j = -1;
    next[0] = -1;

    while (t[i]) {
        if (-1 == j || t[i] == t[j]) {
            ++i;
            ++j;
            // 注意这优化，在往后退的时候，如果发现和下一个退回
            // 去的地方的字符是相等的，那么再往后退一下。
            // 否则就退到下一个要比较的地方
            next[i] = t[i] == t[j] ? next[j] : j;
        } else {
            j = next[j];
        }
    }
}

int strStr(char * s, char * t){
    if (!t || !*t) {
        return 0;
    }

    if (!s) {
        return -1;
    }

    const int tlen = strlen(t);

    int next[tlen + 1];
    make_next(t, next);

    int i = 0, j = 0;
    while (s[i] && j < tlen) {
        if (-1 == j || s[i] == t[j]) {
            i++;
            j++;
        } else {
            j = next[j];
        }
    }

    return j == tlen ? i - j : -1;
}
