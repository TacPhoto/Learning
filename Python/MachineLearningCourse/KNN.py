import pandas as pd
import sklearn
from sklearn import linear_model, preprocessing
from sklearn.neighbors import KNeighborsClassifier

data = pd.read_csv("car.data") #import data

#convert non-numeric data into numeric data
pre = preprocessing.LabelEncoder()
buying = pre.fit_transform(list(data["buying"]))
maint = pre.fit_transform(list(data["maint"]))
door = pre.fit_transform(list(data["door"]))
persons = pre.fit_transform(list(data["persons"]))
lug_boot = pre.fit_transform(list(data["lug_boot"]))
safety = pre.fit_transform(list(data["safety"]))
cls = pre.fit_transform(list(data["class"]))

predict = "class"

X = list(zip(buying, maint, door, persons, lug_boot, safety))
y = list(cls)

x_train, x_test, y_train, y_test = sklearn.model_selection.train_test_split(X, y, test_size=0.1) #create train and test data

n_neighbours = 9 #n_neighboors must be an odd number, 9 seemed to work great
model = KNeighborsClassifier(n_neighbors = n_neighbours) #train

model.fit(x_train, y_train) #fit data
accuracy = model.score(x_test, y_test) #check accuracy
print('Accuracy: ' + str(accuracy))

predicted = model.predict(x_test)
names = ["unacc", "acc", "good", "vgood"] #for evaluating non-numeric phrases

for i in range(len(predicted)): #print results
    print("Predicted: ", names[predicted[i]], "Data: ", x_test[i], names[y_test[i]])
    #n = model.kneighbors([x_test[i]], n_neighbours, True)
    #print("N:", n)