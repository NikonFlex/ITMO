def generate_overflow_formula(n):
    if n == 1:
        return "((A0|B0)|(A0|B0))"
    else:
        n -= 1
        return f"(({generate_overflow_formula(n)}|((A{n}|A{n})|(B{n}|B{n})))|(A{n}|B{n}))"
     
n = int(input())
overflow_formula = generate_overflow_formula(n)
print(overflow_formula)