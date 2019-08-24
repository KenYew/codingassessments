"""
Minimum Coins (Coding Assessment Question 1) - JP Morgan
You are given coins of value 1, 2, and 4. You are also given a total which you have to arrive at. 
Find the minimum number of coins to arrive at the total. 

Input: Your program should read lines from standard input. Each line contains a positive integer number which represents the total you have to arrive at.
Output: Print out the minimum number of coins required to arrive at the total. 

Input: "11"
Output: "4"

Input: "20"
Output: "5"
"""

def minimum_coins(n):
    rst = 0
    coins = [4, 2, 1]

    for coin in coins:
        number_of_coins = n // coin
        n %= coin
        rst += number_of_coins
    return rst

print(minimum_coins(11))
print(minimum_coins(20))