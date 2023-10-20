from enum import Enum
 
class TruthRow:
    def __init__(self, args: str, result: int):
        self.__args = args
        self.__result = result
 
    @property
    def args(self):
        return self.__args
     
    @property
    def result(self):
        return self.__result
 
class ElementType(Enum):
    NOT = 1
    MUL = 2
    ADD = 3
 
class Element:
    def __init__(self, operation: ElementType, arg_1: int, arg_2: int = None):
        self.__operation = operation
        self.__arg_1 = arg_1
        self.__arg_2 = arg_2
     
    def __str__(self):
        if self.__arg_2 is None:
            return str(self.__operation.value) + " " + str(self.__arg_1)
        return str(self.__operation.value) + " " + str(self.__arg_1) + " " + str(self.__arg_2)
 
def create_scheme_for_one(n):
    print(n + 2)
    print("1 1")
    print("3 1 " + str(n + 1))
 
def create_scheme_for_zero(n):
    print(n + 2)
    print("1 1")
    print("2 1 " + str(n + 1))
 
def collect_close(truth_table):
    close_elems = []
    for j in range(len(truth_table[0].args)):
        if truth_table[i].args[j] == '1':
            close_elems.append(j)
        else:
            close_elems.append(j + n)
    return close_elems
 
def mul_close(scheme, vars, elem_count):
    scheme.append(Element(ElementType.MUL, vars[0] + 1, vars[1] + 1))
    elem_count += 1
    for j in range(1, len(vars) - 1):
        scheme.append(Element(ElementType.MUL, elem_count, vars[j + 1] + 1))
        elem_count += 1
    return elem_count
 
def add_closes(scheme, closes, elem_count):
    scheme.append(Element(ElementType.ADD, closes[0], closes[1]))
    elem_count += 1
    for i in range(2, len(closes)):
        scheme.append(Element(ElementType.ADD, elem_count, closes[i]))
        elem_count += 1
 
    return elem_count
 
n = int(input())
truth_table = list()
scheme = list()
close_elems = list()
closes = list()
elem_count = n * 2
 
for i in range(2 ** n):
    f = [i for i in input().split()]
    args = f[0]
    ans = int(f[1])
    truth_table.append(TruthRow(args, ans))
 
# запишем все элементы не х
for i in range(n):
    scheme.append(Element(ElementType.NOT, i + 1))
 
for i in range(len(truth_table)):
    #тк сднф - единицы пропускаем
    if truth_table[i].result == 0:
        continue
 
    close_elems = collect_close(truth_table)
     
    elem_count = mul_close(scheme, close_elems, elem_count)
    closes.append(elem_count)
    close_elems = list()
 
if len(closes) > 1:
    elem_count = add_closes(scheme, closes, elem_count)
 
# тожд 0 или 1
if elem_count == n * 2:
    if truth_table[0].result == 1:
        create_scheme_for_one(n)
    else:
        create_scheme_for_zero(n)
    exit()
 
print(elem_count)
for elem in scheme:
    print(elem)