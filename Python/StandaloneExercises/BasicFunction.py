def helloWorld(): #prints "Hello World"
    for val in "Hello World":
        print(val)

def factorial(n):
    if n < 0:
        print("Negative number! Cannot calculate a factorial.")
    elif n == 0:
        print("Factorial = 1")
    else:
        fact = 1
        for i in range( 1 , n + 1 ):
            fact = fact * i;

        print("Factorial = ", fact)

def factorialDriver():
    n = int(input("Enter an integer to find a factorial of"))
    factorial(n)
#####MAIN:
helloWorld()
factorialDriver()
#####it's pretty funny to test your PC's cooling by calculating big factorials ;)