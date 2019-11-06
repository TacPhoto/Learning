public class exercise9
{
    public static void main(String[] args)
    {
        int len = 11;

        for(int i = j; i < len + 1; i += 2)
        {
            for(int j = 0; j < len; j++)
                if( j < (len - 1) / 2 && j != i )
                    System.out.print(" ");
                System.out.print("*");

            System.out.println();
        }
    }
}
