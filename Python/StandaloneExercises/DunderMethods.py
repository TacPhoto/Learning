class Something:

    val = 30

    def __init__(self, text1, text2, value):
        self.text1 = text1
        self.text2 = text2
        self.string = text1 + '.' + text2 + 'blahblahblah'
        self.value = value

    def fullname(self):
        return '{} {}'.format(self.text1, self.text2)

    def add_ten(self):
        self.value = int(self.value) + 10

    def __repr__(self):
        return "Content:('{}', '{}', {})".format(self.text1, self.text2, self.value)

    def __str__(self):
        return '{} - {}'.format(self.fullname(), self.string)

    def __add__(self, other):
        return self.value + other.value

    def __len__(self):
        return len(self.fullname())


obj1 = Something('You', 'Are ', 1000)
obj2 = Something('Man', ' man man!', 2000)

print(obj1 + obj2)
print(len(obj1))