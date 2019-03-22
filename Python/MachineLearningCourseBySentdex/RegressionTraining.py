import pandas as pd
import quandl, math
import numpy as np
from sklearn import preprocessing, svm
from sklearn.linear_model import LinearRegression
from sklearn.model_selection import train_test_split

###get data
df = quandl.get('WIKI/GOOGL') #import quandl data, df stands for 'data frame'
#prepare downloaded data
df = df[['Adj. Open',  'Adj. High',  'Adj. Low',  'Adj. Close', 'Adj. Volume']] #name columns
df['HL_PCT'] = ( df['Adj. High'] - df['Adj. Low'] ) / df['Adj. Close'] * 100.00 #define HL_PCT column
df['PCT_change'] = ( df['Adj. Close'] - df['Adj. Open'] ) / df['Adj. Open'] * 100.00 #define PCT_change column

df = df[['Adj. Close', 'HL_PCT','PCT_change', 'Adj. Volume']] #define columns we need again

###set data to create forecast of
forecast_col = 'Adj. Close' #specify forecast column (adjustable)
df.fillna(-.9999, inplace = True) #fill missing data

###set a timeframe to predict
forecast_out = int(math.ceil(0.01 * len(df))) #number of days out (1%% of current data frame)
df['label'] = df[forecast_col].shift(-forecast_out) #shift column in time
df.dropna(inplace = True)

###create data array using numpy
X = np.array[df.drop(['label']), 1]
Y = np.array(df['label'])
X = preprocessing.scale(X) #scale x before feeding to the classifier
Y = np.array(df['label'])

X_train, X_test, Y_train, Y_test = train_test_split(X, Y, test_size = 0.2)

###create classifier
clf = LinearRegression(n_jobs = -1) #run as many algorithm jobs as possible paralelly
clf.fit(X_train, Y_train)

###check acurracy
accuracy = clf.score(X_test, Y_test) #test clf with data he does no already know

###print the result
print('Number of days predicted: ' + str(forecast_out))
print('Acurracy: ' + str(accuracy)) #the result may vary every launch