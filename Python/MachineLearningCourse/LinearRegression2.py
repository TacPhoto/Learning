import pandas as pd
import numpy as np
import sklearn
from sklearn import linear_model
from sklearn.utils import shuffle

#based on 'Tech With Tim' 's tutorial, I used his *.csv file

data = pd.read_csv("student-mat.csv", sep=";") #import data from csv file and separate by ";"
data = data[["G1", "G2", "G3", "studytime", "failures", "absences"]] #name the collumns

predict = "G3" #set value to predict as G3 (final grade)

X = np.array(data.drop([predict], 1))
y = np.array(data[predict])

x_train, x_test, y_train, y_test = sklearn.model_selection.train_test_split(X, y, test_size = 0.1) #split arrays into lists for lin. regression alg.

linear = linear_model.LinearRegression()

linear.fit(x_train, y_train) #fit the linear regression line
accuracy = linear.score(x_test, y_test) #calculate accuracy
print('Accuracy: ' + str(accuracy))

print('Coefficient: \n', linear.coef_)
print('Intercept: \n', linear.intercept_)
print('------------------------------')

predictions = linear.predict(x_test)

for x in range (len(predictions)): #print predictions and test values
    print(predictions[x], x_test[x], y_test[x])