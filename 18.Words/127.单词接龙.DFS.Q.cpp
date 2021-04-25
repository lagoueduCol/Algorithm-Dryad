/*
 * @lc app=leetcode.cn id=127 lang=cpp
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
 * 字典 wordList 中从单词 beginWord 和 endWord 的 转换序列 是一个按下述规格形成的序列：
 * 
 * 
 * 序列中第一个单词是 beginWord 。
 * 序列中最后一个单词是 endWord 。
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典 wordList 中的单词。
 * 
 * 
 * 给你两个单词 beginWord 和 endWord 和一个字典 wordList ，找到从 beginWord 到 endWord 的 最短转换序列
 * 中的 单词数目 。如果不存在这样的转换序列，返回 0。
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：beginWord = "hit", endWord = "cog", wordList =
 * ["hot","dot","dog","lot","log","cog"]
 * 输出：5
 * 解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。
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


// @lc code=start
class Solution {
    void dfs(vector<vector<int>> &G, int start, vector<int> &dist) {
        for (int nextNode: G[start]) {
            const int nextDist = dist[start] + 1;
            if (nextDist < dist[nextNode]) {
                dist[nextNode] = nextDist;
                dfs(G, nextNode, dist);
            }
        }
    }
public:
    int ladderLength(string beginWord,
                     string endWord,
                     vector<string>& wordList) {

        // 如果两个单词完全一样
        if (beginWord == endWord) {
            return 0;
        }

        // 单词到ID的编号
        unordered_map<string, int> wordID;

        int i = 0;
        for (auto &word: wordList) {
            wordID[word] = i++;
        }

        if (!wordID.count(endWord)) {
            return 0;
        }

        if (!wordID.count(beginWord)) {
            wordID[beginWord] = i++;
            wordList.push_back(beginWord);
        }

        // 得到每个单词可以转换过去的列表
        const int N = wordID.size();

        // graph
        vector<vector<int>> G(N);

        // build graph
        for (auto &word: wordList) {
            const int from = wordID[word];

            for (auto &c: word) {
                // word改变字符，可以变成什么样的单词呢？
                auto old = c;

                for (char toByte = 'a'; toByte <= 'z'; toByte++) {
                    if (toByte == old) {
                        continue;
                    }

                    c = toByte;
                    auto iter = wordID.find(word);

                    if (iter != wordID.end()) {
                        const int to = iter->second;
                        if (to != from) {
                            G[from].push_back(to);
                        }
                    }
                }

                c = old;
            }
        }

        const int src = wordID[beginWord];
        const int dst = wordID[endWord];
        const int maxPathLength = wordID.size() + 1024;
        vector<int> dist(wordID.size() + 1, maxPathLength);
        dist[src] = 0;

        dfs(G, src, dist);

        return dist[dst] >= maxPathLength ? 0 : dist[dst] + 1;
    }
};
// @lc code=end

int main(void) {
    string beginWord = "hit";
    string endWord = "cog";
    vector<string> wordList{"hot","dot","dog","lot","log","cog"};

    Solution s;
    std::cout << s.ladderLength(beginWord, endWord, wordList) << std::endl;
    return 0;
}