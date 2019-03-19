import numpy, os,  matplotlib.pyplot, pyautogui, matplotlib.image

a = numpy.zeros(3)
print(a)
a = numpy.zeros(10)
print(a)
print(a[4])
a = numpy.ones(10)
print(a)
a = numpy.linspace(2, 8, 5)
print(a)
a = numpy.array([10, 20, 25])
print('-----------------')
b = numpy.array([[1,2,3,4],[5,6,7,8]])
print(b)
print('-----------------')

numpy.random.seed(0)
a = numpy.random.randint(88, size = 6)
print(a)
print('-----------------')

scrName = 'scr.png' #set screenshot name
scrPath = os.path.join(os.getcwd(), scrName) #set screenshot path
pyautogui.screenshot(scrPath) #take a screenshot and save

scr = matplotlib.image.imread(scrPath) #open screenshot in pyplot

print('Screenshot path is: ' + str(scrPath))
print('Screenshot size is: ' + str(scr.shape))
print('***Displaying Screenshot***')

matplotlib.pyplot.imshow(scr) #set screenshot as a thing to display
matplotlib.pyplot.show() #run the display

scr_sin = numpy.sin(scr)
matplotlib.pyplot.imshow(scr_sin)#set screenshot_sin to display
matplotlib.pyplot.show() #run the display
#print(scr_sin)

scr_masked = numpy.where(scr > 210, 255, 0)
matplotlib.pyplot.imshow(scr_masked)#set masked screenshot to display
matplotlib.pyplot.show() #run the display
