# 백준 1987번, 알파벳
import sys
input = sys.stdin.readline

# 세로 R칸, 가로 C칸으로 된 표 모양의 보드
# 보드의 각 칸에는 대문자 알파벳이 하나씩 적혀 있고, 좌측 상단 칸 (1행 1열) 에는 말
# 말은 상하좌우로 인접한 네 칸 중의 한 칸으로 이동 가능
# 같은 알파벳이 적힌 칸을 두 번 지날 수 없다.

# 이동 방향
dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]

# 알파벳 한번만 방문하는 루트 찾는 함수 정의
def find_route(alpha_count, first_r, first_c) :
  global result
  global visited_alpha

  result = max(result, alpha_count)
  x, y = first_r, first_c
  # 4방향 탐색
  for dir in range(4) :
    nx, ny = x + dx[dir], y + dy[dir]
    if 0 <= nx < R and 0 <= ny < C and visited_alpha[board[nx][ny]] == 0 :
            visited_alpha[board[nx][ny]] = 1
            find_route(alpha_count + 1, nx, ny)
            visited_alpha[board[nx][ny]] = 0

# 첫째 줄에는 R, C
R, C = map(int,input().split())
# 보드 (문자열을 아스키 코드로 반환하는 ord 함수를 통해 알파벳을 전부 숫자로 바꿔준다)
# ord(A) = 65이므로, 람다함수를 통해 ord 적용 및 -65를 통해 알파벳을 숫자로 만들어준다. 
board = []
for _ in range(R) :
  temp = list(map(lambda x: ord(x) - 65, input().strip()))
  board.append(temp)
# 무조건 하나는 방문하니 result 1부터 시작
result = 1
visited_alpha = [0] * 26
# 좌측 상단 방문 처리
visited_alpha[board[0][0]] = 1
# 만든 함수 실행
find_route(1,0,0)
print(result)
