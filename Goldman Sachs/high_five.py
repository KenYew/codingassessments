# Goldman Sachs Question - High Five
# Given a list of scores of different students, return the average score of each student's top five scores in the order of each student's id.
# Each entry items[i] has items[i][0] the student's id, and items[i][1] the student's score.  The average score is calculated using integer division.

# Example 1:

# Input: [[1,91],[1,92],[2,93],[2,97],[1,60],[2,77],[1,65],[1,87],[1,100],[2,100],[2,76]]
# Output: [[1,87],[2,88]]
# Explanation: 
# The average of the student with id = 1 is 87.
# The average of the student with id = 2 is 88.6. But with integer division their average converts to 88.

# Note:
# 1 <= items.length <= 1000
# items[i].length == 2
# The IDs of the students is between 1 to 1000
# The score of the students is between 1 to 100
# For each student, there are at least 5 scores

# I started by sorting items by scores. That way, if I start from the end, I can easily get the top 5 scores for each student. 
# I created dictionaries to keep track of the total scores of each student and how many scores I have added up so far. 
# I only updated score of a student if that student popped up less than 5 times.
def highFive(items):
    items.sort(key=lambda x: x[1])
    output = []
    freq = {}
    scores = {}
    i = len(items) - 1
    while i >= 0:
        if items[i][0] in freq:
            freq[items[i][0]] += 1
            if freq[items[i][0]] <= 5:
                scores[items[i][0]] += items[i][1]
        else:
            freq[items[i][0]] = 1
            scores[items[i][0]] = items[i][1]
        i -= 1
        
    for key in freq:
        output.append([key, scores[key] // 5])
    return sorted(output, key=lambda x:x[0])
    """
    :type items: List[List[int]]
    :rtype: List[List[int]]
    """

lst = [[1,91],[1,92],[2,93],[2,97],[1,60],[2,77],[1,65],[1,87],[1,100],[2,100],[2,76]]
print(highFive(lst))