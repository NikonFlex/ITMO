def create_zhegalkin_polynom(function):
    monoms = []
    for i in range(2 ** function[0]):
        monoms.append(bin(i)[2:].zfill(function[0]))
 
    support_table = []
 
    for i in range(2 ** function[0]):
        support_table.append([0] * (2 ** function[0] - i))
 
    for i in range(2 ** function[0]):
        row = bin(i)[2:].zfill(function[0])
        support_table[i][0] = int(function[1][row])
 
    column = 1
    for i in range(1, 2 ** function[0]):
        for j in range(2 ** function[0] - column):
            support_table[j][column] = (int(support_table[j][column - 1]) + int(support_table[j + 1][column - 1])) % 2
        column += 1
 
    for i in range(len(monoms)):
        print(monoms[i], end=" ")
        print(support_table[0][i])
 
n = int(input())
function_table = dict()
for i in range(2 ** n):
    row = [i for i in input().split()]
    function_table[row[0]] = row[1]
create_zhegalkin_polynom([n, function_table])