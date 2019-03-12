def divide(divisor):
    try:
        print(100 / divisor)
        return
    except ZeroDivisionError:
        print("You tried to divide by zero, try again")
        return

stop = False
while(stop != True):
    choice = str(input("Hey, input the number to divide 100 by\n(Enter B to exit)"))
    if choice == "B":
        stop = True
    elif choice.isnumeric():
        divide(float(choice))
    else:
        print("You are allowed to type float, integer or 'B' only")
    print("************************************************************************")