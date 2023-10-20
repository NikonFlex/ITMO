def is_reflective(x):
    return all(x[i][i] == 1 for i in range(len(x)))
 
def is_antireflective(x):
    return not any(x[i][i] for i in range(n))
 
def is_symmetric(x):
    return all(x[i][j] == x[j][i] for i in range(n) for j in range(n))
 
def is_antisymmetric(x):
    for i in range(n):
        for j in range(n):
            if x[i][j] == 1 and x[j][i] == 1 and i != j:
                return False
    return True
 
def is_transitive(x):
    for i in range(n):
        for j in range(n):
            for k in range(n):
                if x[i][j] == 1 and x[j][k] == 1 and x[i][k] != 1:
                    return False
    return True
                 
def calc_composition(x, y):
    composition = [[0 for i in range(n)] for j in range(n)]
    for i in range(n):
        for j in range(n):
            for k in range(n):
                if x[i][k] == 1 and y[k][j] == 1:
                    composition[i][j] = 1
    return composition
 
n = int(input())
R = []
for _ in range(n):
    R.append([int(i) for i in input().split()])
 
S = []
for _ in range(n):
    S.append([int(i) for i in input().split()])
 
print(int(is_reflective(R)), end=" ")
print(int(is_antireflective(R)), end=" ")
print(int(is_symmetric(R)), end=" ")
print(int(is_antisymmetric(R)), end=" ")
print(int(is_transitive(R)))
print(int(is_reflective(S)), end=" ")
print(int(is_antireflective(S)), end=" ")
print(int(is_symmetric(S)), end=" ")
print(int(is_antisymmetric(S)), end=" ")
print(int(is_transitive(S)))
composition = calc_composition(R, S)
for i in range(n):
    for j in range(n):
        print(composition[i][j], end=" ")
    print()