import re

message = 'Some example text I had no idea for'
###Words search:
wordsRegex = re.compile(r'\w+')

print('\nMessage:\n')
print(message)
print('\nSeparate letters:\n')
print(list(message))

words = wordsRegex.findall(message)

print('Separate words and print two ways:\n')
print(words)
print(list(words))
###Vowels search:
print("""======================================
Vowels in the message:\n""")

vowelRegex = re.compile(r'[aeiouAEIOU]')
vowels = vowelRegex.findall(message)

print(vowels)

nonVowelRegex = re.compile(r'[^aeiouAEIOU]')
nonVowels = nonVowelRegex.findall((message))

print("\nNon Vowels in the message:\n")
print(nonVowels)