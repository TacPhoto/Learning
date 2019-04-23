class Rectangle:
    def __init__(self, width, height):
        self.width = width
        self.height = height

    def area(self):
        return self.width * self.height

    @classmethod
    def new_square(cls, side_length):
        return cls(side_length, side_length)


class num:
    @staticmethod
    def add(a, b):
        return a + b
    
    
square = Rectangle.new_square(5)
print(square.area())

print(num.add(4, 5))