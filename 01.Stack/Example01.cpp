
#include <assert.h>
#include <limits.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include <algorithm>
#include <iostream>
#include <numeric>
#include <queue>
#include <stack>
#include <string>
#include <unordered_map>
#include <unordered_set>
#include <vector>

using namespace std;

class Solution {
public:
  bool isValid(string s) {
    // 当字符串本来就是空的时候，我们可以快速返回true
    if (s.empty()) {
      return true;
    }
    // 当字符串长度为奇数的时候，不可能是一个有效的合法字符串
    if (s.length() % 2 == 1) {
      return false;
    }

    // 消除法的主要核心逻辑:
    stack<char> t;
    for (size_t i = 0; i < s.length(); i++) {
      // 取出字符
      char c = s[i];
      if (c == '(') {
        // 如果是'('，那么压栈
        t.push(c);
      } else if (c == ')') {
        // 如果是')'，那么就尝试弹栈
        if (t.empty()) {
          // 如果弹栈失败，那么返回false
          return false;
        }
        t.pop();
      }
    }

    return t.empty();
  }
};

// 下面开始测试

#define ASSERT_EQ(a, b) assert((a) == (b))
#define ASSERT_TRUE(a) assert(true == (a))

static Solution solution;

static void testEmptyString() { ASSERT_TRUE(solution.isValid("")); }

static void testSingleChar() {
  ASSERT_TRUE(!solution.isValid("("));
  ASSERT_TRUE(!solution.isValid(")"));
}

static void testTwoChars() {
  ASSERT_TRUE(solution.isValid("()"));
  ASSERT_TRUE(!solution.isValid("(("));
  ASSERT_TRUE(!solution.isValid("))"));
  ASSERT_TRUE(!solution.isValid(")("));
}

static void test3Chars() {
  ASSERT_TRUE(!solution.isValid("())"));
  ASSERT_TRUE(!solution.isValid("((("));
  ASSERT_TRUE(!solution.isValid(")))"));
  ASSERT_TRUE(!solution.isValid(")()"));
}

static void test4Chars() {
  ASSERT_TRUE(solution.isValid("()()"));
  ASSERT_TRUE(solution.isValid("(())"));
  ASSERT_TRUE(!solution.isValid("))(("));
}

static void testOther() {
  ASSERT_TRUE(solution.isValid("()()()"));
  ASSERT_TRUE(solution.isValid("((()))"));
  ASSERT_TRUE(solution.isValid("()(())"));
  ASSERT_TRUE(!solution.isValid("()(()("));
}

static void run() {
  testEmptyString();
  testSingleChar();
  testTwoChars();
  test3Chars();
  test4Chars();
  testOther();
  printf("Test Over!\n");
}

int main(void) {
  run();
  return 0;
}
