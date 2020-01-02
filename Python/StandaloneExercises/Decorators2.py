class Real_pizza:
    def __init__(self, toppings):
        self.toppings = toppings
        self._is_discount = False
        for i in range (len(toppings)):
            if toppings[i] == "pineapple":
                print("Not this time!")
                toppings[i] = "salami"

    @property
    def is_discount(self):
        return self._is_discount

    @property
    def hawaiian(self):
        print("You madman!")
        return False

    @is_discount.setter
    def is_discount(self, bool):
        if bool == True:
            self._is_discount = True

    @is_discount.deleter
    def is_discount(self):
        print("is_discount deleted")
        self.is_discount = None

pizza = Real_pizza(["cheese", "pepperoni"])
print("-" * 10)

pizza.hawaiian
hawaiian = Real_pizza(["cheese", "pineapple", "ham"])
print(hawaiian.toppings)

print("-" * 10)
print(hawaiian.is_discount)
hawaiian.is_discount = True
print(hawaiian.is_discount)

del hawaiian.is_discount