list = ['e', 'x', 'a', 'm', 'p', 'l', 'e']
iter_list = iter(list)

try:
    print(next(iter_list))
    print(next(iter_list))
    print(next(iter_list))
    print(next(iter_list))
    print(next(iter_list))
    print(next(iter_list))
    print(next(iter_list))
    print(next(iter_list)) #will not run
except:
    passlist = ['s','o','r','r','y']

print(20 * "-")

list2 = [7, 54, 12, 5, 32, 99]
iter_list2 = iter(list2)

while True:
    try:
        print(iter_list2.__next__())
    except:
        print('end')
        break