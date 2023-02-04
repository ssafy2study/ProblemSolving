# 좋다_BOJ1253

# input.txt 열기
import sys
sys.stdin = open('input.txt')

# input 받기
N = int(sys.stdin.readline().strip())                   # N input 받을 숫자의 개수
like = list(map(int, sys.stdin.readline().split()))     # N개의 숫자 리스트를 like에 input 받는다

like.sort()                                             # like를 오름차순으로 정렬한 후

ans = 0                                                 # 정답의 수를 저장할 변수를 생성한다
for l in range(len(like)):                              # like의 인덱스를 반복해서
    tmp = like[l]                                       # tmp에 l 위치의 like 원소를 저장한다
    if l == 0:                                          # 만약 l이 0이면
        start = 1                                       # start를 1
        end = N-1                                       # end를 N-1로 변수를 생성한다
    elif l == N-1:                                      # 만약 l이 N-1이면
        start = 0                                       # start를 0
        end = N-2                                       # end를 N-2로 변수를 생성한다
    else:                                               # l이 0이나 N-1이 아니면
        start = 0                                       # start를 0
        end = N-1                                       # end를 N-1로 변수를 생성한다
    while start < end:                                  # start가 end보다 커질 때까지 반복해서
        if tmp < like[start] + like[end]:               # tmp가 start 위치의 like 원소와 end 위치의 like 원소의 합보다 작다면
            end -= 1                                    # end를 1을 빼주고
            if end == l:                                # end가 l이라면
                end -= 1                                # 1을 한 번 더 빼준다
        elif tmp > like[start] + like[end]:             # tmp가 start 위치의 like 원소와 end 위치의 like 원소의 합보다 크다면
            start += 1                                  # start에 1을 더해주고
            if start == l:                              # start가 l이라면
                start += 1                              # 1을 한 번 더 더해준다
        else:                                           # tmp가 start 위치의 like 원소와 end 위치의 like 원소의 합과 같다면
            ans += 1                                    # ans를 1 추가하고
            break                                       # while문을 break한다
print(ans)                                              # 모든 숫자의 탐색을 마쳤다면 ans를 출력한다
