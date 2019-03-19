from datetime import datetime

class man:
    def __init__(self, name, learning, age, hobby): #initialize man class
        self.name = name
        self.learning = learning
        self.age = age
        self.hobby = hobby
        print('Hello')
    def does_he_like_pankaces(self):
        return True #yup, I do, everyone shall so too ;)
    def say_sth(self):
        print('What?! Leave me alone')

class maMan(man):
    def __init__(self, name, learning, age, hobby):
        self.name = name
        self.learning = learning
        self.age = age
        self.hobby = hobby
        print('Hello, I\'m back!')

    def singNowYouAreAMan(self): 
        print("""Now you're a man!
A man, man, man,
Now you're a man!
A manny, manny man,
A man, man, man,
You are now a man.
You're a man!
Now you're a man""")

me = man('Karol', 'Python', (datetime.now().year - 1999), 'Urbex')

print('About me:' + str(vars(me)))
print('Do i like pancakes? ' + str(me.does_he_like_pankaces()).replace('True','Yes').replace('False','No'))
me.say_sth()

print('\n---------------------------------------\n\n')

newMan = maMan('Karol', 'Python', (datetime.now().year - 1999), 'Urbex')
#print(newMan.name + '\n') #inheritance test
newMan.singNowYouAreAMan()