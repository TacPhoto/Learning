
public class HelloWorld
{
	public static void lineSpacing()
	{
		int limit = 30;
		for(int i = 0; i < limit; i++)
		{
			System.out.print('-');
		}
		System.out.println();
	}
	
	
	public static void main(String[] args)
	{
		System.out.println("Hello World, nice to see you!");
		System.out.println(2 + 2);
		System.out.println("2" + "2");
		System.out.println("2" + 2);
		
		lineSpacing();

		int integer = 4;
		System.out.println(integer);
		System.out.println(2 * integer);

		lineSpacing();
		
		char character = (char) integer;
		System.out.println(character);
		
		character = 'c';
		System.out.println(character);
		character = '¿'; //non ASCII!
		System.out.println(character);
		
		lineSpacing();
		
		System.out.println( 4 == 4);
		String one = "test";
		String two = "test";
		System.out.println( one.equals(two));
	}	
}
