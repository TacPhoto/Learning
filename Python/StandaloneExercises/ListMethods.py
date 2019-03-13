#let's test some list methods
menu = ['fries', 'burger', 'polish kielbasa', 'my old shoes', 'spinach']
print(menu)
print('Index of a burger is', menu.index('burger'))
print('Index of a burger is', menu.index('spinach'))
print("====================")

try:
    print('Index of a burger is', menu.index('ooga booga'))
except ValueError:
    print('I did not manage to find such value in the menu')
print("====================")

menu.append('kebab')
print(menu)

menu.insert(0,'chicken')
menu.insert(4,'neighbour\'s cat')
print(menu)

menu.remove('my old shoes')
print(menu)

del menu[5]
print(menu)
print("====================")

arr = [2, 6, 23, -43, 4325, 542, 1, 0, 324, -234, 32]
print(arr)
print('Sorting time!')
arr.sort()
print(arr)
print("====================")

print('I can also sort the string list alphabetically!')
print(menu)
menu.sort()
print(menu)
print('Or reverse sort!')
menu.sort(reverse=True)
print(menu)
print("====================")

anotherArr = ['Jake', 'Melissa', 'pancakes', 'weirdlongwordijustwantedtotype', 'Kyle', 'jeep', 'Peter']
print(anotherArr)
print(anotherArr.sort()) #will return 'None'
anotherArr.sort()
print(anotherArr)
print('Did you see that? It has some issues sorting ASCII-bethically')
anotherArr.sort(key = str.lower)
print(anotherArr)
print("Huh, we've fixed that :)")
print("====================")

letters = ['a', 'b', 'c', 'b', 'b', 'e', 'a', 'b']
print(letters)
print(list(reversed(letters)))
print('b occurs', letters.count('b') ,'times')
print('a occurs', letters.count('a') ,'times')
print('e occurs', letters.count('e') ,'times')
letters.pop(3)
print(letters)
print("====================")


print('arr min =', min(arr))
print('arr max =', max(arr))
anotherArr.clear()
print("cleared 'anotherArr' =", anotherArr)
print(arr, '=', sum(arr,))