# Sorting

## Exercises (oldest to newest):
1.BubbleSort.c

2.BubbleSortClassic.c

3.QuickSort.c

4.BubbleSortClassicOptimized.c

Please note:
    In file BubbleSort.c I have not made typical 'for untill j<n' loop containing sorting loop, I have used a sorting loop and a test if the array is sorted.
   Why? The first one is like (sort iteration)^2 and the second is like (sort iteration)(test) which in my case takes less lines of code to execute and may often quicker finish sorting.
    BubbleSortClassicOptimized.c is optimized by escaping from the loop if none of the variables were swapped during single sorting iteration.

## Proces execution time:

QuickSort.c                   - 0.02129 seconds

BubbleSortClassicOptimized.c  - 0.03205

BubbleSort.c                  - 0.03626 seconds

BubbleSortClassic.c           - 0.03751 seconds

Array used: { 17, 10, 0, 33, 5, 99, 22, 104, 40, 3})


Please note: it was the process execution time measured by automatic default DevCpp compiler function. I did not measure sorting part only and I did not use time.h library. If you want to make more scientific measurements you shoult use time.h library, kill all unnecesary tasks on your computer, make many tests and calculate the average times.
Typically QuickSort.c should be WAY FASTER than BubbleSort.c or BubbleSortCLassic.c
