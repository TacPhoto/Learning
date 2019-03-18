import traceback

def boxPrint(symbol, width, height): #prints a box made up of a given string
    if width <=2 or height <=2: #exception
        raise Exception("Height and width cannot be equal to 2 or less")

    print(symbol * width) #top border
    for i in range(height - 2):
        print(symbol + (' ' * (width - 2) * len(symbol)) + symbol) #left/right borders
    print(symbol * width) #bottom border

boxPrint('*', 8, 7)

boxPrint('*532h', 3, 3)
#boxPrint('*532', 1, 3) #exception test

print('\n\n')
try:
    raise Exception('Some error msg')
except:
    print(traceback.format_exc())