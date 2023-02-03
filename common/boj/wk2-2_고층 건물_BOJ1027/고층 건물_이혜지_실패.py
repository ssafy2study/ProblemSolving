# https://www.acmicpc.net/problem/1027
from decimal import Decimal

N = int(input())
buildings = list(map(int, input().split()))

m_b = max(buildings)

m_bs_idx = [b for b in range(len(buildings)) if buildings[b] >= m_b]


def cal_giulgi(v1, v2):
    return Decimal(abs(buildings[v2] - buildings[v1]) / abs(v2 - v1))


def find_visible_buildings_left(idx):
    cnt = 0
    compare_giulgi = cal_giulgi(idx, idx - 1)
    for i in range(idx - 1, -1, -1):
        giulgi = cal_giulgi(idx, i)
        if giulgi <= compare_giulgi:
            cnt += 1

    return cnt


def find_visible_buildings_right(idx):
    cnt = 0
    compare_giulgi = cal_giulgi(idx, idx + 1)
    for i in range(idx + 1, N):
        giulgi = cal_giulgi(idx, i)
        if giulgi <= compare_giulgi:
            cnt += 1
    return cnt


def find_visible_buildings(idx):
    cnt = find_visible_buildings_left(idx) + find_visible_buildings_right(idx)
    return cnt


b_cnt = -1
for idx in m_bs_idx:
    b_cnt = max(find_visible_buildings(idx), b_cnt)

print(b_cnt)