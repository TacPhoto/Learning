from datetime import datetime

current_time = datetime.now()
print("Time: ", current_time)

import random

random_list = [random.randint(1,101) for i in range(101)]

print("Random number:", random.choice(random_list),"***********")
print("More random numbers (range 1,999):")

for i in range(10):
	print(random.randint(1,999))
