# 빙산_BOJ2573

# input.txt 열기
import sys
sys.stdin = open('input.txt')
from collections import deque

# input 받기
N, M = map(int, sys.stdin.readline().split())                               # N 행의길이 / M 열의길이
bingsan = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]  # 빙산의 지도를 리스트로 input 받는다

year = 0                                                                    # 시간을 세기 위한 변수를 생성하고
dx = [-1, 0, 1, 0]                                                          # 행의 델타탐색을 위한 리스트(상우하좌)
dy = [0, 1, 0, -1]                                                          # 열의 델타탐색을 위한 리스트(상우하좌)

while 1:                                                                    # break가 나올 때까지 반복해서
    year += 1                                                               # 햇수를 1 증가시키고
    for i in range(N):                                                      # 행을 반복하고
        for j in range(M):                                                  # 열을 반복해서
            if bingsan[i][j]:                                               # 바다가 아닌 빙산이 나오면
                for k in range(4):                                          # 4방향으로 델타 탐색을 한다
                    if 0 <= i + dx[k] < N and 0 <= j + dy[k] < M:           # 델타탐색을 한 위치가 지도 안이면서
                        if bingsan[i + dx[k]][j + dy[k]] == 0:              # 그 위치가 바다라면
                            bingsan[i][j] -= 1                              # 빙산의 높이를 1 감소시키고
                            if bingsan[i][j] == 0:                          # 만약 빙산이 사라져 바다가 되었다면
                                bingsan[i][j] = -1                          # -1로 표시해준 후(해당 위치는 올해 바다가 되었으므로 다른 빙하에 영향을 주지 않기 위해)
                                break                                       # 델타탐색을 종료한다
    seom = 0                                                                # 섬을 세기 위한 변수를 생성하고
    visited = [[0]*M for _ in range(N)]                                     # 방문표시를 하기 위한 빈 지도를 생성한다
    for i in range(N):                                                      # 행을 반복하고
        for j in range(M):                                                  # 열을 반복해서
            if bingsan[i][j] < 0:                                           # 올해 바다가 된 위치가 나오면
                bingsan[i][j] = 0                                           # 0으로 만들어 바다로 표시해주고
            if bingsan[i][j] > 0 and visited[i][j] == 0:                    # 빙산이 있는 위치이면서 방문 전이라면
                seom += 1                                                   # 섬에 개수를 1 더해주고
                tmp = deque([[i, j]])                                       # 섬의 위치를 리스트에 넣어 deque를 생성한 후
                visited[i][j] = 1                                           # 해당 위치를 방문 표시한다
                while tmp:                                                  # deque가 모두 빌때까지 BFS탐색하여
                    A = tmp.popleft()                                       # deque의 선입 된 원소를 pop해 A에 저장하고
                    for k in range(4):                                      # 4방향 델타탐색을 해서
                        if 0 <= A[0] + dx[k] < N and 0 <= A[1] + dy[k] < M: # 델타탐색을 한 위치가 지도 안이면서
                            if bingsan[A[0] + dx[k]][A[1] + dy[k]] > 0 and visited[A[0] + dx[k]][A[1] + dy[k]] == 0:    # 탐색한 위치가 빙산이 있는 위치이면서 방문 전이라면
                                tmp.append([A[0] + dx[k], A[1] + dy[k]])    # deque에 빙산의 위치를 append 해주고
                                visited[A[0] + dx[k]][A[1] + dy[k]] = 1     # 방문표시를 해준다
    else:                                                                   # 빙산을 모두 탐색해 섬의 개수를 구했다면
        if seom == 0:                                                       # 섬이 0개라면 빙산이 다 녹은 것이므로
            print(0)                                                        # 0을 출력하고
            quit()                                                          # 종료한다
        elif seom >= 2:                                                     # 섬이 2개 이상이라면
            print(year)                                                     # 걸린 햇수를 출력하고
            quit()                                                          # 종료한다
        else:                                                               # 섬이 1개라면
            pass                                                            # pass 한다