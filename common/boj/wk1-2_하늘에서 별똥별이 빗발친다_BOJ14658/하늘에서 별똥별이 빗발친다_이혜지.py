# https://www.acmicpc.net/problem/14658
import sys

input = sys.stdin.readline
N, M, L, K = map(int, input().split())

stars = [list(map(int, input().split())) for _ in range(K)]

res = -1


def cal(r, c):
    star_cnt = 0
    for star in stars:
        if r <= star[0] <= r + L and c <= star[1] <= c + L:
            star_cnt += 1
    return star_cnt


for s in stars:
    for _s in stars:
        res = max(res, cal(s[0], _s[1]))

print(K - res)
