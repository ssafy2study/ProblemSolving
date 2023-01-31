import sys

input = sys.stdin.readline
N, K = map(int, map(int, input().split()))
conveyor = list(map(int, input().split()))
robot = [0 for _ in range(2 * N)]
# 로봇
start = 0
end = N - 1
zero_d = 0
stage = 0


def rotate():
    global start, end
    start = (start - 1) % (2 * N)
    end = (end - 1) % (2 * N)

    robot[end] = 0


def move_robot():
    global zero_d
    f = end
    t = end + 1
    for i in range(N - 2):
        f -= 1
        t -= 1
        if robot[f] and not robot[t] and conveyor[t]:
            robot[t] = 1
            robot[f] = 0

            conveyor[t] -= 1
            if conveyor[t] == 0:
                zero_d += 1

    robot[end] = 0


def on_robot():
    global zero_d
    if conveyor[start]:
        conveyor[start] -= 1

        if conveyor[start] == 0:
            zero_d += 1

        robot[start] = 1


while zero_d < K:
    rotate()
    move_robot()
    on_robot()
    stage += 1

print(stage)
