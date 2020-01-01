class SomeClass:
    someVal = 3

    def __init__(self, first, second, name):
        self.first = float(first)
        self.second = float(second)
        self.name = name

    def printName(self):
        print(self.name)

    def sum(self):
        return self.first + self.second

    def showSomeVal(self):
        print("from instance:" + str(self.someVal))
        print("from class:" + str(SomeClass.someVal))
        print("from instance again:" + str(self.someVal))


print("OBJ1:")
obj1 = SomeClass('4', .5, 'Me')
obj1.showSomeVal()
print("------------------\nOBJ2:")
obj2 = SomeClass('4', .5, 'Me')
obj2.someVal = 5
obj2.showSomeVal()

#######################################################
print("----------------------------------------------")
print("ANOTHER EXAMPLE, NOTICE HOW MANY TIMES someVal WILL BE INCREMENTED")
print("----------------------------------------------")


class SomeClassButDifferent:
    someVal = 3

    def __init__(self, first, second, name):
        self.first = float(first)
        self.second = float(second)
        self.name = name

        SomeClassButDifferent.someVal += 1

    def printName(self):
        print(self.name)

    def sum(self):
        return self.first + self.second

    def showSomeVal(self):
        print("from instance:" + str(self.someVal))
        print("from class:" + str(SomeClassButDifferent.someVal))
        print("from instance again:" + str(self.someVal))


print("OBJ1:")
obj1 = SomeClassButDifferent('4', .5, 'Me')
obj1.showSomeVal()
print("------------------\nOBJ2:")
obj2 = SomeClassButDifferent('4', .5, 'Me')
obj2.someVal = 5
obj2.showSomeVal()