words = ["Hello", "That", "Is", "A", "Test", "List"]
i = 0
n = 6

while i < n :
    print(words[i])
    i += 1

del words

print("***************")

things = ["test", 0, 3, 4]
i = 0
while i < 4:
    print(things[i])
    i += 1
print("***************")
print(things[2] * things[3])

print("***************")

str = "Some Text"
n = len(str)

i = 0
while i < n:
    print(str[i])
    i += 1