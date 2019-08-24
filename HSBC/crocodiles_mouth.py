"""
Crocodile's Mouth (Coding Assessment Question 1) - HSBC
Little Tommy is in kindergarten on the first day of class. His teacher has taught him about inequalities today, and he is learning how to draw crocodiles to represent them. When there are two numbers, A and B, there are three options: 
1. If A is greater than B, then draw ">". The crocodile's mouth is pointed toward the bigger number, A. 
2. If A is less than B, then draw "<". The crocodile faces B. 
3. If A is equal to B, then draw "=". The crocodile is confused and keeps its mouth shut. 
Unfortunately, Tommy does not like to do his homework, and has bribed you to write a program to do it for him. 

Input: The input consists of two integers A and B on a line, separated by a space. |A,B| < 2^63.
Output: Print a line containing the appropriate symbol that describes the relationship between the numbers. 

Input: 35 40
Output: <
"""
def crocodiles_mouth(line):
    n = line.split()
    A = int(n[0])
    B = int(n[1])
    
    if A > B:
        print(">")
    elif A < B:
        print("<")
    else: 
        print("=")

crocodiles_mouth("35 40")