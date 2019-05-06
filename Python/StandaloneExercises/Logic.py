print(all([False, True, True]))
print(any([False, True, True]))
print(all([True, True, True]))
print(not all([True, True, True]))
print(all([True, True, True]))

print(20 * "-")

def NAND (a, b):
    if a == 1 and b == 1:
        return False
    else:
        return True

print(NAND(1,1))
print(NAND(1,0))
print(NAND(0,0))

print(20 * "-")

def OR (a, b):
    if a == 1 or b == 1:
        return True
    else:
        return False

print(OR(1, 1))
print(OR(1, 0))
print(OR(0, 0))

print(20 * "-")

def XOR (a, b):
    if a != b:
        return True
    else:
        return False

print(XOR(1, 1))
print(XOR(1, 0))
print(XOR(0, 0))