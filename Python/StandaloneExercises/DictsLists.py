my_dict = {
  "Name": "Karol",
  "Learning": "Python",
  "Birth": 1999
}

print("==================", my_dict.items())

print("===================", my_dict.keys())

print("===================",my_dict.values())

for number in range(3):
    print(number)
print("===================")
for letter in "Karol":
    print(letter)
print("===================")
for key in my_dict:
    print(key, my_dict[key])
print("===================")

oddNumbers = [i for i in range (10) if i % 2 == 1]
print(oddNumbers, "\n===================")

sth = ['C' for x in range(4) if x < 3]
print(sth, "\n===================")

list = [1, 4, 3, 64, 23, 5, 7, 9]
print(list[3:6:1])
print(list[1:8:2])
list = list[::-1] #reversing list
print(list, "\n===================")
