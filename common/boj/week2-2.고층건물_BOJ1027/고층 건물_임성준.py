import sys
# 백준 1027번, 고층 건물

# 빌딩의 수 N
N = int(input())
# 빌딩의 높이 input
buildings = list(map(int,input().split()))
# 빌딩 간의 기울기 계산
gradient = [[0] * N for _ in range(N)]

# 각 건물 사이의 기울기 구하기
# gradient[i][j]: i 건물과 j 건물 사이의 기울기
for i in range(N) :
    for j in range(N) :
        if i == j : 
          continue
        gradient[i][j] = (buildings[i] - buildings[j]) / (i-j)

# 각 건물에서 볼 수 있는 건물의 갯수 구하기
# A 건물에서 볼 수 있는 건물의 갯수를 max_count로 계산
max_cnt = [0] * N
for i in range(N) :
    cnt = 0
    # 왼쪽에서 볼 수 있는 건물 갯수 구하기
    for l in range(i) :
        can_see = True
        for k in range(l+1,i) :
            if gradient[k][i] <= gradient[l][i] :
                can_see = False
        if can_see :
          cnt += 1
    # 오른쪽에서 볼 수 있는 건물 갯수 구하기
    for r in range(i+1,N) :
        can_see = True
        for k in range(i+1,r):
            if gradient[i][k] >= gradient[i][r] :
                can_see = False
        if can_see: 
          cnt += 1
    max_cnt[i] = cnt
print(max(max_cnt))