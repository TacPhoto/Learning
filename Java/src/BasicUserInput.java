import java.util.Scanner;

public class BasicUserInput
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter an integer");
		int num = scan.nextInt();
	
		System.out.println(num + " awesome!");
		////
		System.out.println("Enter a float");
		float num2 = scan.nextFloat();
	
		System.out.println(num2 + " awesome!");
	}
}
