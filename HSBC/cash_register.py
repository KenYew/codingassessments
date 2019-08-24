"""
Cash Register (Coding Assessment Question 2) - HSBC
The goal of this challenge is to design a cash register program. You will be given two decimal numbers. The first is the purchase price (PP) of the item. The second is the case (CH) given by the customer.
Your register currently has the following bills/coins within it:
"PENNY":.01, "NICKEL":.05, "DIME":.10, "QUARTER":.25, "HALF DOLLAR":.50, "ONE":1.00, "TWO":2.00, "FIVE": 5.00, "TEN":10.00, "TWENTY":20.00, "FIFTY":50.00, "ONE HUNDRED":100.00

The aim of the program is to calculate the change that has to be returned to the customer. 

Input: Your program should read lines of text from standard input. Each line contains two numnbers which are separated by a semicolon. The first is the Purchase Price (PP) of the item. The second is the cash (CH) given by the customer.

Output: For each line of input, a single line to standard output which is the change to be returned to the customer. In case the CH < PP, print out ERROR. If CH == PP, print out ZERO. For all other cases print the amount that needs to be returned, in terms of the currency values provided. The output should be alphabetically sorted. 

Input: 15.94;16.00
Output: "NICKEL, PENNY"

Input: 17;16
Output: "ERROR"
"""

mapping = {100.00:"ONE HUNDRED", 50.00:"FIFTY", 20.00:"TWENTY", 10.00:"TEN", 5.00:"FIVE", 2.00:"TWO", 1.00:"ONE", .50:"HALF DOLLAR", .25:"QUARTER", .10:"DIME", .05:"NICKEL", .01:"PENNY"}

def cash_register(line):
    pp, ch = line.split(";")
    pp, ch = float(pp), float(ch)
    rst = []

    if ch < pp:
        return "ERROR"
    elif ch == pp:
        return "ZERO"
    else: 
        change = ch - pp
        change = round(change, 2)

        for value in mapping.keys():
            if value <= change: 
                number_of_coins = int(change/value)
                for i in range(number_of_coins):
                    rst.append(mapping[value])
                change %= value
                change = round(change, 2)
        return(rst)

print(cash_register("15.94;16.00"))
print(cash_register("17;16"))

"""
ANSWER:
['NICKEL', 'PENNY']
ERROR
"""