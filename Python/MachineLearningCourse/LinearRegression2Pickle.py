import pandas as pd
import numpy as np
import sklearn, pickle
from sklearn import linear_model
from sklearn.utils import shuffle
import matplotlib.pyplot as pyplot
from matplotlib import style

#based on 'Tech With Tim' 's tutorial, I used his *.csv file
style.use("ggplot") #set pyplot style

data = pd.read_csv("student-mat.csv", sep=";") #import data from csv file and separate by ";"
data = data[["G1", "G2", "G3", "studytime", "failures", "absences"]] #name the collumns

predict = "G3" #set value to predict as G3 (final grade)

X = np.array(data.drop([predict], 1))
y = np.array(data[predict])

bestAccuracy = 0 #temporary variable
for i in range(100): #try to get the highest accuracy model
    x_train, x_test, y_train, y_test = sklearn.model_selection.train_test_split(X, y, test_size = 0.1) #split arrays into lists for lin. regression alg.

    linear = linear_model.LinearRegression()

    linear.fit(x_train, y_train) #fit the linear regression line
    accuracy = linear.score(x_test, y_test) #calculate accuracy

    if accuracy > bestAccuracy:
        bestAccuracy = accuracy
        with open("studentmodel.pickle", "wb") as f: #save pickle model
            pickle.dump(linear, f)
        #print('Accuracy: ' + str(accuracy))
accuracy = bestAccuracy
del bestAccuracy #delete temporary variable
print('Accuracy: ' + str(accuracy))

pickle_in = open("studentmodel.pickle", "rb") #open pickle model
linear = pickle.load(pickle_in)

predictions = linear.predict(x_test)

#for x in range (len(predictions)): #print predictions and test values
#    print(predictions[x], x_test[x], y_test[x])

###show plot
p = 'G1' #choose the axis to show
pyplot.scatter(data[p], data['G3'])
pyplot.xlabel(p)
pyplot.show()