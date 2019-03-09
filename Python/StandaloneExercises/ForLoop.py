arr = [1, 4, 2, 6, 2, 6, 8, 9]

sum = 0

for val in arr: #sum the array
    sum += val

print("The sum of arr is ", sum)
###
sum = 0 #reset sum

for val in arr: #sum the array till a specified number appears
    if val == 6: #stop statement
        break
    sum += val

print("The sum of arr till the first appearance of 6 is ", sum)
###
sum = 0 #reset sum

for val in arr: #sum the array skipping a specified number
    if val == 6: #skip
        continue
    sum += val
print("The sum of arr without appearances of 6 is ", sum)
###
string = "some text here"

for val in string: #loop with a placeholder
    pass #loop body placeholder