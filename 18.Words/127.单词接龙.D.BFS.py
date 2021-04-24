# -*- coding: utf-8 -*-
#
# @lc app=leetcode.cn id=127 lang=python
#
# [127] 单词接龙
#
# https://leetcode-cn.com/problems/word-ladder/description/
#
# algorithms
# Hard (46.22%)
# Likes:    743
# Dislikes: 0
# Total Accepted:    105.3K
# Total Submissions: 227.7K
# Testcase Example:  '"hit"\n"cog"\n["hot","dot","dog","lot","log","cog"]'
#
# 字典 wordList 中从单词 beginWord 和 endWord 的 转换序列 是一个按下述规格形成的序列：
# 
# 
# 序列中第一个单词是 beginWord 。
# 序列中最后一个单词是 endWord 。
# 每次转换只能改变一个字母。
# 转换过程中的中间单词必须是字典 wordList 中的单词。
# 
# 
# 给你两个单词 beginWord 和 endWord 和一个字典 wordList ，找到从 beginWord 到 endWord 的 最短转换序列
# 中的 单词数目 。如果不存在这样的转换序列，返回 0。
# 
# 
# 示例 1：
# 
# 
# 输入：beginWord = "hit", endWord = "cog", wordList =
# ["hot","dot","dog","lot","log","cog"]
# 输出：5
# 解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。
# 
# 
# 示例 2：
# 
# 
# 输入：beginWord = "hit", endWord = "cog", wordList =
# ["hot","dot","dog","lot","log"]
# 输出：0
# 解释：endWord "cog" 不在字典中，所以无法进行转换。
# 
# 
# 
# 提示：
# 
# 
# 1 
# endWord.length == beginWord.length
# 1 
# wordList[i].length == beginWord.length
# beginWord、endWord 和 wordList[i] 由小写英文字母组成
# beginWord != endWord
# wordList 中的所有字符串 互不相同
# 
# 
#

# @lc code=start
import string

class Solution(object):
    def ladderLength(self, beginWord, endWord, wordList):
        """
        :type beginWord: str
        :type endWord: str
        :type wordList: List[str]
        :rtype: int
        """

        # 如果两个字符串相等
        if beginWord == endWord:
            return 0
        
        # 如果endWord没有在wordList里面
        if not (endWord in wordList):
            return 0

        # 开始建立word -> ID的映射
        ID = 0
        wordID = {}
        for word in wordList:
            wordID[word] = ID
            ID += 1

        # 如果begin word没有在里面
        # 那么也是要设上ID
        if wordID.get(beginWord, -1) == -1:
            wordID[beginWord] = ID
            wordList.append(beginWord)

        # 开始建图
        G = []
        for x in range(len(wordID)):
            tmp = []
            G.append(tmp)
        

        # 对于每一个单词，找到可能的边
        for word in wordList:
            startID = wordID[word]

            # 遍历word中的每个字符
            wordArray = list(word)
            for i in range(len(wordArray)):
                old = wordArray[i]

                for toChar in string.ascii_lowercase:
                    if toChar == old:
                        continue
                    
                    wordArray[i] = toChar

                    toWord = ''.join(wordArray)

                    endID = wordID.get(toWord, -1)
                    if endID > -1:
                        G[startID].append(endID)

                wordArray[i] = old

        # 开始执行双向BFS
        # 这里采用的是两段击的方式

        srcNode = wordID[beginWord]
        dstNode = wordID[endWord]

        src = set()
        src.add(srcNode)

        dst = set()
        dst.add(dstNode)
    
        vis = [0] * len(wordID)

        srcVisTag = 1
        dstVisTag = 2

        vis[srcNode] = srcVisTag
        vis[dstNode] = dstVisTag

        step = 0

        while len(src) > 0 and len(dst) > 0:
            step += 1

            for d in dst:
                if d in src:
                    return step
                
            
            tmp = src if len(src) < len(dst) else dst
            next = set()
            visTag = srcVisTag if len(src) < len(dst) else dstVisTag

            for startNode in tmp:
                for nextNode in G[startNode]:
                    if visTag != vis[nextNode]:
                        vis[nextNode] = visTag
                        next.add(nextNode)
            
            if len(src) < len(dst):
                src = next
            else:
                dst = next
        
        return 0
# @lc code=end
