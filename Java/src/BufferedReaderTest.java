import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BufferedReaderTest
{
	public static void main(String[] args)
	{
		int[] tab = {1, 2, 3, 4, 5, 6};
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int index = -1;
		
		while(true)
		{
			System.out.println("Choose index");
			
			try
			{
				index = Integer.parseInt(reader.readLine());
				try
				{
					System.out.println(tab[index - 1]);
				}
				catch (ArrayIndexOutOfBoundsException e)
				{
					System.out.println("Incorrect index value. Array size is:" + tab.length);
				}
			}
			catch (NumberFormatException n)
			{
				System.out.println("Incorrect input, try again");
			}
			catch (IOException e)
			{
				System.out.println("Input error");
			}
		}
	}
}
