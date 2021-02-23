
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
    int leftBraceNumber = 0;
    for (size_t i = 0; i < s.length(); i++) {
      // 取出字符
      char c = s[i];
      if (c == '(') {
        // 如果是'('，那么压栈
        leftBraceNumber++;
      } else if (c == ')') {
        // 如果是')'，那么就尝试弹栈
        if (leftBraceNumber <= 0) {
          // 如果弹栈失败，那么返回false
          return false;
        }
        --leftBraceNumber;
      }
    }

    return leftBraceNumber == 0;
  }
};

static Solution solution;

static void testEmptyString() { assert(solution.isValid("")); }

static void testSingleChar() {
  assert(!solution.isValid("("));
  assert(!solution.isValid(")"));
}

static void testTwoChars() {
  assert(solution.isValid("()"));
  assert(!solution.isValid("(("));
  assert(!solution.isValid("))"));
  assert(!solution.isValid(")("));
}

static void test3Chars() {
  assert(!solution.isValid("())"));
  assert(!solution.isValid("((("));
  assert(!solution.isValid(")))"));
  assert(!solution.isValid(")()"));
}

static void test4Chars() {
  assert(solution.isValid("()()"));
  assert(solution.isValid("(())"));
  assert(!solution.isValid("))(("));
}

static void testOther() {
  assert(solution.isValid("()()()"));
  assert(solution.isValid("((()))"));
  assert(solution.isValid("()(())"));
  assert(!solution.isValid("()(()("));
}

static void run() {
  testEmptyString();
  testSingleChar();
  testTwoChars();
  test3Chars();
  test4Chars();
  testOther();
  printf("test over\n");
}

int main(void) {
  run();
  return 0;
}
