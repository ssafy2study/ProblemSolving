# 하늘에서별똥별이빗발친다_BOJ14658

# input.txt 열기
import sys
sys.stdin = open('input.txt')

# input 받기
N, M, L, K = map(int, sys.stdin.readline().split())                 # N 별똥별이 떨어지는 구역의 가로길이 / M 세로길이 / L 트램펄린의 한 변의 길이 / K 별똥별의 수
star = []                                                           # 별똥별의 좌표를 담을 빈 리스트 생성

for k in range(K):                                                  # 별똥별의 수만큼 반복해서
    j, i = map(int, sys.stdin.readline().split())                   # 별똥별의 행을 j에 열을 i에 input 받고
    star.append((i, j))                                             # star리스트에 (행,렬)형태롤 append 한다

star.sort()                                                         # star를 오름차순으로 정렬한 후
ans = 0                                                             # 트램펄린으로 튕겨낼 별의 수를 저장할 변수 생성

for i in range(K):                                                  # 전체 별똥별을 반복하고
    for j in range(i, K):                                           # i번째 별똥별부터 끝까지 반복해서
        A = star[i][0]                                              # i별의 행을 A에 저장하고
        B = star[i][1]                                              # i별의 열을 B에 저장하고
        C = star[j][0]                                              # j별의 행을 C에 저장하고
        D = star[j][1]                                              # j별의 열을 D에 저장한다
        if abs(A - C) <= L:                                         # i와 j별의 행의 차가 L보다 작아야만 트램펄린 안에 있으므로 절대 값이 L 이하인 것만 구하고
            if abs(B - D) <= L:                                     # i와 j별의 열의 차가 L보다 작아야만 트램펄린 안에 있으므로 절대 값이 L 이하인 것만 구한다
                tmp = 0                                             # i와 별이 한 트램펄린 안에 있는 경우에만 트램펄린으로 튕겨낼 별의 수를 저장할 변수를 생성하고
                for s in star:                                      # 별똥별을 반복해서
                    if 0 <= s[0] - A <= L and 0 <= s[1] - D <= L:   # s별의 위치가 i별의 행부터 i별의 행 + L 사이에 있고 j별의 열부터 j별의 열 + L 사이에 있으면 트램펄린 내에 있으면
                        tmp += 1                                    # 별똥별을 튕겨 낼 수 있으므로 튕겨낸 별똥별의 수를 1 더한다
                else:                                               # 모든 별똥별을 탐색했다면
                    ans = max(ans, tmp)                             # ans와 tmp 중 더 큰값을 ans에 저장한다
print(K-ans)                                                        # 전체 별똥별 중 튕겨낸 별을 뺀 나머지 별똥별을 출력한다