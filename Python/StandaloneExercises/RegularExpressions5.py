import re

message = "Secret Agent Bond works incognito as a Secret Agent Rob"
print('SECRET MESSAGE:   ' + message)

namesRegex = re.compile(r'Secret Agent \w+')
agents = namesRegex.findall(message)
print('OPERATION PYTHON SECRET AGENTS:')
print(agents)

print('MESSAGE:   ' + namesRegex.sub('SECRET AGENT',message))

print('\n===RETRANSMITION===\n')

namesRegex = re.compile((r'Secret Agent (\w)\w*'))
agents = namesRegex.findall(message)
print(agents)
print('MESSAGE:   ' + namesRegex.sub(r'Agent \1*****',message))

print('=========================')
print('\nPhone Exercise (again):\n')

phoneRegex = re.compile(r'''
(\+\d\d-)? #area code
(\d\d\d-\d\d\d-\d\d\d) #actual phone number
''', re.VERBOSE)

print(phoneRegex.search('000-000-000'))
print(phoneRegex.search('+48-000-000-000'))
print(phoneRegex.search('+48'))