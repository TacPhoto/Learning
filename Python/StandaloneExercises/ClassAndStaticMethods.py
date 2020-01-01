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

    @classmethod
    def set_someVal(cls, value):
        cls.someVal = value

    @classmethod
    def from_string(cls, params):
        first, second, name = params.split('-')
        return cls(first, second, name)

    @staticmethod
    def is_odd(num):
        if int(num) % 2 == 1:
            return True
        return False


print("Before modification:")
obj1 = SomeClass('4', .5, 'Me')
obj1.showSomeVal()
print("   Run class method from the instance")
print("After:")
obj1.someVal = 4 #will remain in instance
obj1.set_someVal(40)
obj1.showSomeVal()

print("    Run class methon on the class")
obj2 = SomeClass('4', .5, 'Me')
obj1.someVal = 4 #will NOT remain in instance
SomeClass.set_someVal(6)
obj2.showSomeVal()

print("----------------------------")
objAlt = SomeClass.from_string("4-5.5-name")
print("Object made with alternative constructor.second: " + str(objAlt.second))
print(objAlt.is_odd(5))