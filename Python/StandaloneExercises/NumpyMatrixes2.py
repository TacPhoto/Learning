import numpy

arr = numpy.array([
                 [1, 2, 3, 4],
                  [10, 20, 30, 40],
                  [100, 200, 300, 400],
                    [1000, 2000, 3000, 4000]
                   ])
print('Standard deviation: ' + str(numpy.std(arr)))

print(type(arr))
print(arr.dtype)

arrTwo = 2 * numpy.array([
                 [1, 2, 3, 4],
                  [10, 20, 30, 40],
                  [100, 200, 300, 400],
                    [1000, 2000, 3000, 4000]
                   ])
print(arrTwo)
arrAdd = arr + arrTwo
print(arrAdd)

arrAnother = numpy.add(arrAdd, arrTwo, dtype = numpy.float32)
print(arrAnother)

print( (numpy.subtract(arrAnother, arr, dtype = numpy.float32).astype(int)) )
print(numpy.multiply(arr, arrTwo, dtype = int))
print('arr and arrTwo product:\n' + str(numpy.matmul(arr, arrTwo)))

print('Transposed arr:\n')
print(arr.transpose())

print('Dot product of arr and arrTwo:')
print(numpy.dot(arr, arrTwo))

print('Inner product of arr and arrTwo')
print(numpy.inner(arr, arrTwo))

print('Tensor dot product of arr and arrTwo')
print(numpy.tensordot(arr, arrTwo))

print('Power of arr (^2):')
print(numpy.linalg.matrix_power(arr, 2))

print('Kroenecker product of arr and arrTwo:')
print(numpy.kron(arr, arrTwo))

print('srr matrix norm:')
print(numpy.linalg.norm(arr))

print('arr matrix conditional number:')
print(numpy.linalg.cond(arr))

print('arr matrix conditional det:')
print(numpy.linalg.det(arr))

print('arr matrix rank:')
print(numpy.linalg.matrix_rank(arr))

print('arr matrix trace:')
print(numpy.trace(arr))