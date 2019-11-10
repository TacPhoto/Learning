import java.util.Random;

public class exercise9
{
    private static Object RandomStringUtils;

    public static void main(String[] args)
    {
        java.util.Scanner in = new java.util.Scanner(System.in); //given line of code, cannot be changed
        System.out.println("Enter length");
        int len = in.nextInt();

        char[] arr = new char[len];

        String chars = "qwertyuiopasdfghjklzxcvbnm";
        Random random = new Random();

        for(int i = 0; i < len; i++)
        {
            arr[i] = chars.charAt((random.nextInt(chars.length())));
            System.out.println(arr[i]);
        }

        boolean flag = true;
        while(flag)
        {
            flag = false;
            System.out.print("Enter a letter you want to get rid of");
            char c=in.next() .charAt(0); //given line of code, cannot be changed

            for(int i = 0; i < len; i++)
                if(arr[i] == c)
                {
                    arr[i] = 0;
                }

            for (int i = 0; i < len; i++)
                if (arr[i] != 0)
                    flag = true;
        }
    }
}
