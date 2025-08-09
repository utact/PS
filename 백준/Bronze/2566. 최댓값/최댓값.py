import sys

arrays = []

for _ in range(9):
    row = list(map(int, sys.stdin.readline().strip().split()))
    arrays.append(row)

temp = []

for row in arrays:
    temp.append(max(row))

maxNum = max(temp)
maxRow = temp.index(maxNum)
maxColumn = list(arrays[maxRow]).index(maxNum)

print(maxNum)
print(maxRow + 1, maxColumn + 1)