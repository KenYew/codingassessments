# spot fraud credit card authorisations

# transaction types: 'card','rule','subcategory','auth'

# LOGIC -> authorisation is accepted if:
# - 1) card is found within database
# - 2) auth amount is not larger than amount in card
# - 3) it follows the dynamic rules that are listed along the way
# - 4) if card matches a subcategory of the category relevant to the rule, the rule still applies
# - 5) once card is blacklisted, further auth on the same card will be automatically rejected

# INPUT -> list of json strings
# OUTPUT -> only auth transactions, showing whether they are approved

import json

# auth_logic returns
# - 1) the status of the authentication process
# - 2) whether card should be blacklisted
def auth_logic(cards,rules,subs,i):
    blacklist = False

    # check card
    if i['card_number'] not in cards: return False, blacklist
    if cards[i['card_number']]['blacklisted']: return False, blacklist
    if i['amount_cents'] > cards[i['card_number']]['amount_cents']: return False, blacklist

    category = cards[i['card_number']]['category']

    # check rules
    if category in rules:
        rule = rules[category]
        blacklist = rule['blacklist']

        if rule['block_match']:
            if i[rule['field']] in rule['values']: return False, blacklist
        else:
            if i[rule['field']] not in rule['values']: return False, blacklist
    # check sub categories
    elif category in subs:
        parent = subs[category]
        while parent in subs: parent = subs[parent]
        if parent in rules:
            rule = rules[parent]
            blacklist = rule['blacklist']

            if rule['block_match']:
                if i[rule['field']] in rule['values']: return False, blacklist
            else:
                if i[rule['field']] not in rule['values']: return False, blacklist
    
    return True, blacklist

def authorise_credit_cards(lst):
    answer = []
    cards = {}
    subs = {}
    rules = {}

    for transaction in lst:
        i = json.loads(transaction)
        
        if i['transaction_type'] == 'card':
            cards[i['card_number']] = {
                "amount_cents": i['amount_cents'],
                "category": i["category"],
                "blacklisted": False
            }
        elif i['transaction_type'] == 'subcategory':
            for children in i['children']:
                subs[children] = i['parent']  
        elif i['transaction_type'] == 'rule':
            rules[i['category']] = {
                "field": i["field"],
                "values": i["values"],
                "block_match": True if i["rule_type"]=="block_match" else False,
                "blacklist": i['blacklist']
            }
        elif i['transaction_type'] == 'auth':
            approved, blacklisted = auth_logic(cards,rules,subs,i)
            if approved:
                i['approved'] = True
                cards[i['card_number']]['amount_cents'] -= i['amount_cents']
            else:
                i['approved'] = False
                if blacklisted: cards[i['card_number']]['blacklisted'] = True
            answer.append(json.dumps(i))

    return answer

answer = authorise_credit_cards([
    '{"transaction_type":"card", "card_number":23849, "category":"default", "amount_cents":40000}',
    '{"transaction_type":"subcategory", "parent":"test1", "children":["test3","test4"]}',
    '{"transaction_type":"subcategory", "parent":"default", "children":["test1","test2"]}',
    '{"transaction_type":"rule", "category":"default", "field":"name", "values":["Google","Amazon"], "rule_type":"block_no_match", "blacklist":false}',
    '{"transaction_type":"card", "card_number":3, "category":"test4", "amount_cents":40000}',
    '{"transaction_type":"auth", "card_number":3, "amount_cents":3000, "name":"Alibaba", "industry":"Retail", "country":"China"}',
    '{"transaction_type":"auth", "card_number":23849, "amount_cents":3000, "name":"Amazon", "industry":"Retail", "country":"USA"}'
])
for i in answer: print(i)