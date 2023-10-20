def is_dominant(x, y):
    z = x | y
    bin_x = bin(x)[2:]
    bin_z = bin(z)[2:]
    for i in range(len(bin_x)):
        if bin_x[i] == '0' and bin_z[i] == '1':
            return False
    return True
 
def is_zero_saving(function):
    return function[1][0] == "0"
 
def is_one_saving(function):
    return function[1][len(function[1]) - 1] == '1'
 
def is_selfdual(function):
    if function[0] == 0:
        return False
     
    for j in range(len(function[1]) // 2):
        if function[1][j] == function[1][len(function[1]) - j - 1]:
            return False
     
    return True
 
def is_monotonous(function):
    if function[0] == 0:
        return True
     
    for i in range(len(function[1])):
        if function[1][i] == "0":
            for j in range(i):
                if function[1][j] == "1" and is_dominant(i, j):
                    return False
         
    return True
 
def is_linear(function):
    if function[0] == 0:
        return True
     
    monoms = []
    for i in range(2 ** function[0]):
        monoms.append(bin(i)[2:].zfill(function[0]))
 
    support_table = []
 
    for i in range(2 ** function[0]):
        support_table.append([0] * (2 ** function[0] - i))
 
    for i in range(2 ** function[0]):
        support_table[i][0] = int(function[1][i])
 
    column = 1
    for i in range(1, 2 ** function[0]):
        for j in range(2 ** function[0] - column):
            support_table[j][column] = (int(support_table[j][column - 1]) + int(support_table[j + 1][column - 1])) % 2
        column += 1
 
    for i in range(2 ** function[0]):
        if support_table[0][i] == 1 and monoms[i].count("1") > 1:
            return False
         
    return True
 
n = int(input())
functions = []
 
for i in range(n):
    function = [i for i in input().split()]
    functions.append([int(function[0]), function[1]])
 
table = []
for f in functions:
    row = []
    row.append(is_zero_saving(f))
    row.append(is_one_saving(f))
    row.append(is_selfdual(f))
    row.append(is_monotonous(f))
    row.append(is_linear(f))
    table.append(row)
 
is_full = []
for i in range(5):
    is_full.append(all(table[j][i] for j in range(n)))
 
for i in range(5):
    if is_full[i]:
        print("NO")
        exit()
print("YES")