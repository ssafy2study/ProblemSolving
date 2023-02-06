import sys

input = sys.stdin.readline
N = int(input())
numbers = list(map(int, input().split()))
numbers.sort()
good_num_idx = [False] * N

for i in range(N):
    target = numbers[i]
    start = 0
    end = N - 1

    while start < end:
        # 자기 자신은 세면 안되니까
        if start == i:
            start += 1
            continue
        if end == i:
            end -= 1
            continue
        if target == numbers[start] + numbers[end]:
            good_num_idx[i] = True
            break
        if target > numbers[start] + numbers[end]:
            start += 1
        else:
            end -= 1

# print(good_num_idx)
result = [r for r in good_num_idx if r]

print(len(result))