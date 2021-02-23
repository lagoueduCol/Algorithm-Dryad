// 64. 字符流中第一个只出现一次的字符
/*
请实现一个函数用来找出字符流中第一个只出现一次的字符。

例如，当从字符流中只读出前两个字符”go”时，第一个只出现一次的字符是’g’。

当从该字符流中读出前六个字符”google”时，第一个只出现一次的字符是’l’。

如果当前字符流没有存在出现一次的字符，返回#字符。

输入："google"

输出："ggg#ll"

解释：每当字符流读入一个字符，就进行一次判断并输出当前的第一个只出现一次的字符。

*/
// https://www.acwing.com/problem/content/60/

class Solution{
    int left = -1;
    int i = -1;
    int cnt[256] = {};
    string s;
public:
    //Insert one char from stringstream
    void insert(char ch){
        i++;
        s.push_back(ch);
        cnt[ch]++;
        while (left < i && cnt[s[1+left]] > 1) {
            ++left;
        }
    }
    //return the first appearence once char in current stringstream
    char firstAppearingOnce(){
        if (left >= i) {
            return '#';
        }
        return s[left+1];
    }
};
