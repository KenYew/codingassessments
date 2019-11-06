# input:
# - card number
# - type
# - amount (in cents)
# - timestamp

def amounts_to_return_users(transaction_activities):
    # sort the transactions based on timestamp
    sorted_t = sorted([t.split(",")[0]+","+t.split(",")[-1]+","+",".join(t.split(",")[1:3]) for t in transaction_activities])

    cards = {}
    for transaction in sorted_t:
        card,timestamp,command,value = transaction.split(",")
        value = int(value)

        # CREATION
        if command == 'Creation':
            cards[card] = {"Initial": value, "Purchased": False, "Affirm": value, "User": 0}

        # RETURN
        elif (command == 'Load') & (cards[card]["Purchased"] == True):
            if value <= cards[card]["Initial"]-cards[card]["Affirm"]:
                cards[card]["Affirm"] += value
            else:
                cards[card]["User"] += value-(cards[card]["Initial"]-cards[card]["Affirm"])
                cards[card]["Affirm"] = cards[card]["Initial"]
        # LOAD
        elif (command == 'Load') & (cards[card]["Purchased"] == False):
            cards[card]["User"] += value

        # PURCHASE
        elif command == 'Purchase':
            cards[card]["Purchased"] = True
            if cards[card]["Affirm"] >= value:
                cards[card]['Affirm'] -= value
            else:
                cards[card]['User'] -= (value-cards[card]["Affirm"])
                cards[card]['Affirm'] = 0

    return [c+"**"+str(cards[c]["User"]) for c in cards if (cards[c]["User"] > 0) and (cards[c]["Purchased"] == True)]

# print(amounts_to_return_users([
#     "1289,Creation,120000,2019-05-18 05:30:00",
#     "1289,Load,40000,2019-05-18 05:31:00",
#     "510,Creation,120000,2019-05-18 02:30:00",
#     "510,Load,50000,2019-05-18 02:31:00",
#     "1289,Load,10000,2019-05-18 05:31:30",
#     "361,Purchase,100000,2019-05-18 06:32:00",
#     "361,Load,90000,2019-05-18 06:33:00",
#     "1289,Purchase,150000,2019-05-18 05:32:00",
#     "1289,Load,140000,2019-05-18 05:34:00",
#     "510,Purchase,150000,2019-05-18 02:32:00",
#     "510,Load,10000,2019-05-18 02:33:00",
#     "510,Load,100000,2019-05-18 02:34:00",
#     "361,Creation,120000,2019-05-18 06:30:00",
#     "361,Load,50000,2019-05-18 06:31:00",
#     "7,Creation,120000,2019-05-18 09:30:00",
#     "8888,Creation,50000,2019-05-18 15:30:00",
#     "8888,Load,50000,2019-05-18 15:35:00",
#     "10,Creation,10000,2019-05-18 14:29:00",
#     "10,Load,70000,2019-05-18 14:30:00",
#     "8888,Purchase,100000,2019-05-18 15:40:00",
#     "8888,Load,50000,2019-05-18 15:47:00"
# ]))

def amounts_to_return_users_for_date(curr_date, date_and_transaction_activities):
    # sort the transactions based on timestamp
    sorted_t = sorted([t.split(",")[0]+","+t.split(",")[-1]+","+",".join(t.split(",")[1:3]) for t in date_and_transaction_activities])

    cards = {}
    for transaction in sorted_t:
        card,timestamp,command,value = transaction.split(",")
        date_,time_ = timestamp.split(" ")
        value = int(value)

        if (cards.get(card) is not None) and (date_ != cards[card]["Date"]):
            cards[card]["Date"] = date_
            if (cards[card]["Purchased"] == True): cards[card]["User"] = 0

        # CREATION
        if command == 'Creation':
            cards[card] = {"Date": date_, "Initial": value, "Purchased": False, "Affirm": value, "User": 0}

        # RETURN
        elif (command == 'Load') & (cards[card]["Purchased"] == True):
            if value <= cards[card]["Initial"]-cards[card]["Affirm"]:
                cards[card]["Affirm"] += value
            else:
                cards[card]["User"] += value-(cards[card]["Initial"]-cards[card]["Affirm"])
                cards[card]["Affirm"] = cards[card]["Initial"]
        # LOAD
        elif (command == 'Load') & (cards[card]["Purchased"] == False):
            cards[card]["User"] += value

        # PURCHASE
        elif command == 'Purchase':
            cards[card]["Purchased"] = True
            if cards[card]["Affirm"] >= value:
                cards[card]['Affirm'] -= value
            else:
                cards[card]['User'] -= (value-cards[card]["Affirm"])
                cards[card]['Affirm'] = 0

    return [c+"**"+str(cards[c]["User"]) for c in cards if (cards[c]["User"] > 0) and (cards[c]["Purchased"] == True)]

print(amounts_to_return_users_for_date("2019-05-19", [
    "1289,Creation,120000,2019-05-18 05:30:00",
    "1289,Load,50000,2019-05-18 05:31:00",
    "777,Creation,120000,2019-05-18 05:30:00",
    "777,Load,50000,2019-05-18 05:31:00",
    "1289,Purchase,150000,2019-05-18 05:32:00",
    "1289,Load,130000,2019-05-19 05:33:00",
    "868,Creation,120000,2019-05-19 05:30:00",
    "868,Load,40000,2019-05-19 05:31:00",
    "777,Purchase,150000,2019-05-19 05:32:00",
    "777,Load,130000,2019-05-19 05:33:00"
]))