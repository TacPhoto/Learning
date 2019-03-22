import pandas as pd
import quandl, math
import numpy as np
from sklearn import preprocessing, svm
from sklearn.linear_model import LinearRegression
from sklearn.model_selection import train_test_split

###get data
df = quandl.get('WIKI/GOOGL') #import quandl data, df stands for 'data frame'

#prepare downloaded data
df = df[['Adj. Open', 'Adj. High', 'Adj. Low', 'Adj. Close',  'Adj. Volume']] #name columns
df['HL_PCT'] = (df['Adj. High'] - df['Adj. Close']) / df['Adj. Close'] * 100 #define HL_PCT column
df['PCT_change'] = (df['Adj. Close'] - df['Adj. Open']) / df['Adj. Open'] * 100 #define PCT_change column

df = df[['Adj. Close', 'HL_PCT', 'PCT_change', 'Adj. Volume']] #define columns we need again

###set data to create forecast of
forecast_col = 'Adj. Close'
df.fillna(-.99999, inplace=True)
forecast_out = int(math.ceil(0.01*len(df)))

###set a timeframe to predict
df['label'] = df[forecast_col].shift(-forecast_out)
df.dropna(inplace=True)

###create data array using numpy
X = np.array(df.drop(['label'], 1))
Y = np.array(df['label'])
X = preprocessing.scale(X)
Y = np.array(df['label'])

###create classifier
X_train, X_test, Y_train,Y_test = train_test_split(X, Y, test_size=0.2)

clf = LinearRegression()
clf.fit(X_train, Y_train)

###check acurracy
accuracy = clf.score(X_test, Y_test)
print('Number of days predicted: ' + str(forecast_out))
print(accuracy)