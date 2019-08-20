# Goldman Sachs Question - String Compression
# Given an array of characters, compress it in-place.
# The length after compression must always be smaller than or equal to the original array.
# Every element of the array should be a character (not int) of length 1.
# After you are done modifying the input array in-place, return the new length of the array.

def compress(chars):
    left = i = 0
    while i < len(chars):
        char, length = chars[i], 1
        while (i + 1) < len(chars) and char == chars[i + 1]:
            length, i = length + 1, i + 1
        chars[left] = char
        if length > 1:
            len_str = str(length)
            chars[left + 1:left + 1 + len(len_str)] = len_str
            left += len(len_str)
        left, i = left + 1, i + 1
    return left

arr = ["a","a","b","b","c","c","c"]
print(compress(arr))
print(arr)

#========== compress_2 Algorithm ==========
# Maintain a rptr and wptr to write in-place.
# Use 2 while loops - outer loop rptr < len(chars) and inner loops counts the streak of common characters.
# Always write the character, but only write the frequency when it is more than 1. Note that when f = 12, we need to write two characters: "1" and "2".

def compress_2(chars):
    """
    :type chars: List[str]
    :rtype: int
    """
    rptr, wptr = 0, 0
    while rptr < len(chars):
        ch, f = chars[rptr], 0
        while rptr < len(chars) and chars[rptr] == ch:
            rptr, f = rptr+1, f+1
        chars[wptr], wptr = ch, wptr + 1
        if f > 1:
            for c in str(f):
                chars[wptr], wptr = c, wptr + 1
    return wptr

arr = ["a","a","b","b","c","c","c"]
print(compress_2(arr))
print(arr)

# Example 1:
# Input:
# ["a","a","b","b","c","c","c"]
# Output:
# Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
# Explanation:
# "aa" is replaced by "a2". "bb" is replaced by "b2". "ccc" is replaced by "c3".
 

# Example 2:
# Input:
# ["a"]
# Output:
# Return 1, and the first 1 characters of the input array should be: ["a"]
# Explanation:
# Nothing is replaced.
 

# Example 3:
# Input:
# ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
# Output:
# Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].

# Explanation:
# Since the character "a" does not repeat, it is not compressed. "bbbbbbbbbbbb" is replaced by "b12".
# Notice each digit has it's own entry in the array.