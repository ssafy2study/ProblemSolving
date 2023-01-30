# 컨베이어벨트위의로봇_BOJ20055

# input.txt 열기
import sys
sys.stdin = open('input.txt')

# input 받기
N, K = map(int, sys.stdin.readline().split())                   # N 컨베이어 벨트의 길이 / K 내구도
Durability = list(map(int, sys.stdin.readline().split()))       # 각 칸의 내구도를 list로 input 받음
robot = [0]*(2*N)                                               # 컨베이어 벨트 위에 있는 로봇의 위치를 나타내기 위한 리스트 생성

cnt = 0                                                         # 단계를 측정하기 위한 변수 생성
start = 0                                                       # 컨베이어벨트의 시작점을 나타내기 위한 변수 생성
end = N-1                                                       # 컨베이어벨트의 종착점을 나타내기 위한 변수 생성

while 1:                                                        # break가 나올 때까지 반복해서
    # 진행 단계 세기
    cnt += 1                                                    # 단계를 1 증가시킨다
    # 1. 컨베이어 벨트 이동하기
    if start == 0:                                              # 시작점이 0이라면 한칸 이동 후에 (2*N) - 1이 시작점이 되므로
        start = (2*N) - 1                                       # 시작점을 (2*N) - 1로 바꿔주고
    else:                                                       # 시작점이 0이 아니라면
        start -= 1                                              # 시작점에서 1을 빼준다
    if end == 0:                                                # 종착점이 0이라면 한칸 이동 후에 (2*N) - 1이 종착점이 되므로
        end = (2*N) - 1                                         # 종착점을 (2*N) - 1로 바꿔주고
    else:                                                       # 종착점이 0이 아니라면
        end -= 1                                                # 종착점에서 1을 빼준다
    # 로봇 내리기
    if robot[end] == 1:                                         # 로봇이 종착점에 도착했다면
        robot[end] = 0                                          # 로봇을 내려준다
    # 2. 로봇 움직이기
    if start < end:                                             # 종착점의 인덱스가 시작점보다 크다면
        for i in range(end-1, start-1, -1):                     # 종착점부터 시작점까지 역순으로 반복해서
            if robot[i] == 1:                                   # 로봇이 올려져 있는 칸이 나오면
                if Durability[i+1] > 0 and robot[i+1] == 0:     # 다음칸이 내구도가 남아있고 로봇이 없는지 확인한 후
                    robot[i] = 0                                # 해당 칸에 로봇을 없애고
                    robot[i+1] = 1                              # 다음칸으로 이동 시킨 후
                    Durability[i+1] -= 1                        # 다음칸의 내구도를 1감소 시킨다
    else:                                                       # 종착점의 인덱스가 시작점보다 작다면
        for i in range(end-1, -1, -1):                          # 종착점부터 0까지 역순으로 반복해서
            if robot[i] == 1:                                   # 로봇이 올려져 있는 칸이 나오면
                if Durability[i+1] > 0 and robot[i+1] == 0:     # 다음칸이 내구도가 남아있고 로봇이 없는지 확인한 후
                    robot[i] = 0                                # 해당 칸에 로봇을 없애고
                    robot[i+1] = 1                              # 다음칸으로 이동 시킨 후
                    Durability[i+1] -= 1                        # 다음칸의 내구도를 1감소 시킨다
        for i in range((2*N)-1, start-1, -1):                   # 마지막칸부터 시작점까지 역순으로 반복해서
            if robot[i] == 1:                                   # 로봇이 올려져 있는 칸이 나오면
                if i == (2*N) - 1:                              # 인덱스가 마지막 칸인 경우에는
                    if Durability[0] > 0 and robot[0] == 0:     # 0번째 칸이 내구도가 남아있고 로봇이 없는 지 확인한 후
                        robot[i] = 0                            # 해당 칸에 로봇을 없애고
                        robot[0] = 1                            # 0번째 칸으로 이동 시킨 후
                        Durability[0] -= 1                      # 0번째 칸의 내구도를 1감소 시킨다
                elif Durability[i+1] > 0 and robot[i+1] == 0:   # 마지막칸이 아닌 경우에는 다음칸이 내구도가 남아있고 로봇이 없는지 확인한 후
                    robot[i] = 0                                # 해당 칸에 로봇을 없애고
                    robot[i + 1] = 1                            # 다음칸으로 이동 시킨 후
                    Durability[i + 1] -= 1                      # 다음칸의 내구도를 1감소 시킨다
    # 로봇 내리기
    if robot[end] == 1:                                         # 로봇이 종착점에 도착했다면
        robot[end] = 0                                          # 로봇을 내려준다
    # 3. 로봇 올리기
    if Durability[start] > 0:                                   # 시작점의 내구도가 남아있다면
        robot[start] = 1                                        # 로봇을 올려주고
        Durability[start] -= 1                                  # 시작점의 내구도를 1 감소시킨다
    # 4. 내구도 측정하기
    stop = 0                                                    # 내구도가 0인 벨트를 셀 변수 생성
    for d in Durability:                                        # 벨트의 내구도를 반복해서
        if d == 0:                                              # 내구도가 0인 칸이 나오면
            stop += 1                                           # stop을 1 증가시킨다
    else:                                                       # 모든 벨트를 탐색한 후
        if stop >= K:                                           # 만약 내구도가 0인 벨트가 K개 이상이라면
            print(cnt)                                          # 현재 단계를 출력하고
            break                                               # while문을 break한다