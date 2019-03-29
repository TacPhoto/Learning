import pandas as pd
import sklearn
from sklearn import svm, metrics
from sklearn import datasets

cancer = datasets.load_breast_cancer() #get data
#print(cancer.feature_names) #test
#print(cancer.target_names) #test

x = cancer.data #set x
y = cancer.target #set y

x_train, x_test, y_train, y_test = sklearn.model_selection.train_test_split(x, y, test_size=0.2)
#print(x_train)

classes = ['malignant', 'benign']

clf = svm.SVC(kernel = "linear", C = 2) #classifier, remember: C is for the margin
clf.fit(x_train, y_train) #feed the classifier with data

y_pred = clf.predict(x_test)

accuracy = metrics.accuracy_score(y_test, y_pred) #calculate accuracy
print('Accuracy:', accuracy)
print('---pred---')
print(y_pred[::1])
print('---test---')
print(y_test[::1])