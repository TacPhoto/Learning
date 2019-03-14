import copy

name = "Garfield the cat"
print(name)
print(name[4])
#name[4] = 'H' #would not work
del name

spam = [0,2,3,4,5,6]
cheese = spam
cheese[1] = 'text'
print(cheese)
print(spam)
print("spam looks exact like cheese now (see: references)")
del spam, cheese
print("====================")


def eggs(parameter):
    parameter.append('Hey Hi Hello!')

spam = [1, 2, 3]
print(spam)
eggs(spam)
print(spam)
print("The function has affected eggs as we can see. another reference example (similar to C ;) )")

spam = [1, 2, 3]
print(spam)
cheese = copy.deepcopy(spam)
cheese[1] = 5
print(cheese)
print("We managed to copy the list without using the reference! Hurray!")