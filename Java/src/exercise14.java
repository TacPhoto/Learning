public class exercise14
{
    public static void main(String[] args)
    {
        int[][] arr = new int[25][25];

        for(int i = 0; i < arr.length; i++)
            for(int j = 0; j < arr[i].length; j++)
                arr[i][j] = (int)(Math.random() * 2);

        show(arr);

        int[] result = secondCheck(arr, arr.length);

        if(result != null)
        {
            System.out.println("Col: " + result[0] + "   Row: " + result[1] + "   Size: " + result[2]);
        }
        else
            System.out.println("NOT FOUND");
    }


    static boolean firstCheck(int[][] arr, int firstCol, int fistRow, int size)
    {
        for(int col = firstCol; col < size + firstCol; col++)
            for(int row = fistRow; row < size + fistRow; row++)
            {
                if( ( (row - fistRow) > (col - fistRow) )    &&    ( arr[col][row] == 0 ))
                    return false;
                if( ( (row - fistRow) < (col - fistRow) )    &&    ( arr[col][row] == 1 ))
                    return false;
            }

        return true;
    }


    static int[] secondCheck(int[][] arr, int size)
    {
        if(size < 3)
            return null;

        int[] result = new int[3];

        for(int col = 0; col <= arr.length - size; col++)
            for(int row = 0; row < arr.length - size; row++)
                if(firstCheck(arr, col, row ,size))
                {
                    result[0] = col;
                    result[1] = row;
                    result[2] = size;

                    return result;
                }

        return secondCheck(arr, size - 1);
    }


    static void show(int[][] arr)
    {
        for(int col = 0; col < arr.length; col++)
        {
            for (int row = 0; row < arr[col].length; row++)
                System.out.print(arr[col][row] + " ");

            System.out.println();
        }

        System.out.println();
    }
}
