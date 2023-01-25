import heapq

INF = int(1e9)

dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]


def dijkstra():
    q = []
    # 시작 가격, 시작 r, c
    heapq.heappush(q, (graph[0][0], 0, 0))
    distance[0][0] = 0

    while q:
        dist, r, c = heapq.heappop(q)

        if r == N - 1 and c == N - 1:
            print(f"Problem {count}: {distance[r][c]}")
            break

        if distance[r][c] > dist:
            continue

        for d in range(4):
            nr = r + dr[d]
            nc = c + dc[d]

            if nr < 0 or nr >= N or nc < 0 or nc >= N:
                continue

            new_dist = dist + graph[nr][nc]

            if new_dist < distance[nr][nc]:
                distance[nr][nc] = new_dist
                heapq.heappush(q, (new_dist, nr, nc))


count = 1

while True:
    N = int(input())
    if N == 0:
        break

    graph = [list(map(int, input().split())) for _ in range(N)]

    distance = [[INF] * N for _ in range(N)]

    dijkstra()
    count += 1
