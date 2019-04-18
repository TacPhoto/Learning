class Spam:
    __egg = 4
    def print__egg(self):
        print(self.__egg)

s = Spam()

s.print__egg()
print(s._Spam__egg)
print(s.print__egg)