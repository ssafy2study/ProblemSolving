# 알파벳_BOJ1987

# input.txt 열기
import sys
sys.stdin = open('input.txt')

def DFS(r, c):
    global ans                                                  # ans 변수를 불러와서
    if road.get(board[r][c]) == None:                           # 딕셔너리에 board[r][c]의 값이 key인 value가 없어 None으로 나오면
        road[board[r][c]] = 1                                   # 지나가지 않은 길이므로 딕셔너리에 board[r][c]를 key로 1을 value로 저장한다
        for i in range(4):                                      # 델타탐색을 위해 반복해서
            if 0 <= r+dx[i] < R and 0 <= c+dy[i] < C:           # board 내에 있는 위치라면
                DFS(r+dx[i], c+dy[i])                           # DFS 탐색을 한다
        else:                                                   # 4방향 모두 델타탐색을 마쳤다면
            del road[board[r][c]]                               # 딕셔너리에서 key가 board[r][c]인 값을 삭제하고
            return                                              # return한다
    else:                                                       # 만약 key가 board[r][c]인 값이 존재한다면 이미지나온 길이므로 탐색을 멈추고
        if ans < len(road):                                     # ans보다 현재 road의 개수가 더 많다면
            ans = len(road)                                     # ans를 road의 개수로 갱신한 후
        return                                                  # return한다

# input 받기
R, C = map(int, sys.stdin.readline().split())                   # R 행의 길이 / C 열의 길이
board = [list(sys.stdin.readline().strip()) for _ in range(R)]  # 알파벳이 적힌 보드를 input 받아 board에 저장
dx = [-1, 0, 1, 0]                                              # 행을 델타 탐색하기 위한 리스트 (상우하좌)
dy = [0, 1, 0, -1]                                              # 열을 델타 탐색하기 위한 리스트 (상우하좌)

road = dict()                                                   # road라는 딕셔너리를 생성하고
ans = 0                                                         # 정답을 저장할 변수를 생성한다
DFS(0, 0)                                                       # DFS를 0,0에서 시작해서

print(ans)                                                      # 가장 많이 지날 수 있는 칸의 수 출력한다