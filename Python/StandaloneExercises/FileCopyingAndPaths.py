import os

print('Current working directory is:', os.getcwd())
print('Files:', os.listdir(), '\n-------------------------------------------\n')

pathOne = os.path.join('D:', 'file1.txt')

content = ''
lines = []

if os.path.exists(pathOne):
    print('Opening', pathOne)

    file = open(pathOne)
    content = file.read()
    print(content)

    file = open(pathOne)
    lines = file.readlines()
    print(lines)

    file.close()
    del file

pathTwo = os.path.join('D:', 'file2.txt')
file = open(pathTwo, 'w')
file.write(content)
file.close()

print('\n\nCheck file.txt , it should have the same content as file1.txt')