myBool = False #it seems false/true are case sensitive
print(myBool)

print(1==4)

print("tekst" != "text")
print("text" == "tExt")

print(9>4)
print(3 == 3.0)
print(6.5 >= 5)

###
x = 7
y = 5

if x > y :
    print("if statement no 1")

###

if x == 25:
    print("Yes")
else:
    print("No")

###

if 7 == 5:
    print("Yes")
elif 4 == 4: #elif == else if
    print("Else if")

###

print( 1 == 1 and 2 == 2 )
print( 1 != 1 or 2 == 2 )
print( 1 == 0 or 1 == 2 )
print( not 5 == 7 )

print( 1 == 1 and ( 3==2 or 6 == 6 )) #operator precedence example
