string = 'some text'
print(string)
print(id(string))
print('Run fuction, then print string and it\'s id again without using the function')
def changeString():
    string = 'changed'
    print(string)
    print(id(string))

changeString()
print(string)
print(id(string))

print('(Redeclaring function)')
print('Run fuction, then print string and it\'s id again without using the function')
def changeString():
    string = 'changed'
    print(string)
    print(id(string))
    return string

changeString()
print(string)
print(id(string))
print('-----------------------------')

arr = [1, 2, 3]
print(arr)
def changeArr():
    arr = [6, 7, 8]
    print(arr)

changeArr()
print(arr)
print('-----------------------------')

print(arr)
def changeArr():
    arr[0] = 6
    arr[1] = 7
    arr[2] = 8
    print(arr)

changeArr()
print(arr)
print('-----------------------------')

arr = [1, 2, 3]
print(arr)
print(id(arr))
def changeArr():
    global arr
    arr = [6, 7, 8]
    print(arr)

changeArr()
print(arr) #this time the arr got modified
print(id(arr)) #arr also got a new adress