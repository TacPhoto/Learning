public class MergeSort
{
    public static void main(String[] args)
    {
        int[] arr = {7, 3, 6, 2, 1, 0, 9, 3};

        printArray(arr);
        System.out.println("---------------");

        arr = mergeSort(arr);
        printArray(arr);
    }


    public static void printArray(int[] arr)
    {
        for(int i: arr)
        {
            System.out.print(i + " ");
        }
        System.out.println();
    }


    public static int[] mergeSort(int[] arr) {
        if (arr.length <= 1)
            return arr;

        int midpoint = arr.length / 2;

        int[] left = new int[midpoint];
        int[] right;

        if (arr.length % 2 == 0)
            right = new int[midpoint];
        else
            right = new int[midpoint + 1];

        { //populate new arrays
            for (int i = 0; i < midpoint; i++) {
                left[i] = arr[i];
            }

            for (int j = 0; j < right.length; j++) {
                right[j] = arr[midpoint + j];
            }
        }

        int[] result = new int[arr.length];

        left = mergeSort(left);
        right = mergeSort(right);

        return merge(left, right);
    }


    private static int[] merge(int[] left, int[] right)
    {
        int[] result = new int[left.length + right.length];
        int leftPointer = 0, rightPointer = 0, resultPointer = 0;

        while(leftPointer < left.length || rightPointer < right.length)
        {
            if(leftPointer < left.length && rightPointer < right.length)
                if(left[leftPointer] < right[rightPointer])
                    result[resultPointer++] = left[leftPointer++];
                else
                    result[resultPointer++] = right[rightPointer++];
            else if(leftPointer < left.length)
                result[resultPointer++] = left[leftPointer++];
            else if(rightPointer < right.length)
                result[resultPointer++] = right[rightPointer++];
        }
        return result;
    }
}
