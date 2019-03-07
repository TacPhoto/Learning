from math import pi

print(1, 3, 4, "text")

print(1, 3, 4, "text", sep = "****")

print(1, 3, 4, "text", sep = "    space    ")

print(1, 3, 4, "text", sep = "_")

print('This is {0} and this is {1}' .format("first", "second"))
print('This is {1} and this is {0}' .format("first", "second"))

print('Hello {name}, my name is {myName}'.format(name = 'You', myName = 'Me'))

print("PI equals something about %.4f" %pi)
print("PI equals something about %.40f" %pi)


num = input("Enter a number")
print(3*num) #see? it treats num as a string

num = int(input("Enter a number")) #casting input to integer value
print(num*3) #see? this time num is a number indeed

num = float(input("Enter a number")) #again but let's use float this time
print(num*3)