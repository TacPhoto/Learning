def divide(a, b):
        assert b != 0, "Tried to divide by 0"
        return a/b


def divide2(a, b):
        if b == 0:
                raise AssertionError("Tried to divide by 0")
        return a/b

#assert False
assert True

print(divide(4,5))
#print(divide(4,0))

print(divide2(4,5))
print(divide2(4,0))