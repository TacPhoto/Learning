public class exercise7
{
    public static void main(String[] args)
    {
        int len = 5;
        len = (len % 2) == 0 ? len : len + 1;
        int[] arr = new int[len];

        for(int i = 0; i < len ; i++)
        {
            for(int j = 1; j < len; j++)
            {
                if((j == len - i || i == j) )
                    System.out.print("*");
                else
                    System.out.print(" ");
            }
            System.out.println();
        }
    }
}
