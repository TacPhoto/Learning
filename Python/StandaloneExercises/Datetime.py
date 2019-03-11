from datetime import datetime

weekDays = ("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday") #define days of the week

def factorial(n):
    if n > 100000:
        print("It is a bad idea to calculate the factorial of such a big number")
    elif n < 0:
        print("Negative number! Cannot calculate a factorial.")
    elif n == 0:
        print("Factorial = 1")
    else:
        fact = 1
        for i in range( 1 , n + 1 ):
            fact = fact * i;

date = datetime(1999, 8, 26, 23, 12, 34) #example birth date

print(date) #print date
print(date.year) #print year from the date
print( weekDays[ date.weekday() ] ) #print day of the week from the date

today = datetime.now() #current time

print("Time passed between the 'date' and today:", today - date)

i = 100000 #a number to calculate a factorial of
factorial(i)#needed to consume some time

print( "Calculated a factorial of", i , "in time of", datetime.now() - today)


parsed_date = datetime.strptime("20 Jan, 2017", "%d %b, %Y") #parse date from a string
print(parsed_date)
date_string = datetime.now().strftime("%m/%d/%Y, %H:%M:%S") #parse todays date to string
print(date_string)