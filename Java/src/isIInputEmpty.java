import java.util.Scanner;

public class isIInputEmpty
{
    public static void main(String[] args)
    {
        String username = "username";
        System.out.print(input(username));
    }
    public static String input(String input_text)
    {
        Scanner scan = new Scanner(System.in);

        String s = scan.nextLine().toLowerCase().trim();

        if(s.isEmpty())
        {
            System.out.println("Error: " + input_text + " can't be empty.");
            return input(input_text);
        }
        return s;
    }
}