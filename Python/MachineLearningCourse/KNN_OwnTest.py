import numpy as np
from math import sqrt
from collections import Counter
import warnings
import pandas as pd
import random

def k_nearest_neighbours(data, predict, k = 3):
    if len(data) >= k:
        warnings.warn('K is set to a value less than total length of the data set')

    distances = []
    for group in data:
        for features in data[group]:
            #euclidean_distance = sqrt( (plot1[0] - plot2[0])**2  + (plot1[1] - plot2[1])**2 )
            euclidean_distance = np.linalg.norm( np.array(features) - np.array(predict) )
            distances.append([euclidean_distance, group])
    votes = [i[1] for i in sorted(distances)[:k]]
    vote_result = Counter(votes).most_common(1)[0][0]
    confidence = Counter(votes).most_common(1)[0][1] / k

    return vote_result, confidence

accuracies = []
for i in range(10):
    ###get data
    df = pd.read_csv("breast-cancer-wisconsin.data")
    df.replace('?', -99999, inplace = True)
    #df.drop(['id'], axis = 1, inplace = True)
    full_data = df.astype(float).values.tolist()

    random.shuffle(full_data) #shuffle the data

    test_size = 0.2
    train_set = {2:[], 4:[]}
    test_set = {2:[], 4:[]}
    train_data = full_data[:-int(test_size * len(full_data))]
    test_data = full_data[-int(test_size * len(full_data)):]

    ###populate dicts
    for i in train_data:
        train_set[i[-1]].append(i[:-1])
    for i in test_data:
        test_set[i[-1]].append(i[:-1])

    correct = 0 #counts correct set
    total = 0   #count total set

    for group in test_set: #count total and correct
        for data in test_set[group]:
            vote, confidence = k_nearest_neighbours(train_set, data, k = 5)
            if group == vote:
                correct += 1
            """
            else:
                print('Confidence:', confidence)
            """
            total += 1

    accuracies.append(correct/total)

print(sum(accuracies) / len(accuracies))
