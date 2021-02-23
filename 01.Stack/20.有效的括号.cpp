/*
 *
 * [20] 有效的括号
 */

class Solution {
   public:
    bool isValid(string s) {
        if (s.length() & 0x01) {
            return false;
        }
        stack<char> t;
        for (auto c : s) {
            if (c == '{' || c == '[' || c == '(') {
                t.push(c);
            } else if (c == ')') {
                if (t.empty() || t.top() != '(') {
                    return false;
                }
                t.pop();
            } else if (c == '}') {
                if (t.empty() || t.top() != '{') {
                    return false;
                }
                t.pop();
            } else if (c == ']') {
                if (t.empty() || t.top() != '[') {
                    return false;
                }
                t.pop();
            } else {
                return false;
            }
        }

        return t.empty();
    }
};
