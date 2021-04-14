int strStr(char * s, char * t){
    if (!t || !*t) {
        return 0;
    }

    if (!s || !*s) {
        return -1;
    }

    const int slen = strlen(s);
    const int tlen = strlen(t);

    if (slen < tlen) {
        return -1;
    }

    int pos[256];
    memset(pos, -1, sizeof(pos));

    for (int i = 0; i < tlen; i++) {
        pos[t[i]] = i;
    }

    for (int i = 0; i <= slen - tlen; i++) {
        int j = 0;
        while (t[j]) {
            if (s[i + j] != t[j]) {
                break;
            }
            j++;
        }

        if (j == tlen) {
            return i;
        }

        const int lastChar = s[i+tlen-1];
        if (lastChar != t[tlen-1]) {
            if (pos[lastChar] != -1) {
                i += tlen - 1 - pos[lastChar] - 1;
            } else {
                i = i + tlen - 1;
            }
        }
    }

    return -1;
}
