#
# @lc app=leetcode.cn id=839 lang=python
#
# [839] 相似字符串组
#
# https://leetcode-cn.com/problems/similar-string-groups/description/
#
# algorithms
# Hard (57.25%)
# Likes:    119
# Dislikes: 0
# Total Accepted:    17.3K
# Total Submissions: 30.2K
# Testcase Example:  '["tars","rats","arts","star"]'
#
# 如果交换字符串 X 中的两个不同位置的字母，使得它和字符串 Y 相等，那么称 X 和 Y
# 两个字符串相似。如果这两个字符串本身是相等的，那它们也是相似的。
# 
# 例如，"tars" 和 "rats" 是相似的 (交换 0 与 2 的位置)； "rats" 和 "arts" 也是相似的，但是 "star" 不与
# "tars"，"rats"，或 "arts" 相似。
# 
# 总之，它们通过相似性形成了两个关联组：{"tars", "rats", "arts"} 和 {"star"}。注意，"tars" 和 "arts"
# 是在同一组中，即使它们并不相似。形式上，对每个组而言，要确定一个单词在组中，只需要这个词和该组中至少一个单词相似。
# 
# 给你一个字符串列表 strs。列表中的每个字符串都是 strs 中其它所有字符串的一个字母异位词。请问 strs 中有多少个相似字符串组？
# 
# 
# 
# 示例 1：
# 
# 
# 输入：strs = ["tars","rats","arts","star"]
# 输出：2
# 
# 
# 示例 2：
# 
# 
# 输入：strs = ["omv","ovm"]
# 输出：1
# 
# 
# 
# 
# 提示：
# 
# 
# 1 
# 1 
# strs[i] 只包含小写字母。
# strs 中的所有单词都具有相同的长度，且是彼此的字母异位词。
# 
# 
# 
# 
# 备注：
# 
# 字母异位词（anagram），一种把某个字符串的字母的位置（顺序）加以改换所形成的新词。
# 
#

# @lc code=start

class UF(object):
    def __init__(self, N):
        self.F = [0] * N
        self.Cnt = [0] * N
        for i in range(N):
            self.F[i] = i
            self.Cnt[i] = 1

        self.count = N
    
    def Find(self, x):
        if x == self.F[x]:
            return x
        self.F[x] = self.Find(self.F[x])
        return self.F[x]

    def Union(self, x, y):
        xpar = self.Find(x)
        ypar = self.Find(y)
        if xpar != ypar:
            self.F[xpar] = ypar
            self.Cnt[ypar] += self.Cnt[xpar]
            self.count -= 1
    
    def Size(self, i):
        return self.Cnt[self.Find(i)]
    
    def Count(self):
        return self.count

class Solution(object):
    def numSimilarGroups(self, A):
        
        N = 0 if not A else len(A)

        uf = UF(N)

        # 找到边与边之间的联系
        G = {}
        for i in range(N):
            u = A[i]
            t = "".join((lambda x:(x.sort(),x)[1])(list(u)))
            if not G.get(t):
                G[t] = []
            
            G[t].append(i)
        
        def cnt(x,y):
            cnt = 0
            for i in range(len(x)):
                cnt += 0 if x[i] == y[i] else 1
                if cnt > 2:
                    break
            return cnt

        for _,v in G.items():
            n = len(v)
            for i in range(n):
                for j in range(i+1,n):
                    dc = cnt(A[i], A[j])
                    if dc == 0 or dc == 2:
                        uf.Union(i,j)
        
        return uf.count

# @lc code=end

