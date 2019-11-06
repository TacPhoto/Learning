import java.util.Scanner;

public class exercise6
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);

        int len = scan.nextInt();
        len = (len % 2) == 0 ? len + 1 : len;

        int[] arr = new int[len];

        for(int i = 0; i < len; i++) //prints a cross
        {
            for(int j = 0; j < len; j++)
            {
                if(i == len /2 | j == len / 2)
                    System.out.print("*");
                else
                    System.out.print(" ");
            }
            System.out.println();
        }

    }
}
