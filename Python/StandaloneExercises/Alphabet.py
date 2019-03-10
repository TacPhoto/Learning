print("Let's print the alphabet!")

x = 0
for i in range (65, 91): #ascii alpha characters range
    letter = chr(i)
    x += 1
    tmp = letter + " => " + letter.lower() #uses both upper and lower letters
    if i >65 and x % 5 == 0: #sets the length of a single line to 5 strings
        x = 0
        tmp += "\n" 
    print(tmp, end = " ") #prints the letters
    
###based on https://python101.readthedocs.io/pl/latest/podstawy/przyklady/przyklad02.html