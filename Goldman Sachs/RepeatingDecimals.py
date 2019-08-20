def RepeatingDecimals(num, den): 
    # Checks required: 
    # 1. Check if the numerator is 0 
    if num == 0:
        return "0"

    # 2. Check if the denominator is 0
    if den == 0:
        return "Undefined"

    # 3. Check if the remainder is 0
    if num % den == 0: 
        return str(num/den)

    # 4. Check if both the numerator and denominator is negative
    neg = False
    if num * den < 0:
        neg = True

    # The absolute values of the inputs num and den would suffice 
    num = abs(num)
    den = abs(den)
     
    # Concatenate the output result
    output = "" # Initialise the output string
    output += str(num // den) # // indicates floor division which returns just the quotient and dumping digits after the decimal
    output += "."

    num_q = []
    while True: 
        rem = num % den 

        # CASE 1: Problem: 1/4 gives 0.250
        if rem == 0: 
            for element in num_q: 
                output += str(element[-1])
            break

        num = rem * 10
        q = num // den

        if [num, q] not in num_q:
            num_q.append([num, q])
        elif [num, q] in num_q:
            # CASE 2: Problem: 1/6 gives 0.(16) instead of 0.(6)
            ind = num_q.index([num, q])
            for element in num_q[:ind]:
                output += str(element[-1])

            output += "("
            for element in num_q[ind:]: 
                # for i in range(-1,2):
                #     print("[%s]" % i, element[i])
                output += str(element[-1])
            output += ")"
            break
    return output
print(RepeatingDecimals(1,3))
print(RepeatingDecimals(1,4))
print(RepeatingDecimals(1,6))