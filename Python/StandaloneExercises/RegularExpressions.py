import re #for regular expressions

message = 'Call me 000-000-000 tomorrow or 111-111-111 on Monday'
print("\nGiven text:\n\n" + message + '\n')

phoneNumRegex = re.compile(r'\d\d\d-\d\d\d-\d\d\d')
phoneNum = phoneNumRegex.findall(message)

print("Phone numbers found (Polish 9 digit format without region codes):\n")
#print(phoneNum.group()) #I've used it in phoneRegex.search(message) code version
print(phoneNum) #print results
print('----------------------------------------------------------------\n')

print("Let's try implementing region code separation, message:\n")
message = 'Call me +48-000-000-000 tomorrow or +22-111-111-111 on Monday'
print(message + '\n')
phoneNumRegex = re.compile(r'(\d\d)-(\d\d\d-\d\d\d-\d\d\d)') #no it can work with region codes
phoneNum = phoneNumRegex.findall(message)
print("Result:\n")
print(phoneNum)
print('----------------------------------------------------------------\n')