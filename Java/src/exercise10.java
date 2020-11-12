public class exercise10
{
    public static void main(String[] args)
    {
        int[] arr1 = {7, 8, 2, 7, 9, 331, 4};
        int[] arr2 = {2, 1, 2, 8, 90};

        int len1 = arr1.length;
        int len2 = arr2.length;

        int[] arr3 = new int[len1+len2];

        alternateMerge(arr1, arr2, len1, len2, arr3);

        System.out.println("Array after merging");
        for (int i = 0; i < len1 + len2; i++)
            System.out.print( arr3[i] + " ");
    }


    static void alternateMerge(int[] arr1, int[] arr2, int len1, int len2, int[] arr3)
    {
        int i = 0, j = 0, k = 0;

        while (i < len1 && j < len2)
        {
            arr3[k++] = arr1[i++];
            arr3[k++] = arr2[j++];
        }

        while (i < len1) //arr1 remains
            arr3[k++] = arr1[i++];

        while (j < len2) //arr2 remains
            arr3[k++] = arr2[j++];
    }
}
