base = "That is a sample TEXT! Even got a number PI: 3.14!"
german = "Spaﬂ"

print(base)
print(base.capitalize())
print(base.center(40)) #will not affect the string, too low center() parameter
print(base.center(80))
print(base.center(120))
print(base.casefold())
print(german)
print(german.casefold())
print("\'is\' occurs {0} times in the \'base\' text!".format(base.count("is")))
print("Substring 'sample' on position no ", base.find('sample'))

print("Substring 'a' on position no ", base.find('a', 1))
print("Substring 'a' on position no ", base.find('a', 3))
print("Substring 'a' on position no ", base.find('a', (base.find('a', 1) + 1)))
print("Substring 'a' on index ", base.index('a'))

print(base.join(german))
print(base.upper())
print(base.lower())
print("*" + base.rjust(100))
print(base.ljust(100) + "*")
