// you can also use imports, for example:
// 测试链接： https://app.codility.com/programmers/lessons/7-stacks_and_queues/fish/start/
import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {
  int solution(int[] fishSize, int[] fishDirection) {
    // 首先拿到鱼的数量
    // 如果鱼的数量小于等于1，那么直接返回鱼的数量
    final int fishNumber = fishSize.length;
    if (fishNumber <= 1) {
      return fishNumber;
    }

    // 0表示鱼向左游
    final int left = 0;
    // 1表示鱼向右游
    final int right = 1;

    Stack<Integer> t = new Stack();

    for (int i = 0; i < fishNumber; i++) {
      // 当前鱼的情况：1，游动的方向；2，大小
      final int curFishDirection = fishDirection[i];
      final int curFishSize = fishSize[i];

      // 当前的鱼是否被栈中的鱼吃掉了
      boolean hasEat = false;

      // 如果栈中还有鱼，并且栈中鱼向右，当前的鱼向左游，那么就会有相遇的可能性
      while (!t.empty() && fishDirection[t.peek()] == right &&
             curFishDirection == left) {
        // 如果栈顶的鱼比较大，那么把新来的吃掉
        if (fishSize[t.peek()] > curFishSize) {
          hasEat = true;
          break;
        }
        // 如果栈中的鱼较小，那么会把栈中的鱼吃掉，栈中的鱼被消除，所以需要弹栈。
        t.pop();
      }
      // 如果新来的鱼，没有被吃掉，那么压入栈中。
      if (!hasEat) {
        t.push(i);
      }
    }

    return t.size();
  }
}
