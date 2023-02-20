# 별자리만들기_BOJ4386

# input.txt 열기
import sys
sys.stdin = open('input.txt')

# input 받기
N = int(sys.stdin.readline().strip())                                                               # 별의 개수
star = []                                                                                           # 별을 저장할 리스트 생성
for _ in range(N):                                                                                  # 별의 개수만큼 반복해서
    star.append(list(map(float, sys.stdin.readline().split())))                                     # star리스트에 별의 좌표를 리스트형태로 append

starline = [[0]*N for _ in range(N)]                                                                # 하나의 별에서 다른 별을 이었을 때 거리를 저장할 행렬 생성

for i in range(N):                                                                                  # star 리스트를 반복하여 하나의 별을 선택하고
    for j in range(N):                                                                              # star 리스트를 반복하여 다른 하나의 별을 선택
        if i == j:                                                                                  # 두 별이 같은 별이면
            pass                                                                                    # pass
        else:                                                                                       # 다른 별이라면
            starline[i][j] = ((star[j][0] - star[i][0])**2 + (star[j][1] - star[i][1])**2)**(1/2)   # 두 별사이의 거리를 구해 i별에서 j별까지의 길이를 starline[i][j]에 저장

visited = [0] * N                                                                                   # 방문표시할 리스트의 원소를 별의 개수만큼 생성하고
visited[0] = 1                                                                                      # 0번째 인덱스의 별을 시작점으로 표시한다

ans = 0                                                                                             # 모든 별이 직 간접적으로 이어졌을 때 최소값을 저장하기 위한 변수 생성
while sum(visited) < N:                                                                             # 모든 별을 방문할 때까지 반복해서
    minD = 1000                                                                                     # 두 별사이의 최소거리를 저장할 변수생성
    minStar = -1                                                                                    # 최소거리가 되는 방문하지 않은 별의 인덱스를 저장할 변수생성
    for v in range(N):                                                                              # 별들을 반복해서
        if visited[v] == 1:                                                                         # 방문 표시된 별이라면
            for l in range(N):                                                                      # 인접한 별들을 반복해서
                if visited[l] == 0 and v != l:                                                      # 아직 방문전인 별이면서 v와 같은 별이 아닐 때
                    if starline[v][l] < minD:                                                       # v별과 l별의 거리가 minD보다 짧다면
                        minD = starline[v][l]                                                       # minD를 v별과 l별의 거리로 바꾸고
                        minStar = l                                                                 # minStar를 l 별로 저장한다
    else:                                                                                           # 모든 방문표시된 별의 탐색을 마쳤다면
        ans += minD                                                                                 # ans에 minStar과의 최소거리를 더하고
        visited[minStar] = 1                                                                        # visited에 minStar를 방문표시한다

print(ans)                                                                                          # 모든 별이 직 간접적으로 이어졌을 때 최소값을 출력한다
