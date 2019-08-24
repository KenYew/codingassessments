"""
Check Duplicates (Coding Assessment Question 2) - JP Morgan
The problem is as follows: choose a number, reverse its digits and add it to the original. If the sum has any duplicate digits, repeat this procedure.

Example:
122 (initial number) + 221 (reverse of initial number) = 343
343 + 343 = 686
686 + 686 = 1372 (no duplicate digits)
In this particular case the first sum with no duplicate digits, 1372, appeared adter the 3rd addition.

Input: "162"
Output: "1 423"

Input: "122"
Output: "3 1372"
"""
def reverse_string(s):
    return s[::-1]

def check_duplicates(n):
    if str(n) == reverse_string(str(n)):
        return True
    else: 
        return False

def sum_duplicates(n):
    reversed_n = reverse_string(str(n))
    n = n + int(reversed_n)
    count = 1
    while check_duplicates(n): 
        reversed_n = reverse_string(str(n))
        n = n + int(reversed_n)
        count += 1
    return count, n

print(sum_duplicates(162))
print(sum_duplicates(122))