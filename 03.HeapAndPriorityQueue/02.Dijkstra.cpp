class Solution {
public:
    int networkDelayTime(vector<vector<int>>& es, int N, int K) {
        vector<vector<pair<int,int>>> G(N+1);
        for (auto &e: es) {
            const int from = e[0], to = e[1], cost = e[2];
            G[from].push_back({to, cost});
        }

        constexpr int INF = INT_MAX >> 2;

        vector<int> ans(N+1, INF);
        ans[K] = 0;

        auto cmp = [&](const int i, const int j) {
            return ans[i] > ans[j];
        };

        priority_queue<int, vector<int>, decltype(cmp)> Q(cmp);
        Q.push(K);

        while (!Q.empty()) {
            auto cur = Q.top();
            Q.pop();

            for (auto n: G[cur]) {
                const int dist = ans[cur] + n.second;
                if (dist < ans[n.first]) {
                    ans[n.first] = dist;
                    Q.push(n.first);
                }
            }
        }

        int max_value = *max_element(ans.begin() + 1, ans.end());
        return max_value >= INF ? -1 : max_value; 
    }
};
