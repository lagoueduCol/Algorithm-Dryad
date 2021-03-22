//测试平台 http://poj.org/problem?id=1287

#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define N 500

typedef struct _edge
{
  int in;
  int out;
  int weight;
} edge;

edge ES[N];

int F[N];

int
find(int x)
{
  return x != F[x] ? F[x] = find(F[x]) : F[x];
}

int
join(int x, int y)
{
  int px = find(x);
  int py = find(y);
  if (px == py)
    return 0;
  F[px] = py;
  return 1;
}

int
cmp(const void* a, const void* b)
{
  const edge* x = (const edge*)a;
  const edge* y = (const edge*)b;
  if (x->weight < y->weight)
    return -1;
  if (x->weight == y->weight)
    return 0;
  return 1;
}

int
main(void)
{
  int P;
  int R;
  int i = 0;
  int ans = 0;

  while (scanf("%d", &P) != EOF && P > 0) {
    scanf("%d", &R);

    if (R == 0) {
      printf("0\n");
      continue;
    }

    for (i = 0; i < R; i++) {
      scanf("%d%d%d", &ES[i].in, &ES[i].out, &ES[i].weight);
      F[ES[i].in] = ES[i].in;
      F[ES[i].out] = ES[i].out;
    }

    qsort(ES, R, sizeof(*ES), cmp);

    ans = 0;
    for (i = 0; i < R; i++) {
      if (join(ES[i].in, ES[i].out)) {
        ans += ES[i].weight;
      }
    }

    printf("%d\n", ans);
  }
  return 0;
}