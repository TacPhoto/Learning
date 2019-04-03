import pandas as pd
import quandl, math, datetime
import numpy as np
from sklearn import preprocessing, svm
from sklearn.linear_model import LinearRegression
from sklearn.model_selection import train_test_split
import matplotlib.pyplot as plt
from matplotlib import style

style.use('ggplot') #set pyplot display style

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
forecast_out = int(math.ceil(0.1*len(df)))

###set a timeframe to predict
df['label'] = df[forecast_col].shift(-forecast_out)

###create data array using numpy
X = np.array(df.drop(['label'], 1))
X = preprocessing.scale(X)
X_lately = X[-forecast_out:]
X = X[:-forecast_out]
df.dropna(inplace=True)
Y = np.array(df['label'])

###create classifier
X_train, X_test, Y_train,Y_test = train_test_split(X, Y, test_size=0.2)

clf = LinearRegression()
clf.fit(X_train, Y_train)

###check acurracy
accuracy = clf.score(X_test, Y_test)

###predict based on X data
forecast_set = clf.predict(X_lately)

###new, empty column
df['Forecast'] = np.nan

###set basic datetime data
last_date = df.iloc[-1].name
last_unix = last_date.timestamp()
one_day = 86400
next_unix = last_unix + one_day

###assign dates
for i in forecast_set:
    next_date = datetime.datetime.fromtimestamp(next_unix)
    next_unix += one_day
    df.loc[next_date] = [np.nan for _ in range(len(df.columns) - 1)] + [i]

###set pyplot display settings
df['Adj. Close'].plot()
df['Forecast'].plot()
plt.legend(loc=4)
plt.xlabel('Date')
plt.ylabel('Price')

####Display plot
plt.show()