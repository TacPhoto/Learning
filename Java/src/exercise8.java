public class exercise8
{
    public static void main(String[] args)
    {
        int len = 11;

        for(int i = 1; i < len + 1; i += 2)
        {
            for(int j = 0; j < (len - i) / 2; j++)
                System.out.print(" ");
            for(int j = 0; j < i; j++)
                System.out.print("*");

            System.out.println();
        }
    }
}
