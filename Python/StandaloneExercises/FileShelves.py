import os, shelve

path = os.path.join('D:', 'file2')
path = 'D:\\file2'

shelfFile = shelve.open(path)

shelfFile['Food'] = ['Spam', 'Bacon', 'Cheese', 'Eggs','Sausage']
shelfFile['Monty'] = ['Python']
shelfFile.close()

shelfFile = shelve.open((path))

print(shelfFile)
print(list(shelfFile))

print('Food:')
print(shelfFile['Food'])
print('Monty:')
print(shelfFile['Monty'])

print('---------')
print(shelfFile.keys())
print(list(shelfFile.keys()))
print(shelfFile.values())

shelfFile.close()
