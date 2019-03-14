import pprint

dict = {'first': 'thing', 'second': 'something', 'third': 'pancake!'}

for k in dict.keys():
    print(k)
print("=============")
for v in dict.values():
    print(v)
print("=============")
for k, v in dict.items():
    print(k, v)
print("=============")
print('How \'bout checking if a value or a key is present in the given list?')
print('first' in dict.values())
print('first' in dict.keys())
print('thing' in dict.values())
print('thing' in dict.keys())
print("=============")
print('Let\'s experiment with the get method')
print( dict.get('first', 'Not found') )
print( dict.get('fifth', 'Not found') )
print("=============")

print('Another exercise:')
if 'fourth' not in dict:
    dict['fourth'] = 'Horseman'
print(dict)
dict.setdefault('fifth', 'placeholder')
dict.setdefault('first', 'placeholder') #will not affect the dict
print(dict)
print("=============")
######
print('Let\'s count number of specific characters in the message below')
string = 'A horseman has risen his head and looked straight into the eyes of Spongebob The Mighty. Spongebob acted unconcerned'
print(string)

count = {}

for character in string.upper():
    count.setdefault(character, 0)
    count[character] += 1

print( pprint.pformat(count) ) #works same as pprint.pprint(count)
######