# spot fraud credit card authorisations

# transaction type: 'card','rule','auth'

# LOGIC -> authorisation is accepted if:
# - 1) card is found within database
# - 2) auth amount is not larger than amount in card
# - 3) country is "USA", industry is not "bitcoin" or "precious metals" 

# INPUT -> list of json strings
# OUTPUT -> only auth transactions, showing whether they are approved

import json

def auth_logic(cards,rules,i):
    # check card
    if i['card_number'] not in cards: return False
    if i['amount_cents'] > cards[i['card_number']]['amount_cents']: return False

    # check rules
    if cards[i['card_number']]['category'] in rules:
        rule = rules[cards[i['card_number']]['category']]
        if rule['block_match']:
            if i[rule['field']] in rule['values']: return False
        else:
            if i[rule['field']] not in rule['values']: return False
    
    return True

def authorise_credit_cards(lst):
    answer = []
    cards = {}
    rules = {}

    for transaction in lst:
        i = json.loads(transaction)
        
        if i['transaction_type'] == 'card':
            cards[i['card_number']] = {
                "amount_cents": i['amount_cents'],
                "category": i["category"]
            }
        elif i['transaction_type'] == 'rule':
            rules[i['category']] = {
                "field": i["field"],
                "values": i["values"],
                "block_match": True if i["rule_type"]=="block_match" else False
            }
        elif i['transaction_type'] == 'auth':
            if auth_logic(cards,rules,i):
                i['approved'] = True
                cards[i['card_number']]['amount_cents'] -= i['amount_cents']
            else:
                i['approved'] = False
            answer.append(json.dumps(i))

    return answer

answer = authorise_credit_cards([
    '{"transaction_type":"card", "card_number":23849, "category":"default", "amount_cents":40000}',
    '{"transaction_type":"rule", "category":"default", "field":"name", "values":["Google","Amazon"], "rule_type":"block_no_match"}',
    '{"transaction_type":"rule", "category":"trend", "field":"industry", "values":["fashion"], "rule_type":"block_match"}',
    '{"transaction_type":"auth", "card_number":2389, "amount_cents":3000, "name":"Amazon", "industry":"retail", "country":"USA"}',
    '{"transaction_type":"auth", "card_number":23849, "amount_cents":3000, "name":"Amazon", "industry":"fashion", "country":"USA"}',
    '{"transaction_type":"auth", "card_number":23849, "amount_cents":300000, "name":"Amazon", "industry":"retail", "country":"USA"}',
    '{"transaction_type":"auth", "card_number":23849, "amount_cents":3000, "name":"Alibaba", "industry":"retail", "country":"China"}',
    '{"transaction_type":"card", "card_number":3, "category":"trend", "amount_cents":40000}',
    '{"transaction_type":"auth", "card_number":3, "amount_cents":3000, "name":"Alibaba", "industry":"Retail", "country":"China"}'
])
for i in answer: print(i)