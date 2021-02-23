// https://www.acwing.com/problem/content/description/788/

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
#include <set>
#include <stack>
#include <string>
#include <unordered_map>
#include <unordered_set>
#include <vector>

using namespace std;


int N;
int K;
int A[100001];

int main(void) {
  while (scanf("%d%d", &N, &K) != EOF) {
    for (int i = 0; i < N; i++) {
      scanf("%d", A + i);
    }
    
    priority_queue<int> Q;
    for (int i = 0; i < N; i++) {
        Q.push(A[i]);
        while (Q.size() > K) {
            Q.pop();
        }
    }
    printf("%d\n", Q.top());
  }
  return 0;
}
