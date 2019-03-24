import pandas as pd
import quandl

df = quandl.get('WIKI/GOOGL') #import quandl data
#print(df.head())
df = df[['Adj. Open',  'Adj. High',  'Adj. Low',  'Adj. Close', 'Adj. Volume']] #name columns
df['HL_PCT'] = ( df['Adj. High'] - df['Adj. Low'] ) / df['Adj. Close'] * 100.0 #define HL_PCT column
df['PCT_change'] = ( df['Adj. Close'] - df['Adj. Open'] ) / df['Adj. Open'] * 100.00 #define PCT_change column

df = df[['Adj. Close', 'HL_PCT','PCT_change', 'Adj. Volume']] #define columns we need again

print(df.head()) #print