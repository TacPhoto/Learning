import re

###Text Regex###
message = 'Here comes the Batwoman!' #declare string to test regex on

batRegex = re.compile(r'Bat(wo)?man') #declare the regular expresion

mo = batRegex.search(message) #search for regex

try:
    print(mo.group()) #print result
except: #prevent's 'no mo.group() error
    print('Not found')

message = 'Here comes the Superman!' #redeclare the string to test regex on
mo = batRegex.search(message) #search for regex (in the updated string)

try:
    print(mo.group()) #print result
except: #prevent's 'no mo.group() error
    print('Not found')

print('---------------------------------------')
###Phone Number Regex (redesigned)###
numOne = '000-000-000'
numTwo = '+48-000-000-000'

phoneRegex = re.compile(r'(\+\d\d-)?\d\d\d-\d\d\d-\d\d\d')

print(phoneRegex.search(numOne))
print(phoneRegex.search(numTwo))
print("Let's cleanup:")
print(phoneRegex.search(numOne).group())
print(phoneRegex.search(numTwo).group())
print('---------------------------------------')

###Text Regex Updated###
batRegex = re.compile(r'Bat(wo)*man')

message = 'Here comes the Batwoman!'
print(batRegex.search(message).group())

message = 'Here comes the Batwowowoman!'
print(batRegex.search(message).group())
print('---------------------------------------')

batRegex = re.compile(r'Bat(wo)+man') #'wo' has to appear at least 1 time to  match regex

message = 'Here comes the Batman!'
try:
    print(batRegex.search(message).group()) #print result
except:
    print('Not found') #prevent 'no group' error
print('---------------------------------------')

haRegex = re.compile((r'(Ha){3}'))
print(haRegex.search('HaHa You Lost!')) #will not find the regex
print(haRegex.search('HaHaHa You Lost!'))
print(haRegex.search('HaHaHaHa You Lost!'))
print('---------------------------------------')

haRegex = re.compile((r'(Ha){2,3}'))
print(haRegex.search('Ha You Lost!')) #will not find the regex
print(haRegex.search('HaHa You Lost!'))
print(haRegex.search('HaHaHa You Lost!'))
print(haRegex.search('HaHaHaHa You Lost!'))
print('---------------------------------------')

digitRegex = re.compile((r'(\d){3,5}'))
print(digitRegex.search('123456789'))

digitRegex = re.compile((r'(\d){3,5}?'))
print(digitRegex.search('123456789'))