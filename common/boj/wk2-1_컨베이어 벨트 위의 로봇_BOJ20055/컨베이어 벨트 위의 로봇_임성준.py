from collections import deque

# 백준 20055번

# 길이가 N인 컨베이어 벨트가 있고, 길이가 2N인 벨트가 이 컨베이어 벨트를 위아래로 감싸며 돌고 있다. 
# 벨트는 길이 1 간격으로 2N개의 칸으로 나뉘어져 있으며, 
# 각 칸에는 아래 그림과 같이 1부터 2N까지의 번호가 매겨져 있다.
# 1번 칸 '올리는 위치' / N번 칸 '내리는 위치'

# 컨베이어 벨트 길이 N
# 내구도가 0인 칸 개수 제한 K
N, K = map(int,input().split())
# 벨트의 내구도
belt = deque(list(map(int,input().split())))
# 박스 로봇이 있냐없냐 체크
box_robot = deque([0]*N)

# 내구도 체크
def durable_check():
  cnt = 0
  for i in range(N*2) :
    # 내구도가 0인 개수 더한다.
    if belt[i] == 0 :
      cnt += 1 
  # 내구도가 0인 개수가 K개 이상일 때
  if cnt >= K :
    return True
  return False

# 단계는 0부터 시작
stage = 0
while True :
  # 내구도 0 개수가 K개 이상일 경우
  if durable_check():
    # 몇번째 단계일때 종료인지 출력
    print(stage)
    break
  # 단계는 증가
  stage += 1
  # 회전 단계 (벨트, 로봇 한번씩)
  belt.rotate(1)
  box_robot.rotate(1)
  # N번 칸에 있으면 N번 칸 내리기
  if box_robot[N-1] != 0 :
    box_robot[N-1] = 0
  # 가장 나중에 올라간 로봇부터
  for i in range(N-1,-1,-1) :
    # 옆이 비어있고, 내구도가 1 이상이면 로봇 이동
    if box_robot[i] == 1 and box_robot[i+1] == 0 and belt[i+1] > 0 :
      box_robot[i+1] = 1
      box_robot[i] = 0
      # 벨트 내구도 감소
      belt[i+1] -= 1
    # N번 칸에 있으면 내리기
    if box_robot[N-1] != 0 :
      box_robot[N-1] = 0
  # 로봇 올리기 단계
  # 첫번째 벨트 (belt[0]) 내구도가 1 이상이면 로봇 배열에 추가
  if belt[0] > 0 :
    box_robot[0] = 1
    belt[0] -= 1
