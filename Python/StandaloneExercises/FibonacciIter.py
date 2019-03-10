def fibonacciIter(n):
    a, b = 0, 1
    for i in range (0, n):
        a, b = b, a+b
    return a

def fibonacciIterPrint(n):
        a, b = 0, 1
        for i in range(0, n):
            a, b = b, a + b
            print(a)
        return a
#####
n = int(input("Enter a fibonacci's number index to print"))
print( fibonacciIter(n))

print("Lets print some more ;)")

fibonacciIterPrint(n)