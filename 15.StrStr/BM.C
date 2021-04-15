
void build_bad_char_pos(char *t, int bad[]) {
    int i = 0;
    memset(bad, -1, sizeof(int) * 256);
    while (t[i]) {
        bad[t[i]] = i;
        i++;
    }
}

void build_suffix_prefix(char *t, int n, int suffix[], char prefix[]) {
    int i = 0, j = 0, len = 0;
    memset(prefix, 0, sizeof(char) * n);
    memset(suffix, -1, sizeof(int) * n);
    for (i = 0; i < n - 1; i++) {
        j = i;
        len = 0;
        // 头部字符串是t[0.... j]
        // 尾部字符串是t[xxx..n-1]
        // 在比较的时候，就是从后面往前进行比较
        while (j >= 0 && t[j] == t[n - 1 - len]) {
            ++len;
            suffix[len] = j;
            --j;
        }

        // 这里表示能够找到和前比完全一样的子串
        // 注意这个长度
        if (-1 == j) { prefix[len] = 1; }
    }
}

// 这里需要注意，j的位置是一个从后往前匹配失败的位置。
int move_by_suffix_prefix(int j, int n, int suffix[], char prefix[]) {
    // 这里匹配成功的长度是[j+1 ... n)
    int len = n - 1 - j, r;
    if (suffix[len] != -1) { return j - suffix[len] + 1; }

    // 这里也可以从r = j + 1开始。但是如果j+1是有效的。那么
    // 前面的suffix[len] != -1就会处理掉。
    // 所以这里没有必要再看j + 1
    // 直接找到一个可以匹配的后缀子串与前缀子串匹配的位置就可以了。
    for (r = j + 2; r <= n - 1; r++) {
        if (prefix[n - r]) { return r; }
    }
    return n;
}

int strStr(char *s, char *t) {
    if (!t || !*t) { return 0; }

    if (!s || !*s) { return -1; }

    int bad[256];
    build_bad_char_pos(t, bad);

    const int tlen = strlen(t);
    char prefix[tlen + 1];
    int suffix[tlen + 1];

    build_suffix_prefix(t, tlen, suffix, prefix);

    const int slen = strlen(s);
    int i = 0, j = 0;
    while (i <= slen - tlen) {
        for (j = tlen - 1; j >= 0; j--) {
            if (s[i + j] != t[j]) { break; }
        }
        if (j < 0) { return i; }
        int x = j - bad[s[i + j]];
        int y = 0;
        // 这里在t的右边最后一个字符就匹配失败的时候，是不能使用好后缀匹配的。
        // 可以认为此时没有任何好后缀!
        // 否则在匹配mississippi, issi的时候，会移动4步。得不到正确的结果
        if (j < tlen - 1) {
            y = move_by_suffix_prefix(j, tlen, suffix, prefix);
        }
        i += x > y ? x : y;
    }
    return -1;
}
