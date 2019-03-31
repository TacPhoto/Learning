from statistics import mean
import numpy as np

import matplotlib.pyplot as plt

def best_fit_slope_and_intercept(xs, ys):
    m = (   (   (mean(xs) * mean(ys)) - mean(xs*ys)   ) /
        (       (mean(xs)*mean(xs)) - mean(xs**2)     )     )
    b = mean(ys) - m * mean(xs)
    return m, b

plt.style.use('ggplot') #set plot style

#set example data
xs = np.array([1,2,3,4,5,6], dtype = np.float64)
ys = np.array([5,4,6,5,6,7], dtype = np.float64)

#calcucale reg line and slope
m, b = best_fit_slope_and_intercept(xs, ys)
regression_line = [(m * x) + b for x in xs]

#predict
predict_x = 8
predict_y = (m * predict_x) + b

print("m = ", m)

#draw plot
plt.scatter(xs, ys) #display x and y data
plt.scatter(predict_x, predict_y, color = 'g') #display predicion
plt.plot(xs, regression_line) #display reg line
plt.show()