import java.util.Scanner;

public class PascalTriangleJagged
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter triangle length");
        int len = scan.nextInt(); scan.close();

        int[][] arr = createPascalArray(len);
        arr = fillPascalArray(arr);

        printJaggedArray(arr);
    }


    public static int[][] createPascalArray(int len)
    {
        int arr[][] = new int[len][];

        for (int i=0; i<arr.length; i++)
            arr[i] = new int[i+1];

        for (int i=0; i<arr.length; i++)
            for(int j=0; j<arr[i].length; j++)
                arr[i][j] = 0;

        return arr;
    }


    public static int[][] fillPascalArray(int arr[][])
    {
        for(int row = 0; row < arr.length - 1; row++)
        {
            int var = 1;

            for(int col = 0; col < row; col++)
            {
                arr[row + 1][col] += arr[row][col];
                arr[row + 1][col + 1] += arr[row][col];
            }
        }

        return arr;
    }


    public static void printJaggedArray(int arr[][])
    {
        for(int i = 0; i < arr.length; i++)
        {
            for(int j = 0; j < arr[i].length; j++)
                System.out.print(arr[i][j]);
            System.out.println();
        }
    }
}
