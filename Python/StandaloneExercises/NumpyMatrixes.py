import numpy

arr = numpy.array([
                 [1, 2, 3, 4],
                  [10, 20, 30, 40],
                  [100, 200, 300, 400],
                    [1000, 2000, 3000, 4000]
                   ])
print(arr)
print('Arr shape: ' + str(arr.shape))
print(arr[0,])
print(arr[0,3])

print('Extracting a submatrix')

subarr = arr[1:3,1:] #rows, columns (first:last, first:last)
print(subarr)
print('------------------------------------------')

I = numpy.eye(3)
print(I)
O = numpy.zeros((4,4))
print(O)
ones = numpy.ones((3,3))
print(ones)
full = numpy.full((2,4), 3)
print(full)
X  = numpy.random.random((2,3))
print(X)
print('------------------------------------------')
print('X Mean: ' + str(numpy.mean(X)))
