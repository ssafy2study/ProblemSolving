# https://www.acmicpc.net/problem/1027
from decimal import Decimal

N = int(input())
buildings = list(map(int, input().split()))


# m_b = max(buildings)
#
# m_bs_idx = [b for b in range(len(buildings)) if buildings[b] >= m_b]


def cal_giulgi(i, idx):
    return (buildings[idx] - buildings[i]) / (idx - i)


def find_visible_buildings_left(idx):
    cnt = 0
    compare_giulgi = float("INF")
    for i in range(idx - 1, -1, -1):
        giulgi = cal_giulgi(i, idx)
        if giulgi < compare_giulgi:
            compare_giulgi = giulgi
            cnt += 1
    return cnt


def find_visible_buildings_right(idx):
    cnt = 0
    compare_giulgi = -float("INF")
    for i in range(idx + 1, N):
        giulgi = cal_giulgi(i, idx)
        if giulgi > compare_giulgi:
            compare_giulgi = giulgi
            cnt += 1
    return cnt


def find_visible_buildings(idx):
    return find_visible_buildings_left(idx) + find_visible_buildings_right(idx)


b_cnt = 0
for idx in range(N):
    b_cnt = max(find_visible_buildings(idx), b_cnt)

print(b_cnt)
