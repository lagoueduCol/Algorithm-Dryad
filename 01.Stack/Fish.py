# -*- coding: utf-8 -*-
# 测试链接： https://app.codility.com/programmers/lessons/7-stacks_and_queues/fish/start/
# you can write to stdout for debugging purposes, e.g.
# print("this is a debug message")

def solution(fishSize, fishDirection):
    # write your code in Python 3.6
    fishNumber = len(fishSize)
    if fishNumber <= 1:
        return fishNumber

    left = 0
    right = 1

    t = []

    for i in range(0, fishNumber):
      # 当前鱼的情况：1，游动的方向；2，大小
      curFishDirection = fishDirection[i]
      curFishSize = fishSize[i]

      # 当前的鱼是否被栈中的鱼吃掉了
      hasEat = False

      # 如果栈中还有鱼，并且栈中鱼向右，当前的鱼向左游，那么就会有相遇的可能性
      while len(t) > 0 and fishDirection[t[-1]] == right and curFishDirection == left:
        # 如果栈顶的鱼比较大，那么把新来的吃掉
        if fishSize[t[-1]] > curFishSize:
          hasEat = True
          break

        # 如果栈中的鱼较小，那么会把栈中的鱼吃掉，栈中的鱼被消除，所以需要弹栈。
        t.pop()

      # 如果新来的鱼，没有被吃掉，那么压入栈中。
      if not hasEat:
        t.append(i)

    return len(t)