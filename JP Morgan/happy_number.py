# Happy Number (#202) - JP Morgan
# Write an algorithm to determine if a number is "happy".
# A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.

# Example: 
# Input: 19
# Output: true
# Explanation: 
# 1^2 + 9^2 = 82
# 8^2 + 2^2 = 68
# 6^2 + 8^2 = 100
# 1^2 + 0^2 + 0^2 = 1

def happy_number(n): 
    dict_numbers = {} 

    while n != 1: 
        if n in dict_numbers:
            return False
        else: 
            # Set the n = True at the n-th index of the dict
            dict_numbers[n] = True
            n = sum([int(i) ** 2 for i in str(n)])
    return True

            # print(dict_numbers)
            # print(str(n))
            # {19: True}
            # 19
            # {19: True, 82: True}
            # 82
            # {19: True, 82: True, 68: True}
            # 68
            # {19: True, 82: True, 68: True, 100: True}
            # 100
            # True
            # n = sum([pow(int(i), 2) for i in str(n)])

def happy_number(n):
    if n == 1: return True
    n = sum([int(i) ** 2 for i in str(n)])
    return happy_number(n)

print(happy_number(19))