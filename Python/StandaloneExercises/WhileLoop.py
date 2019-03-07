i = 1
##########
while i <= 10 :
    print(i)
    i += 1
print("Finished 1st loop")
##########
while i < 15 :
    print (i)
    if i == 12:
        break
    i += 1
print("Finished 2nd loop")
##########
i = 0
while True:
    i += 1
    if i == 2 :
        print("Skip 2 now")
        continue
    if i == 5 :
        print("STOP")
        break
    print(i)
print("Finished 3rd loop")