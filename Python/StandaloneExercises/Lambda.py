square = lambda x: x ** 2
print( square(float(input("Enter number to calculate square of"))) )
#print( (float(input("Enter a number to calculate square of"))) ** 2 )
###
arr = [1, 6, 3, 5, 7, 23 , 34, 54, 36, 73]
###
arrEven = list(filter(lambda x: (( x % 2) == 0) , arr))
print(arrEven)
###
arrOdd = list(filter(lambda x: (( x % 2) == 1) , arr))
print(arrOdd)
###
arrTripple = list(map(lambda x: x * 3, arr))
print(arrTripple)
###
mergeText = lambda x,y : x + " " + y #concatenation using lambda
textOne = "Some text"
textTwo = "More text"

string = mergeText(textOne, textTwo)
print(string)
###