# 녹색옷입은애가젤다지?_BOJ4485

# input.txt 열기
import sys
sys.stdin = open('input.txt')
from collections import deque

def BFS():
    while Q:                                                                        # Q가 빌때까지 반복해서
        A = Q.popleft()                                                             # A에 Q의 가장 앞쪽 원소를 pop하여 저장한다
        for i in range(4):                                                          # 상우하좌 델타탐색을 해서
            nx = A[0] + dx[i]                                                       # 델타탐색으로 이동한 행의 위치를 nx에 저장하고
            ny = A[1] + dy[i]                                                       # 델타탐색으로 이동한 열의 위츠를 ny에 저장한다
            if 0 <= nx < N and 0 <= ny < N:                                         # 델타탐색으로 이동한 위치가 행렬 내에 있다면
                if rupee[nx][ny] > rupee[A[0]][A[1]] + jido[nx][ny]:                # 델타탐색으로 이동한 위치에 저장되어 있는 최소 도둑루피가 A위치에서 최소 도둑루피와 델타탐색으로 이동한 위치의 도둑루피의 합보다 크다면 해당 지점까지 새로운 최소값이 나왔으므로
                    rupee[nx][ny] = rupee[A[0]][A[1]] + jido[nx][ny]                # 델타탐색으로 이동한 위치의 최소루피를 A위치에서 최소 도둑루피와 델타탐색으로 이동한 위치의 도둑루피의 합으로 저장한다
                    Q.append([nx, ny])                                              # 최소의 길을 찾았으므로 Q에 델타탐색으로 이동한 위치를 append하여 최소 길을 찾아 나간다

# input 받기
dx = [-1, 0, 1, 0]                                                                  # 델타 탐색을 위한 행의 이동 (상우하좌)
dy = [0, 1, 0, -1]                                                                  # 델타 탐색을 위한 열의 이동 (상우하좌)
cnt = 0                                                                             # 테스트 케이스를 번호를 나타낼 변수 생성

while 1:                                                                            # break가 나올 때까지 반복해서
    N = int(sys.stdin.readline().strip())                                           # N에 행렬의 길이를 input 받는다
    if N:                                                                           # N이 0이 아니라면
        cnt += 1                                                                    # 테스트 케이스의 번호를 1 올리고
        jido = [list(map(int,sys.stdin.readline().split())) for _ in range(N)]      # jido에 도둑루피를 행렬 형태로 input 받고
        rupee = [[125*125*9]*N for _ in range(N)]                                   # 행렬의 해당위치까지 가장 적게 뺏기는 도둑루피를 저장하기 위한 리스트를 생성한다
        Q = deque([[0, 0]])                                                         # deque에 zelda의 출발지점을 넣어 생성하고
        rupee[0][0] = jido[0][0]                                                    # 출발지점의 최소값을 저장하고
        BFS()                                                                       # BFS로 탐색하여 각 지점마다 최소값을 구한다
        print(f'Problem {cnt}: {rupee[N-1][N-1]}')                                  # 문제 번호와 도착지점의 최소 도둑루피를 출력한다
    else:                                                                           # N이 0이면
        break                                                                       # while문을 break