arr = [1,2,3,4,5]
target = 4

#===== Brute Force Method =====
# Time complexity: O(n^2)
# Space complexity: O(1)
def sum_pair_brute_force(arr, target):
	for i in range(len(arr) - 1):
		for j in range(i+1, len(arr)):
			if arr[i] + arr[j] == target:
				print(arr[i],arr[j])

#===== Hash Table Method =====
# Time complexity: O(n)
# Space complexity: O(n)
def sum_pair_hash_table(arr, target):
	hash_table = dict()
	for i in range(len(arr)):
		if arr[i] in hash_table:
			print(hash_table[arr[i]], arr[i])
		else:
			hash_table[target - arr[i]] = arr[i]

#===== Efficient Method =====
# Time complexity: O(n)
# Space complexity: O(1)
def sum_pair(arr, target): 
	i = 0
	j = len(arr) - 1

	while i <= j:
		if arr[i] + arr[j] == target: 
			print(arr[i], arr[j])
			return True
		elif arr[i] + arr[j] < target:
			i += 1
		else: # arr[i] + arr[j] < target
			j -= 1
	return False

sum_pair_brute_force(arr, target)
sum_pair_hash_table(arr,target)
sum_pair(arr,target)