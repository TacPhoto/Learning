import pyperclip

print('''Hey, Look!
We've gotta a
multiline string
right here!''')
print('---------------------------------------------------------------')
print('Hello World'.startswith('Hello'))
print('Hello World'.endswith('World'))

print(' '.join(['Hello', 'Year', '2019']))

print('---------------------------------------------------------------')
string = 'My*name*is*some*name'
newOne = string.split("*")
print(string)
print(newOne)
print(list(newOne))
del string, newOne
print('---------------------------------------------------------------')

print('       text         ')
print('       text         '.strip())
print('       text         '.lstrip())
print('       text         '.rstrip())
print('****/*** text****')
print('****/*** text****'.strip('*'))
print('---------------------------------------------------------------')

print('Some Text To Play With'.replace('Text', 'Code'))
print('---------------------------------------------------------------')

pyperclip.copy('Hello!')
#pyperclip.paste()