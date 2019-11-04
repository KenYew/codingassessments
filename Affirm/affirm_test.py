# 1. Subarray products. Given an array, find the number of subarrays whose product is less than a given value ‘k’
# 2. Prefix to Postfix

# How would you implement a persistent stack?

# Recursive and iterative way to flatten a list, given  [1, [2,3], [[[4]]]], return [1,2,3,4]
def flatten(li):
    return sum(([x] if not isinstance(x, list) else flatten(x)
        for x in li), [])

print(flatten([1, 2, [3], [4, [5, 6]],[],[]]))