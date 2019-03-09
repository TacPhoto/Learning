def countRecursive(n):
    print(n)
    if n == 1:
        return 1
    else:
        return(n + countRecursive(n - 1))

def sum(n):
    if n == 1:
        return 1
    else:
        return(n + sum(n - 1))
#####
num = int(input("Enter a number"))
countRecursive(num)
print("The sum is ", sum(num))

sumTwo = sum(30)
print("Sum of 30 is ", sumTwo)