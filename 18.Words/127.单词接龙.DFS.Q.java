/*
 * @lc app=leetcode.cn id=127 lang=java
 *
 * [127] 单词接龙
 *
 * https://leetcode-cn.com/problems/word-ladder/description/
 *
 * algorithms
 * Hard (46.22%)
 * Likes:    743
 * Dislikes: 0
 * Total Accepted:    105.3K
 * Total Submissions: 227.7K
 * Testcase Example:  '"hit"\n"cog"\n["hot","dot","dog","lot","log","cog"]'
 *
 * 字典 wordList 中从单词 beginWord 和 endWord 的 转换序列
 * 是一个按下述规格形成的序列：
 *
 *
 * 序列中第一个单词是 beginWord 。
 * 序列中最后一个单词是 endWord 。
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典 wordList 中的单词。
 *
 *
 * 给你两个单词 beginWord 和 endWord 和一个字典 wordList ，找到从 beginWord
 * 到 endWord 的 最短转换序列 中的 单词数目 。如果不存在这样的转换序列，返回 0。
 *
 *
 * 示例 1：
 *
 *
 * 输入：beginWord = "hit", endWord = "cog", wordList =
 * ["hot","dot","dog","lot","log","cog"]
 * 输出：5
 * 解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * 返回它的长度 5。
 *
 *
 * 示例 2：
 *
 *
 * 输入：beginWord = "hit", endWord = "cog", wordList =
 * ["hot","dot","dog","lot","log"]
 * 输出：0
 * 解释：endWord "cog" 不在字典中，所以无法进行转换。
 *
 *
 *
 * 提示：
 *
 *
 * 1
 * endWord.length == beginWord.length
 * 1
 * wordList[i].length == beginWord.length
 * beginWord、endWord 和 wordList[i] 由小写英文字母组成
 * beginWord != endWord
 * wordList 中的所有字符串 互不相同
 *
 *
 */

import java.util.*;

// @lc code=start
class Solution {

  Map<String, Integer> wordID = null;
  List<Integer> Graph[] = null;

  boolean buildGraph(String beginWord,
                     String endWord,
                     List<String> wordList) {
    // 首先如果单词一样
    if (beginWord.compareTo(endWord) == 0) {
      return false;
    }

    // 需要记录每个单词的ID
    wordID = new HashMap<>();
    int id = 0;
    for (String word : wordList) {
      if (!wordID.containsKey(word)) {
        wordID.put(word, id++);
      }
    }

    // 根据题意：如果我们在wordList中找不到endWord必须要
    // 返回0
    if (!wordID.containsKey(endWord)) {
      return false;
    }

    // 如果wordID中没有beginWord
    // 那么把beginWord添加到wordID & wordList中
    if (!wordID.containsKey(beginWord)) {
      wordID.put(beginWord, id++);
      wordList.add(beginWord);
    }

    // 构建图
    Graph = new ArrayList[wordID.size()];
    for (int i = 0; i < wordID.size(); i++) {
      Graph[i] = new ArrayList<>();
    }

    for (String word : wordList) {
      // 边的起始点 from
      final int from = wordID.get(word);

      // 看一下from能转变成什么
      byte[] wordBytes = word.getBytes();
      for (int i = 0; i < wordBytes.length; i++) {
        byte old = wordBytes[i];

        // 改变成其他byte
        for (byte toByte = 'a'; toByte <= 'z'; toByte++) {
          wordBytes[i] = toByte;

          String toWord = new String(wordBytes);
          if (wordID.containsKey(toWord)) {
            // 边的终点to
            int to = wordID.get(toWord);
            // 把这条边加到Graph中
            Graph[from].add(to);
          }
        }

        wordBytes[i] = old;
      }
    }

    return true;
  }

  private int[] dist = null;
  private Queue<Integer> Q = new PriorityQueue<>((v1, v2) -> dist[v1] - dist[v2]);

  private void dfs()
  {
    if (Q.isEmpty()) {
      return;
    }

    final int startNode = Q.poll();

    for (int nextNode : Graph[startNode]) {
      final int nextDist = dist[startNode] + 1;
      if (nextDist < dist[nextNode]) {
        dist[nextNode] = nextDist;
        Q.add(nextNode);
      }
    }

    dfs();
  }

  public int ladderLength(String beginWord,
                          String endWord,
                          List<String> wordList) {
    if (!buildGraph(beginWord, endWord, wordList)) {
      return 0;
    }
    final int src = wordID.get(beginWord);
    final int dst = wordID.get(endWord);

    dist = new int[wordID.size()];
    int maxPathLength = wordID.size() + 1024;

    for (int i = 0; i < dist.length; i++) {
      dist[i] = maxPathLength;
    }
    dist[src] = 0;

    Q.add(src);

    dfs();

    return dist[dst] >= maxPathLength ? 0 : dist[dst] + 1;
  }
}
// @lc code=end

public class Main {
  public static void main(String[] args) {
      Solution s = new Solution();
      String beginWord = new String("hit");
      String endWord = new String("cog");
      List<String> wordList = new ArrayList<>();
      String ar[] = new String[]{"hot","dot","dog","lot","log","cog"};
      for (int i = 0; i < ar.length; i++) {
          wordList.add(ar[i]);
      }

      System.out.println(s.ladderLength(beginWord, endWord, wordList));
  }
}

