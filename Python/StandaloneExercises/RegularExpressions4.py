import re

beginsWithHelloRegex = re.compile(r'^Hello')
print(beginsWithHelloRegex.search('Hello there!'))
print(beginsWithHelloRegex.search('Hey! Hi! Hello!'))

print('=====================================================================')

endsWithHelloRegex = re.compile(r'Hello!$')
print(endsWithHelloRegex.search('Hello there!'))
print(endsWithHelloRegex.search('Hey! Hi! Hello!'))

print('=====================================================================')

allDigitsRegex = re.compile((r'^\d+$'))
print(allDigitsRegex.search('2787683242'))
print(allDigitsRegex.search('324jfsno324432'))

print('=====================================================================')

atRegex = re.compile(r'(\w*?.at)')
at = atRegex.findall('The Pat with no hat said he wants A black cat. Test words: flat jubilat')
print("Words ending with 'at' in the 'The Pat with no hat said he wants black cat. Test words: flat jubilat' poem")
print(at)

print('=====================================================================')

message = """A long message
just to make some
multiline text for a regex test"""

print(message)

dotStar = re.compile(r'.*', re.DOTALL)
print(dotStar.search(message))
print(dotStar.search(message))

print('=====================================================================')

vowelRegex = re.compile(r'[[aeiou]', re.I)
print(vowelRegex.findall('Some TEXT with uppeRcAsE and LowerCase letterS'))
