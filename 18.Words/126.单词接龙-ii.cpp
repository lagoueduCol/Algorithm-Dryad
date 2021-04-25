/*
 * @lc app=leetcode.cn id=126 lang=cpp
 *
 * [126] 单词接龙 II
 *
 * https://leetcode-cn.com/problems/word-ladder-ii/description/
 *
 * algorithms
 * Hard (38.47%)
 * Likes:    427
 * Dislikes: 0
 * Total Accepted:    31.1K
 * Total Submissions: 80.9K
 * Testcase Example:  '"hit"\n"cog"\n["hot","dot","dog","lot","log","cog"]'
 *
 * 按字典 wordList 完成从单词 beginWord 到单词 endWord 转化，一个表示此过程的 转换序列 是形式上像 beginWord ->
 * s1 -> s2 -> ... -> sk 这样的单词序列，并满足：
 * 
 * 
 * 
 * 
 * 每对相邻的单词之间仅有单个字母不同。
 * 转换过程中的每个单词 si（1 ）必须是字典 wordList 中的单词。注意，beginWord 不必是字典 wordList 中的单词。
 * sk == endWord
 * 
 * 
 * 给你两个单词 beginWord 和 endWord ，以及一个字典 wordList 。请你找出并返回所有从 beginWord 到 endWord
 * 的 最短转换序列 ，如果不存在这样的转换序列，返回一个空列表。每个序列都应该以单词列表 [beginWord, s1, s2, ..., sk]
 * 的形式返回。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：beginWord = "hit", endWord = "cog", wordList =
 * ["hot","dot","dog","lot","log","cog"]
 * 输出：[["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
 * 解释：存在 2 种最短的转换序列：
 * "hit" -> "hot" -> "dot" -> "dog" -> "cog"
 * "hit" -> "hot" -> "lot" -> "log" -> "cog"
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：beginWord = "hit", endWord = "cog", wordList =
 * ["hot","dot","dog","lot","log"]
 * 输出：[]
 * 解释：endWord "cog" 不在字典 wordList 中，所以不存在符合要求的转换序列。
 * 
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
 * wordList 中的所有单词 互不相同
 * 
 * 
 * 
 * 
 */

// @lc code=start
class Solution {
public:
    vector<vector<string>> findLadders(string beginWord, string endWord, vector<string>& wordList) {
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
        int step = 0;

        vector<int> cur{src}, next;

        bool vis[wordID.size()];
        memset(vis, 0, sizeof(vis));
        vis[src] = true;

        // 单向BFS构建可行性地图
        // 再DFS反向寻找结果

        // Solution 2: 
        // Dijstra 找到到每个点的最短距离
        // 然后再用DFS或者BFS反向找
    }
};
// @lc code=end

