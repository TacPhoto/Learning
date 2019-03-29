import pandas as pd
import sklearn
from sklearn import svm
from sklearn import datasets
#########TO BE DEVELOPED ;)
cancer = datasets.load_breast_cancer() #get data

print(cancer.feature_names)
print(cancer.target_names)

x = cancer.data #set x
y = cancer.target #set y

x_train, x_test, y_train, y_test = sklearn.model_selection.train_test_split(x, y, test_size=0.2)
#print(x_train)

classes = ['malignant', 'benign']