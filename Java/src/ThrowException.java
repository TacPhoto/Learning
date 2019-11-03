import java.util.Scanner;

public class ThrowException
{
	public static void main(String[] args) throws ArithmeticException
	{
		int a = 15;
		int b;
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Input b:");
		b = scan.nextInt();
		
		if( b == 0 )
		{
			throw new ArithmeticException("Divide by 0 error");
		}
		else
			System.out.println(a/(double)b);
	}
}
