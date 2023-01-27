import sys
import heapq

# 이동 좌표 입력
dr = [0, 0, -1, 1]
dc = [-1, 1, 0, 0]
# 최소값을 구하므로 숫자를 제일 크게 지정
INF = sys.maxsize
# 몇 개의 테스트케이스인지는 알 수 없고, N과 N줄의 인풋이 끝났을 때 다른 테이스케이스 넘어가도록 1로 지정
test_count = 1
# 각 테스트 케이스마다 시작
while True:
    n = int(input())
    if n == 0:
        break
    # 동굴 맵 인풋
    cave = [list(map(int, input().split())) for _ in range(n)]
    # heap q를 만들어서 잃은 돈을 기준으로 정렬되도록 한다.
    q = []
    # 잃은 돈, 좌표
    heapq.heappush(q, (cave[0][0], 0, 0))
    
    # 각 칸을 갈 때, 최소의 금액을 잃고 가도록 계속 갱신해준다.
    ans = [[INF for _ in range(n)] for _ in range(n)]
    ans[0][0] = cave[0][0]
    result = 0
    
    while q:
        money, r, c = heapq.heappop(q)
        # 좌표가 도착지점일 경우, 최소값인 잃은 돈을 결과값으로 저장한다.
        if r == n-1 and c == n-1:
            result = money
        # 델타 탐색으로 4방향 전부 탐색
        for move in range(4):
            nr = r + dr[move]
            nc = c + dc[move]
            if 0 <= nr < n and 0 <= nc < n:
            	# 현재까지의 최소돈 + 다음 지점 돈 vs 현재까지 계산된 다음 지점의 잃은 돈 비교
                if money + cave[nc][nr] < ans[nr][nc]:
                    ans[nr][nc] = money + cave[nc][nr]
                    heapq.heappush(q, (money + cave[nr][nc], nr, nc))
    print(f'Problem {test_count}: {result}')
    test_count += 1