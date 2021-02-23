import java.util.*;

class Solution {
    public boolean isValid(String s) {
        // 当字符串本来就是空的时候，我们可以快速返回true
        if (s == null || s.length() == 0) {
            return true;
        }
        // 当字符串长度为奇数的时候，不可能是一个有效的合法字符串
        if (s.length() % 2 == 1) {
            return false;
        }

        // 消除法的主要核心逻辑:
        int leftBraceNumber = 0;
        for (int i = 0; i < s.length(); i++) {
            // 取出字符
            char c = s.charAt(i);
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
}

class UnitTest {
    private static Solution solution = new Solution();

    public static void testEmptyString() {
        assert solution.isValid(null);
        assert solution.isValid("");
    }

    public static void testSingleChar() {
        assert !solution.isValid("(");
        assert !solution.isValid(")");
    }

    public static void testTwoChars() {
        assert solution.isValid("()");
        assert !solution.isValid("((");
        assert !solution.isValid("))");
        assert !solution.isValid(")(");
    }

    public static void test3Chars() {
        assert !solution.isValid("())");
        assert !solution.isValid("(((");
        assert !solution.isValid(")))");
        assert !solution.isValid(")()");
    }

    public static void test4Chars() {
        assert solution.isValid("()()");
        assert solution.isValid("(())");
        assert !solution.isValid("))((");
    }

    public static void testOther() {
        assert solution.isValid("()()()");
        assert solution.isValid("((()))");
        assert solution.isValid("()(())");
        assert !solution.isValid("()(()(");
    }

    public static void run() {
        testEmptyString();
        testSingleChar();
        testTwoChars();
        test3Chars();
        test4Chars();
        testOther();
        System.out.println("test over");
    }
}

public class Example01_D {
    public static void main(String[] argv) {
        UnitTest.run();
    }
}
