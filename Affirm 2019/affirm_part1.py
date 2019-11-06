# spot fraud credit card authorisations

# transaction type: 'card','auth'

# LOGIC -> authorisation is accepted if:
# - 1) card is found within database
# - 2) auth amount is not larger than amount in card
# - 3) country is "USA", industry is not "bitcoin" or "precious metals" 

# INPUT -> list of json strings
# OUTPUT -> only auth transactions, showing whether they are approved

import json

def auth_logic(cards,i):
    if i['card_number'] not in cards: return False
    if i['amount_cents'] > cards[i['card_number']]: return False
    if ((i['industry'] == 'precious metals') or 
        (i['industry'] == 'bitcoin') or 
        (i['country']) != 'USA'): return False
    return True

def authorise_credit_cards(lst):
    answer = []
    cards = {}

    for transaction in lst:
        i = json.loads(transaction)
        
        if i['transaction_type'] == 'card':
            cards[i['card_number']] = i['amount_cents']
        elif i['transaction_type'] == 'auth':
            if auth_logic(cards,i):
                i['approved'] = True
                cards[i['card_number']] -= i['amount_cents']
            else:
                i['approved'] = False
            answer.append(json.dumps(i))

    return answer

answer = authorise_credit_cards([
    '{"transaction_type":"card", "card_number":23849, "amount_cents":40000}',
    '{"transaction_type":"auth", "card_number":23849, "amount_cents":3000, "name":"Amazon", "industry":"retail", "country":"USA"}',
    '{"transaction_type":"auth", "card_number":238, "amount_cents":3000, "name":"Amazon", "industry":"retail", "country":"USA"}',
    '{"transaction_type":"auth", "card_number":23849, "amount_cents":300000, "name":"Amazon", "industry":"retail", "country":"USA"}',
    '{"transaction_type":"auth", "card_number":23849, "amount_cents":3000, "name":"Alibaba", "industry":"retail", "country":"China"}'
])
for i in answer: print(i)